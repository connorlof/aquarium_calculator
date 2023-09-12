package com.loftydev.aquariumcalculator.data.model

import  com.loftydev.aquariumcalculator.data.model.ConversionType.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConversionTypeTest {

    @Test
    fun correctValue_calculate_FAHRENHEIT_TO_CELSIUS() {
        assertThat(FAHRENHEIT_TO_CELSIUS.calculate(32.0)).isEqualTo(0.0)
        assertThat(FAHRENHEIT_TO_CELSIUS.calculate(-32.0)).isEqualTo(-35.55555555555556)
        assertThat(FAHRENHEIT_TO_CELSIUS.calculate(0.0)).isEqualTo(-17.77777777777778)
        assertThat(FAHRENHEIT_TO_CELSIUS.calculate(80.0)).isEqualTo(26.666666666666668)
    }

    @Test
    fun correctValue_calculate_CELSIUS_TO_FAHRENHEIT() {
        assertThat(CELSIUS_TO_FAHRENHEIT.calculate(0.0)).isEqualTo(32.0)
        assertThat(CELSIUS_TO_FAHRENHEIT.calculate(-18.0)).isEqualTo(-0.3999999999999986)
        assertThat(CELSIUS_TO_FAHRENHEIT.calculate(28.0)).isEqualTo(82.4)
    }

    @Test
    fun swap_FAHRENHEIT_TO_CELSIUS() {
        assertThat(FAHRENHEIT_TO_CELSIUS.swap()).isEqualTo(CELSIUS_TO_FAHRENHEIT)
        assertThat(CELSIUS_TO_FAHRENHEIT.swap()).isEqualTo(FAHRENHEIT_TO_CELSIUS)
    }

    @Test
    fun correctValue_calculate_GALLONS_TO_LITERS() {
        assertThat(GALLONS_TO_LITERS.calculate(1.0)).isEqualTo(3.78541)
        assertThat(GALLONS_TO_LITERS.calculate(15.0)).isEqualTo(56.781150000000004)
    }

    @Test
    fun correctValue_calculate_LITERS_TO_GALLONS() {
        assertThat(LITERS_TO_GALLONS.calculate(3.78541)).isEqualTo(1.0)
        assertThat(LITERS_TO_GALLONS.calculate(56.781150000000004)).isEqualTo(15.0)
    }

    @Test
    fun swap_GALLONS_TO_LITERS() {
        assertThat(GALLONS_TO_LITERS.swap()).isEqualTo(LITERS_TO_GALLONS)
        assertThat(LITERS_TO_GALLONS.swap()).isEqualTo(GALLONS_TO_LITERS)
    }

    @Test
    fun correctValue_calculate_INCHES_TO_MM() {
        assertThat(INCHES_TO_MM.calculate(1.0)).isEqualTo(25.4)
        assertThat(INCHES_TO_MM.calculate(15.0)).isEqualTo(381.0)
    }

    @Test
    fun correctValue_calculate_MM_TO_INCHES() {
        assertThat(MM_TO_INCHES.calculate(25.4)).isEqualTo(1.0)
        assertThat(MM_TO_INCHES.calculate(381.0)).isEqualTo(15.0)
    }

    @Test
    fun swap_INCHES_TO_MM() {
        assertThat(INCHES_TO_MM.swap()).isEqualTo(MM_TO_INCHES)
        assertThat(MM_TO_INCHES.swap()).isEqualTo(INCHES_TO_MM)
    }

    @Test
    fun correctValue_calculate_PPM_TO_DEGREES() {
        assertThat(PPM_TO_DEGREES.calculate(17.86)).isEqualTo(1.0)
        assertThat(PPM_TO_DEGREES.calculate(214.32)).isEqualTo(12.0)
    }

    @Test
    fun correctValue_calculate_DEGREES_TO_PPM() {
        assertThat(DEGREES_TO_PPM.calculate(1.0)).isEqualTo(17.86)
        assertThat(DEGREES_TO_PPM.calculate(12.0)).isEqualTo(214.32)
    }

    @Test
    fun swap_PPM_TO_DEGREES() {
        assertThat(PPM_TO_DEGREES.swap()).isEqualTo(DEGREES_TO_PPM)
        assertThat(DEGREES_TO_PPM.swap()).isEqualTo(PPM_TO_DEGREES)
    }

}