package com.loftydev.aquariumcalculator.data.repository.datasource

import com.loftydev.aquariumcalculator.data.model.FilterResponse
import retrofit2.Response

interface EquipmentRemoteDataSource {
    suspend fun getFilters(): Response<FilterResponse>
}