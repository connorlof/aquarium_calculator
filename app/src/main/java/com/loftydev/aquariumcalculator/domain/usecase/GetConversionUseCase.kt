package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.util.Resource

class GetConversionUseCase {

    fun execute(initialValue: Double, conversionType: ConversionType): Double {
        return conversionType.calculateConversion(initialValue)
    }

}