package offer.di

import offer.domain.GetOfferUseCase
import org.koin.dsl.module

internal fun offerModule() = module {
    factory { GetOfferUseCase(get(), get()) }
}