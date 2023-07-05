package com.loftydev.aquariumcalculator.domain.usecase

import com.loftydev.aquariumcalculator.data.model.ConversionType

class SwapConversionUseCase {
    fun execute(conversionType: ConversionType): ConversionType {
        return conversionType.swap()
    }
}