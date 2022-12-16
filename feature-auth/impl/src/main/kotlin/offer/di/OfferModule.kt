package offer.di

import offer.domain.GetOfferUseCase
import org.koin.dsl.module

fun offerModule() = module {
    factory { GetOfferUseCase(get(), get()) }
}