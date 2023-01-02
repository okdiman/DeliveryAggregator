package root.di

import data.ProfileRepository
import data.mapper.ProfileModelMapper
import deleting.domain.DeleteProfileUseCase
import domain.GetMaskedPhoneUseCase
import editing.domain.GetMaskedPhoneUseCaseImpl
import editing.domain.UpdateProfileUseCase
import exit.domain.ExitFromProfileUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.ProfileApi
import root.data.ProfileRepositoryImpl
import root.domain.GetProfileUseCase
import root.presentation.mapper.ProfileUiMapper

fun profileModule() = module {
    single<ProfileApi> { get<Retrofit>().create() }
    factory { ProfileModelMapper() }
    factory { GetProfileUseCase(get()) }
    factory { UpdateProfileUseCase(get()) }
    factory { DeleteProfileUseCase(get()) }
    factory { ExitFromProfileUseCase(get()) }
    factory { ProfileUiMapper(get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
    factory<GetMaskedPhoneUseCase> { GetMaskedPhoneUseCaseImpl() }
}