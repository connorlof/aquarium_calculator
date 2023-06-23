package com.loftydev.aquariumcalculator.data.api

import com.loftydev.aquariumcalculator.data.model.FilterResponse
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentAPIService {
    @GET("data/filters")
    suspend fun getFilters(): Response<FilterResponse>
}