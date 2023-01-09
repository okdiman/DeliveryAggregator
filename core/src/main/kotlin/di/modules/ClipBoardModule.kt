package di.modules

import android.content.ClipboardManager
import android.content.Context
import clipboard.data.ClipboardRepositoryImpl
import clipboard.domain.ClipboardRepository
import clipboard.domain.ClipboardUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal fun clipboardModule() = module {
    single<ClipboardRepository> { ClipboardRepositoryImpl(get()) }
    factory { ClipboardUseCase(get()) }
    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
}