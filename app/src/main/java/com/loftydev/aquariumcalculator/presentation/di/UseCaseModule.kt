package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetFiltersUseCase(
        equipmentRepository: EquipmentRepository
    ): GetFiltersUseCase {
        return GetFiltersUseCase(equipmentRepository)
    }

}