package com.loftydev.aquariumcalculator.domain.usecase

class GetSurfaceAreaStockingUseCase {
    // TODO: Provide the ability to enter tank dimensions (need to consider tank shape)
    fun execute(inchesOfFish: Double, surfaceAreaSqInches: Double): Double {
        val maxInches = surfaceAreaSqInches / 12
        return inchesOfFish / maxInches
    }
}