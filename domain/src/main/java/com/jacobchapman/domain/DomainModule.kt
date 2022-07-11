package com.jacobchapman.domain

import com.jacobchapman.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun getEmployeeDirectory(defaultGetEmployeeDirectory: DefaultGetEmployeeDirectoryUseCase): GetEmployeeDirectoryUseCase

    @Binds
    abstract fun getEmployeeFilters(defaultGetEmployeeDirectoryFiltersUseCase: DefaultGetEmployeeDirectoryFiltersUseCase): GetEmployeeDirectoryFiltersUseCase

    @Binds
    abstract fun refreshDirectory(defaultRefreshEmployeeDirectoryUseCase: DefaultRefreshEmployeeDirectoryUseCase): RefreshEmployeeDirectoryUseCase
}