package trinity_monsters.delivery_aggregator.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import trinity_monsters.delivery_aggregator.di.applicationModules

class DeliveryAggregatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidContext(this@DeliveryAggregatorApp)
            modules(applicationModules())
        }
    }
}