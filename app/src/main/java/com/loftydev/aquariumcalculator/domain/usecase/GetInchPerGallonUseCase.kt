package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.UnitSystemType

class GetInchPerGallonUseCase {
    fun execute(
        fishDistance: Double,
        distanceUnit: UnitSystemType,
        tankVolume: Double,
        volumeUnit: UnitSystemType
    ): Double {
        val inchesOfFish = if (distanceUnit == UnitSystemType.METRIC) {
            fishDistance / 25.4
        } else fishDistance

        val tankGallons = if (volumeUnit == UnitSystemType.METRIC) {
            tankVolume / 3.785
        } else tankVolume

        return inchesOfFish / tankGallons
    }
}