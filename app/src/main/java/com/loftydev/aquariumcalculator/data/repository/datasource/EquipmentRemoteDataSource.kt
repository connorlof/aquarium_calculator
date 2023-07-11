package com.loftydev.aquariumcalculator.data.repository.datasource

import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import retrofit2.Response

interface EquipmentRemoteDataSource {
    suspend fun getFilters(): Response<EquipmentResponse>
    suspend fun getHeaters(): Response<EquipmentResponse>
}