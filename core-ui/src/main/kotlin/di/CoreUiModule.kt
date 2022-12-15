package di

import org.koin.dsl.module
import utils.resource.data.ResourceRepositoryImpl
import utils.resource.domain.ResourceInteractor
import utils.resource.domain.ResourceRepository

fun coreUiiModule() = module {
    factory { ResourceInteractor(get()) }
    factory<ResourceRepository> { ResourceRepositoryImpl(get()) }
}