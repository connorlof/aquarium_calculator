package com.loftydev.aquariumcalculator.presentation.di

import android.app.Application
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SaveUnitSettingsUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitSettingsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideUnitConverterViewModelFactory(
        getConversionUseCase: GetConversionUseCase,
        swapConversionUseCase: SwapConversionUseCase
    ): UnitConverterViewModelFactory {
        return UnitConverterViewModelFactory(getConversionUseCase, swapConversionUseCase)
    }

    @Singleton
    @Provides
    fun provideStockingViewModelFactory(
        getInchPerGallonUseCase: GetInchPerGallonUseCase,
        getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase,
        getUnitSettingsUseCase: GetUnitSettingsUseCase
    ): StockingViewModelFactory {
        return StockingViewModelFactory(getInchPerGallonUseCase, getSurfaceAreaStockingUseCase, getUnitSettingsUseCase)
    }

    @Singleton
    @Provides
    fun provideEquipmentViewModelFactory(
        getFiltersUseCase: GetFiltersUseCase,
        getHeatersUseCase: GetHeatersUseCase,
        getUnitSettingsUseCase: GetUnitSettingsUseCase,
    ): EquipmentViewModelFactory {
        return EquipmentViewModelFactory(getFiltersUseCase, getHeatersUseCase, getUnitSettingsUseCase)
    }

    @Singleton
    @Provides
    fun provideUnitSettingsViewModelFactory(
        getUnitSettingsUseCase: GetUnitSettingsUseCase,
        saveUnitSettingsUseCase: SaveUnitSettingsUseCase,
    ): UnitSettingsViewModelFactory {
        return UnitSettingsViewModelFactory(getUnitSettingsUseCase, saveUnitSettingsUseCase)
    }

}