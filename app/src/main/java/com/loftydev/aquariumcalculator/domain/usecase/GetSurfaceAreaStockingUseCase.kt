package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.UnitSystemType

class GetSurfaceAreaStockingUseCase {
    // TODO: Provide the ability to enter tank dimensions (need to consider tank shape)
    fun execute(
        fishDistance: Double,
        surfaceArea: Double,
        distanceUnit: UnitSystemType,
    ): Double {
        val inchesOfFish = if (distanceUnit == UnitSystemType.METRIC) {
            fishDistance / 25.4
        } else fishDistance

        val surfaceAreaSqInches = if (distanceUnit == UnitSystemType.METRIC) {
            surfaceArea / 645.2
        } else surfaceArea

        val maxInches = surfaceAreaSqInches / 12
        return inchesOfFish / maxInches
    }
}