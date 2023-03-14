package clipboard

import clipboard.data.ClipboardRepositoryImpl
import clipboard.domain.ClipboardInteractor
import clipboard.domain.ClipboardRepository
import org.koin.dsl.module

internal fun clipboardModule() = module {
    single<ClipboardRepository> { ClipboardRepositoryImpl(get()) }
    factory { ClipboardInteractor(get()) }
}