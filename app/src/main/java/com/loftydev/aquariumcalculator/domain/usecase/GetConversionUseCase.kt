package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.ConversionType

class GetConversionUseCase {
    fun execute(initialValue: Double, conversionType: ConversionType): Double {
        return conversionType.calculate(initialValue)
    }
}