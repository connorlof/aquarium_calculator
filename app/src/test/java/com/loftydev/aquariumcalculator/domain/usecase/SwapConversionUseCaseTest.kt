package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth
import com.loftydev.aquariumcalculator.data.model.ConversionType
import org.junit.Test

class SwapConversionUseCaseTest {

    private val useCase = SwapConversionUseCase()

    @Test
    fun execute_correctValue() {
        // Temperature
        Truth.assertThat(useCase.execute(ConversionType.FAHRENHEIT_TO_CELSIUS)).isEqualTo(ConversionType.CELSIUS_TO_FAHRENHEIT)
        Truth.assertThat(useCase.execute(ConversionType.CELSIUS_TO_FAHRENHEIT)).isEqualTo(ConversionType.FAHRENHEIT_TO_CELSIUS)

        // Volume
        Truth.assertThat(useCase.execute(ConversionType.GALLONS_TO_LITERS)).isEqualTo(ConversionType.LITERS_TO_GALLONS)
        Truth.assertThat(useCase.execute(ConversionType.LITERS_TO_GALLONS)).isEqualTo(ConversionType.GALLONS_TO_LITERS)

        // Distance
        Truth.assertThat(useCase.execute(ConversionType.INCHES_TO_MM)).isEqualTo(ConversionType.MM_TO_INCHES)
        Truth.assertThat(useCase.execute(ConversionType.MM_TO_INCHES)).isEqualTo(ConversionType.INCHES_TO_MM)

        // Hardness
        Truth.assertThat(useCase.execute(ConversionType.PPM_TO_DEGREES)).isEqualTo(ConversionType.DEGREES_TO_PPM)
        Truth.assertThat(useCase.execute(ConversionType.DEGREES_TO_PPM)).isEqualTo(ConversionType.PPM_TO_DEGREES)
    }

}