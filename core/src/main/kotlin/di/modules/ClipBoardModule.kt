package di.modules

import android.content.ClipboardManager
import android.content.Context
import org.koin.dsl.module
import clipboard.data.ClipboardRepositoryImpl
import clipboard.domain.ClipboardUseCase
import clipboard.domain.ClipboardRepository
import org.koin.android.ext.koin.androidContext

fun clipboardModule() = module {
    single<ClipboardRepository> { ClipboardRepositoryImpl(get()) }
    factory { ClipboardUseCase(get()) }
    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
}