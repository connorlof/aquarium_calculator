package com.loftydev.aquariumcalculator.domain.usecase

class GetConversionUseCase {
    fun execute(initialValue: Double, conversionType: ConversionType): Double {
        return conversionType.calculate(initialValue)
    }
}