package com.loftydev.aquariumcalculator.domain.usecase

import  com.loftydev.aquariumcalculator.domain.usecase.ConversionType.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConversionTypeTest {

    @Test
    fun correctValue_FAHRENHEIT_TO_CELSIUS() {
        assertThat(FAHRENHEIT_TO_CELSIUS.calculateConversion(32.0)).isEqualTo(0.0)
        assertThat(FAHRENHEIT_TO_CELSIUS.calculateConversion(0.0)).isEqualTo(-17.77777777777778)
        assertThat(FAHRENHEIT_TO_CELSIUS.calculateConversion(80.0)).isEqualTo(26.666666666666668)
    }

    @Test
    fun correctValue_CELSIUS_TO_FAHRENHEIT() {
        assertThat(CELSIUS_TO_FAHRENHEIT.calculateConversion(0.0)).isEqualTo(32.0)
        assertThat(CELSIUS_TO_FAHRENHEIT.calculateConversion(-18.0)).isEqualTo(-0.3999999999999986)
        assertThat(CELSIUS_TO_FAHRENHEIT.calculateConversion(28.0)).isEqualTo(82.4)
    }

    @Test
    fun correctValue_GALLONS_TO_LITERS() {
        assertThat(GALLONS_TO_LITERS.calculateConversion(1.0)).isEqualTo(3.78541)
        assertThat(GALLONS_TO_LITERS.calculateConversion(15.0)).isEqualTo(56.781150000000004)
    }

    @Test
    fun correctValue_LITERS_TO_GALLONS() {
        assertThat(LITERS_TO_GALLONS.calculateConversion(3.78541)).isEqualTo(1.0)
        assertThat(LITERS_TO_GALLONS.calculateConversion(56.781150000000004)).isEqualTo(15.0)
    }

    @Test
    fun correctValue_INCHES_TO_MM() {
        assertThat(INCHES_TO_MM.calculateConversion(1.0)).isEqualTo(25.4)
        assertThat(INCHES_TO_MM.calculateConversion(15.0)).isEqualTo(381.0)
    }

    @Test
    fun correctValue_MM_TO_INCHES() {
        assertThat(MM_TO_INCHES.calculateConversion(25.4)).isEqualTo(1.0)
        assertThat(MM_TO_INCHES.calculateConversion(381.0)).isEqualTo(15.0)
    }

    @Test
    fun correctValue_PPM_TO_DEGREES() {
        assertThat(PPM_TO_DEGREES.calculateConversion(17.86)).isEqualTo(1.0)
        assertThat(PPM_TO_DEGREES.calculateConversion(214.32)).isEqualTo(12.0)
    }

    @Test
    fun correctValue_DEGREES_TO_PPM() {
        assertThat(DEGREES_TO_PPM.calculateConversion(1.0)).isEqualTo(17.86)
        assertThat(DEGREES_TO_PPM.calculateConversion(12.0)).isEqualTo(214.32)
    }

}