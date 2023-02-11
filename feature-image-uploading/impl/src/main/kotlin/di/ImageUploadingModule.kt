package di

import data.ImageUploadingApi
import data.ImageUploadingRepositoryImpl
import domain.ImageUploadingRepository
import domain.LoadImageUseCase
import domain.LoadImageUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun imageUploadingModule() = module {
    single<ImageUploadingApi> { get<Retrofit>().create() }
    factory<ImageUploadingRepository> { ImageUploadingRepositoryImpl(androidContext(), get()) }
    factory<LoadImageUseCase> { LoadImageUseCaseImpl(get()) }
}