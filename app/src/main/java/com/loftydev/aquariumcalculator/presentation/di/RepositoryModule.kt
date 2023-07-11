package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.data.repository.EquipmentRepositoryImpl
import com.loftydev.aquariumcalculator.data.repository.UnitSettingsRepositoryImpl
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import com.loftydev.aquariumcalculator.data.repository.datasource.UnitSettingsLocalDataSource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import com.loftydev.aquariumcalculator.domain.repository.UnitSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideEquipmentRepository(
        equipmentRemoteDataSource: EquipmentRemoteDataSource
    ): EquipmentRepository {
        return EquipmentRepositoryImpl(equipmentRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideUnitSettingsRepository(
        unitSettingsLocalDataSource: UnitSettingsLocalDataSource
    ): UnitSettingsRepository {
        return UnitSettingsRepositoryImpl(unitSettingsLocalDataSource)
    }

}