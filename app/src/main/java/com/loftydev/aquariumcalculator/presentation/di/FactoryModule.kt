package com.loftydev.aquariumcalculator.presentation.di

import android.app.Application
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
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
    fun provideEquipmentViewModelFactory(
        app: Application,
        getFiltersUseCase: GetFiltersUseCase
    ): EquipmentViewModelFactory {
        return EquipmentViewModelFactory(app, getFiltersUseCase)
    }

}