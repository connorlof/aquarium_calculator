package com.loftydev.aquariumcalculator.data.model

enum class ConversionType(val header: String) {

    // Temperature
    FAHRENHEIT_TO_CELSIUS("F to C") {
        override fun calculate(initialValue: Double): Double {
            return (initialValue - 32.0) * (5.0 / 9.0)
        }

        override fun swap(): ConversionType = CELSIUS_TO_FAHRENHEIT
    },
    CELSIUS_TO_FAHRENHEIT("C to F") {
        override fun calculate(initialValue: Double): Double {
            return (initialValue * (9.0 / 5.0)) + 32
        }

        override fun swap(): ConversionType = FAHRENHEIT_TO_CELSIUS
    },
    // Volume
    GALLONS_TO_LITERS("Gallons to Liters") {
        override fun calculate(initialValue: Double): Double {
            return initialValue * 3.78541
        }

        override fun swap(): ConversionType = LITERS_TO_GALLONS
    },
    LITERS_TO_GALLONS("Liters to Gallons") {
        override fun calculate(initialValue: Double): Double {
            return initialValue / 3.78541
        }

        override fun swap(): ConversionType = GALLONS_TO_LITERS
    },
    // Distance
    INCHES_TO_MM("Inch to MM") {
        override fun calculate(initialValue: Double): Double {
            return initialValue * 25.4
        }

        override fun swap(): ConversionType = MM_TO_INCHES
    },
    MM_TO_INCHES("MM to Inch") {
        override fun calculate(initialValue: Double): Double {
            return initialValue / 25.4
        }

        override fun swap(): ConversionType = INCHES_TO_MM
    },
    // Hardness
    PPM_TO_DEGREES("PPM to DgH") {
        override fun calculate(initialValue: Double): Double {
            return initialValue / 17.86
        }

        override fun swap(): ConversionType = DEGREES_TO_PPM
    },
    DEGREES_TO_PPM("DgH to PPM") {
        override fun calculate(initialValue: Double): Double {
            return initialValue * 17.86
        }

        override fun swap(): ConversionType = PPM_TO_DEGREES
    };

    abstract fun calculate(initialValue: Double): Double
    abstract fun swap(): ConversionType
}