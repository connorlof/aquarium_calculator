package com.loftydev.aquariumcalculator.data.api

import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentAPIService {
    @GET("data/filters.json")
    suspend fun getFilters(): Response<EquipmentResponse>

    @GET("data/heaters.json")
    suspend fun getHeaters(): Response<EquipmentResponse>
}