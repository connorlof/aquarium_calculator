package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository

class GetHeatersUseCase(private val equipmentRepository: EquipmentRepository) {
    suspend fun execute(): Resource<EquipmentResponse> {
        return equipmentRepository.getHeaters()
    }
}