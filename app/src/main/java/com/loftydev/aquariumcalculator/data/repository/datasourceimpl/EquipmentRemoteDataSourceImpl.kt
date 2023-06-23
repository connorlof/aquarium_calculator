package com.loftydev.aquariumcalculator.data.repository.datasourceimpl

import com.loftydev.aquariumcalculator.data.api.EquipmentAPIService
import com.loftydev.aquariumcalculator.data.model.FilterResponse
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import retrofit2.Response

class EquipmentRemoteDataSourceImpl(
    private val equipmentAPIService: EquipmentAPIService
) : EquipmentRemoteDataSource {
    override suspend fun getFilters(): Response<FilterResponse> {
        return equipmentAPIService.getFilters()
    }
}