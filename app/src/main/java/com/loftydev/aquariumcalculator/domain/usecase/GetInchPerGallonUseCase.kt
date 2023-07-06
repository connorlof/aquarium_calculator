package com.loftydev.aquariumcalculator.domain.usecase

class GetInchPerGallonUseCase {
    fun execute(inchesOfFish: Double, tankGallons: Double): Double {
        return inchesOfFish / tankGallons
    }
}