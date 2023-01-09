package root.di

import data.ProfileRepository
import data.mapper.ProfileModelMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.ProfileApi
import root.data.ProfileRepositoryImpl
import root.domain.GetProfileUseCase
import root.presentation.mapper.ProfileUiMapper

internal fun profileRootModule() = module {
    single<ProfileApi> { get<Retrofit>().create() }
    factory { ProfileModelMapper() }
    factory { GetProfileUseCase(get()) }
    factory { ProfileUiMapper(get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
}