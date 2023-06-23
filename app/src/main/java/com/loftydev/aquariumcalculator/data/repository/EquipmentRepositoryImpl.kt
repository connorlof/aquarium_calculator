package com.loftydev.aquariumcalculator.data.repository

import com.loftydev.aquariumcalculator.data.model.FilterResponse
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import retrofit2.Response

class EquipmentRepositoryImpl(
    private val equipmentRemoteDataSource: EquipmentRemoteDataSource
) : EquipmentRepository {
    override suspend fun getFilters(): Resource<FilterResponse> {
        return responseToFilterResource(equipmentRemoteDataSource.getFilters())
    }

    private fun responseToFilterResource(response: Response<FilterResponse>): Resource<FilterResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}