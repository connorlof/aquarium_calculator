package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.data.db.UnitSettingsDao
import com.loftydev.aquariumcalculator.data.repository.datasource.UnitSettingsLocalDataSource
import com.loftydev.aquariumcalculator.data.repository.datasourceimpl.UnitSettingsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(unitSettingsDao: UnitSettingsDao): UnitSettingsLocalDataSource {
        return UnitSettingsLocalDataSourceImpl(unitSettingsDao)
    }

}