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
        app: Application,
        getConversionUseCase: GetConversionUseCase,
        swapConversionUseCase: SwapConversionUseCase
    ): UnitConverterViewModelFactory {
        return UnitConverterViewModelFactory(app, getConversionUseCase, swapConversionUseCase)
    }

    @Singleton
    @Provides
    fun provideStockingViewModelFactory(
        app: Application,
        getInchPerGallonUseCase: GetInchPerGallonUseCase,
        getSurfaceAreaStockingUseCase: GetSurfaceAreaStockingUseCase
    ): StockingViewModelFactory {
        return StockingViewModelFactory(app, getInchPerGallonUseCase, getSurfaceAreaStockingUseCase)
    }

    @Singleton
    @Provides
    fun provideEquipmentViewModelFactory(
        app: Application,
        getFiltersUseCase: GetFiltersUseCase,
        getHeatersUseCase: GetHeatersUseCase
    ): EquipmentViewModelFactory {
        return EquipmentViewModelFactory(app, getFiltersUseCase, getHeatersUseCase)
    }

    @Singleton
    @Provides
    fun provideUnitSettingsViewModelFactory(
        app: Application,
        getUnitSettingsUseCase: GetUnitSettingsUseCase,
        saveUnitSettingsUseCase: SaveUnitSettingsUseCase,
    ): UnitSettingsViewModelFactory {
        return UnitSettingsViewModelFactory(app, getUnitSettingsUseCase, saveUnitSettingsUseCase)
    }

}