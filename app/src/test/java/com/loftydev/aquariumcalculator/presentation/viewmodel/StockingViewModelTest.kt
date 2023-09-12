package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.usecase.GetInchPerGallonUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetSurfaceAreaStockingUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class StockingViewModelTest {

    private lateinit var viewModel: StockingViewModel

    private val getInchPerGallonUseCase = GetInchPerGallonUseCase()
    private val getSurfaceAreaStockingUseCase = GetSurfaceAreaStockingUseCase()
    private val getUnitSettingsUseCase = Mockito.mock(GetUnitSettingsUseCase::class.java)

    @JvmField
    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = StockingViewModel(getInchPerGallonUseCase, getSurfaceAreaStockingUseCase, getUnitSettingsUseCase)
    }

    @Test
    fun stockingViewModel_CalculateInchPerGallonValidInputs_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateInchPerGallon(10.0, 20.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(0.5)
        }
    }

    @Test
    fun stockingViewModel_CalculateSurfaceAreaValidInputs_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateSurfaceArea(10.0, 288.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(0.4166666666666667)
        }
    }

    @Test
    fun stockingViewModel_CalculateThenReset_Zero() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateInchPerGallon(10.0, 20.0).invokeOnCompletion {
            assertThat(viewModel.stockingPercent.value).isNonZero()
        }

        viewModel.reset().invokeOnCompletion {
            assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)
        }
    }

    @Test
    fun stockingViewModel_CalculateInchPerGallonNegativeFishDistance_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateInchPerGallon(-10.0, 20.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(-0.5)
        }
    }

    @Test
    fun stockingViewModel_CalculateInchPerGallonNegativeVolume_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateInchPerGallon(10.0, -20.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(-0.5)
        }
    }

    @Test
    fun stockingViewModel_CalculateSurfaceAreaNegativeFishDistance_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateSurfaceArea(-10.0, 288.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(-0.4166666666666667)
        }
    }

    @Test
    fun stockingViewModel_CalculateSurfaceAreaNegativeVolume_CorrectOutput() = runTest {
        assertThat(viewModel.stockingPercent.value).isEqualTo(0.0)

        viewModel.calculateSurfaceArea(10.0, -288.0).invokeOnCompletion {
            assertThat(viewModel.distanceUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.volumeUnit.value).isEqualTo(UnitSystemType.IMPERIAL)
            assertThat(viewModel.stockingPercent.value).isEqualTo(-0.4166666666666667)
        }
    }

}