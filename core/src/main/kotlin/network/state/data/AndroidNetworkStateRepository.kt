package network.state.data

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import network.state.domain.NetworkStateRepository
import utils.ext.offerSafe

class AndroidNetworkStateRepository(private val context: Context) : NetworkStateRepository {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isInternetOn(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isNetworkAvailableFlow(): Flow<Boolean> {
        return callbackFlow {
            var currentActiveNetworks = 0
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    currentActiveNetworks++
                    offerSafe(currentActiveNetworks > 0)
                }

                override fun onLost(network: Network) {
                    currentActiveNetworks--
                    offerSafe(currentActiveNetworks > 0)
                    super.onLost(network)
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cm.registerDefaultNetworkCallback(callback)
            } else {
                val request = NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
                cm.registerNetworkCallback(request, callback)
            }

            if (!isInternetOn()) {
                offerSafe(false)
            }
            awaitClose {
                cm.unregisterNetworkCallback(callback)
            }
        }
    }
}