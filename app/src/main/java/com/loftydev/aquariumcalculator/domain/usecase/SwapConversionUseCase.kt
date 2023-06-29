package com.loftydev.aquariumcalculator.domain.usecase

class SwapConversionUseCase {
    fun execute(conversionType: ConversionType): ConversionType {
        return conversionType.swap()
    }
}