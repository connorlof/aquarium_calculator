package com.loftydev.aquariumcalculator.data.repository

import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import retrofit2.Response

class EquipmentRepositoryImpl(
    private val equipmentRemoteDataSource: EquipmentRemoteDataSource
) : EquipmentRepository {
    override suspend fun getFilters(): Resource<EquipmentResponse> {
        return responseToResource(equipmentRemoteDataSource.getFilters())
    }

    override suspend fun getHeaters(): Resource<EquipmentResponse> {
        return responseToResource(equipmentRemoteDataSource.getHeaters())
    }


    private fun responseToResource(response: Response<EquipmentResponse>): Resource<EquipmentResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}