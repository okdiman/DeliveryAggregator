package permissions.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import permissions.data.PermissionsRepositoryImpl
import permissions.data.datasource.PermissionsDataSource
import permissions.domain.PermissionsRepository
import permissions.domain.interactor.PermissionsInteractor

internal fun permissionsModule() = module {
    factory { PermissionsDataSource(androidContext(), get(), get()) }
    factory { PermissionsInteractor(get()) }
    factory<PermissionsRepository> { PermissionsRepositoryImpl(get()) }
}