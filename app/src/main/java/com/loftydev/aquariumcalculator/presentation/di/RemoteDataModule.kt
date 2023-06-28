package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.data.api.EquipmentAPIService
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import com.loftydev.aquariumcalculator.data.repository.datasourceimpl.EquipmentRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideEquipmentRemoteDataSource(
        equipmentAPIService: EquipmentAPIService
    ): EquipmentRemoteDataSource {
        return EquipmentRemoteDataSourceImpl(equipmentAPIService)
    }

}