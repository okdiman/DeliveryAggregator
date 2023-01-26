package di.modules

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import network.state.data.AndroidNetworkStateRepository
import network.state.domain.NetworkStateInteractor
import network.state.domain.NetworkStateRepository

fun networkStateModule() = module {
    factory { AndroidNetworkStateRepository(androidContext()) } bind NetworkStateRepository::class
    factory { NetworkStateInteractor(get()) }
}