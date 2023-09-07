package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.ConversionType
import org.junit.Test

class SwapConversionUseCaseTest {

    private val useCase = SwapConversionUseCase()

    @Test
    fun execute_correctValue() {
        // Temperature
        assertThat(useCase.execute(ConversionType.FAHRENHEIT_TO_CELSIUS)).isEqualTo(ConversionType.CELSIUS_TO_FAHRENHEIT)
        assertThat(useCase.execute(ConversionType.CELSIUS_TO_FAHRENHEIT)).isEqualTo(ConversionType.FAHRENHEIT_TO_CELSIUS)

        // Volume
        assertThat(useCase.execute(ConversionType.GALLONS_TO_LITERS)).isEqualTo(ConversionType.LITERS_TO_GALLONS)
        assertThat(useCase.execute(ConversionType.LITERS_TO_GALLONS)).isEqualTo(ConversionType.GALLONS_TO_LITERS)

        // Distance
        assertThat(useCase.execute(ConversionType.INCHES_TO_MM)).isEqualTo(ConversionType.MM_TO_INCHES)
        assertThat(useCase.execute(ConversionType.MM_TO_INCHES)).isEqualTo(ConversionType.INCHES_TO_MM)

        // Hardness
        assertThat(useCase.execute(ConversionType.PPM_TO_DEGREES)).isEqualTo(ConversionType.DEGREES_TO_PPM)
        assertThat(useCase.execute(ConversionType.DEGREES_TO_PPM)).isEqualTo(ConversionType.PPM_TO_DEGREES)
    }

}