package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase
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
    fun provideGetInchPerGallonUseCase(): GetInchPerGallonUseCase {
        return GetInchPerGallonUseCase()
    }

    @Singleton
    @Provides
    fun provideGetSurfaceAreaStockingUseCase(): GetSurfaceAreaStockingUseCase {
        return GetSurfaceAreaStockingUseCase()
    }

    @Singleton
    @Provides
    fun provideGetConversionUseCase(): GetConversionUseCase {
        return GetConversionUseCase()
    }

    @Singleton
    @Provides
    fun provideSwapConversionUseCase(): SwapConversionUseCase {
        return SwapConversionUseCase()
    }

    @Singleton
    @Provides
    fun provideGetFiltersUseCase(
        equipmentRepository: EquipmentRepository
    ): GetFiltersUseCase {
        return GetFiltersUseCase(equipmentRepository)
    }

    @Singleton
    @Provides
    fun provideGetHeatersUseCase(
        equipmentRepository: EquipmentRepository
    ): GetHeatersUseCase {
        return GetHeatersUseCase(equipmentRepository)
    }

}