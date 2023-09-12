package com.loftydev.aquariumcalculator.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.ConversionType
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UnitConverterViewModelTest {

    private lateinit var viewModel: UnitConverterViewModel

    private val getConversionUseCase = GetConversionUseCase()
    private val swapConversionUseCase = SwapConversionUseCase()

    @Before
    fun setup() {
        viewModel = UnitConverterViewModel(getConversionUseCase, swapConversionUseCase)
    }

    @Test
    fun unitConverterViewModel_Convert_ExpectedValue() = runTest {
        viewModel.convert(32.0, ConversionType.FAHRENHEIT_TO_CELSIUS).invokeOnCompletion {
            assertThat(viewModel.output.value).isEqualTo(0.0)
            assertThat(viewModel.conversionType).isEqualTo(ConversionType.FAHRENHEIT_TO_CELSIUS)
        }
    }

    @Test
    fun unitConverterViewModel_SwapUnits_ExpectedValue() = runTest {
        viewModel.convert(32.0, ConversionType.FAHRENHEIT_TO_CELSIUS).invokeOnCompletion {
            assertThat(viewModel.output.value).isEqualTo(0.0)
            assertThat(viewModel.conversionType.value).isEqualTo(ConversionType.FAHRENHEIT_TO_CELSIUS)

            viewModel.swapUnits()
            assertThat(viewModel.conversionType.value).isEqualTo(ConversionType.CELSIUS_TO_FAHRENHEIT)
        }
    }

    @Test
    fun unitConverterViewModel_ConvertNegativeInput_ExpectedValue() = runTest {
        viewModel.convert(-32.0, ConversionType.FAHRENHEIT_TO_CELSIUS).invokeOnCompletion {
            assertThat(viewModel.output.value).isEqualTo(-35.55555555555556)
        }
    }

    @Test
    fun unitConverterViewModel_SwapUnitsNoneSet_ExpectedValue() = runTest {
        assertThat(viewModel.conversionType.value).isNull()

        viewModel.swapUnits().invokeOnCompletion {
            assertThat(viewModel.conversionType.value).isNull()
        }
    }
}