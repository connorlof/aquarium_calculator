package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.ConversionType
import org.junit.Test

class GetConversionUseCaseTest {

    private val useCase = GetConversionUseCase()

    @Test
    fun execute_correctValue() {
        // Temperature
        assertThat(useCase.execute(32.0, ConversionType.FAHRENHEIT_TO_CELSIUS)).isEqualTo(0.0)
        assertThat(useCase.execute(0.0, ConversionType.CELSIUS_TO_FAHRENHEIT)).isEqualTo(32.0)

        // Volume
        assertThat(useCase.execute(1.0, ConversionType.GALLONS_TO_LITERS)).isEqualTo(3.78541)
        assertThat(useCase.execute(3.78541, ConversionType.LITERS_TO_GALLONS)).isEqualTo(1.0)

        // Distance
        assertThat(useCase.execute(1.0, ConversionType.INCHES_TO_MM)).isEqualTo(25.4)
        assertThat(useCase.execute(25.4, ConversionType.MM_TO_INCHES)).isEqualTo(1.0)

        // Hardness
        assertThat(useCase.execute(17.86, ConversionType.PPM_TO_DEGREES)).isEqualTo(1.0)
        assertThat(useCase.execute(1.0, ConversionType.DEGREES_TO_PPM)).isEqualTo(17.86)
    }
}