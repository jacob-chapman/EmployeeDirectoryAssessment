package com.jacobchapman.core.di

import com.jacobchapman.core.repositories.DefaultEmployeeDirectoryRepository
import com.jacobchapman.domain.EmployeeDirectoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {
    @Binds
    abstract fun employeeDirectoryRepository(defaultEmployeeDirectoryRepository: DefaultEmployeeDirectoryRepository): EmployeeDirectoryRepository
}