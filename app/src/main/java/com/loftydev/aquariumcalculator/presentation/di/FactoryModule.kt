package com.loftydev.aquariumcalculator.presentation.di

import android.app.Application
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModelFactory
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
    ): StockingViewModelFactory {
        return StockingViewModelFactory(app)
    }

    @Singleton
    @Provides
    fun provideEquipmentViewModelFactory(
        app: Application,
        getFiltersUseCase: GetFiltersUseCase
    ): EquipmentViewModelFactory {
        return EquipmentViewModelFactory(app, getFiltersUseCase)
    }

}