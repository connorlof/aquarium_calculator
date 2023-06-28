package com.loftydev.aquariumcalculator.domain.usecase

enum class ConversionType {

    // Temperature
    FAHRENHEIT_TO_CELSIUS {
        override fun calculateConversion(initialValue: Double): Double {
            return (initialValue - 32.0) * (5.0 / 9.0)
        }
    },
    CELSIUS_TO_FAHRENHEIT {
        override fun calculateConversion(initialValue: Double): Double {
            return (initialValue * (9.0 / 5.0)) + 32
        }
    },
    // Volume
    GALLONS_TO_LITERS {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue * 3.78541
        }
    },
    LITERS_TO_GALLONS {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue / 3.78541
        }
    },
    // Distance
    INCHES_TO_MM {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue * 25.4
        }
    },
    MM_TO_INCHES {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue / 25.4
        }
    },
    // Hardness
    PPM_TO_DEGREES {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue / 17.86
        }
    },
    DEGREES_TO_PPM {
        override fun calculateConversion(initialValue: Double): Double {
            return initialValue * 17.86
        }
    };

    abstract fun calculateConversion(initialValue: Double): Double
}