package trinity_monsters.delivery_aggregator.root

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import trinity_monsters.delivery_aggregator.di.applicationModules

class DeliveryAggregatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DeliveryAggregatorApp)
            modules(applicationModules())
        }
    }
}