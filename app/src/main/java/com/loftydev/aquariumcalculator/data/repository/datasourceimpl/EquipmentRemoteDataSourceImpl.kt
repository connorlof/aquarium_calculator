package com.loftydev.aquariumcalculator.data.repository.datasourceimpl

import com.loftydev.aquariumcalculator.data.api.EquipmentAPIService
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import retrofit2.Response

class EquipmentRemoteDataSourceImpl(
    private val equipmentAPIService: EquipmentAPIService
) : EquipmentRemoteDataSource {
    override suspend fun getFilters(): Response<EquipmentResponse> {
        return equipmentAPIService.getFilters()
    }

    override suspend fun getHeaters(): Response<EquipmentResponse> {
        return equipmentAPIService.getHeaters()
    }
}