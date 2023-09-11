package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class EquipmentViewModelTest {

    private lateinit var viewModel: EquipmentViewModel

    private val getFiltersUseCase = Mockito.mock(GetFiltersUseCase::class.java)
    private val getHeatersUseCase = Mockito.mock(GetHeatersUseCase::class.java)
    private val getUnitSettingsUseCase = Mockito.mock(GetUnitSettingsUseCase::class.java)

    private val success = Resource.Success(EquipmentResponse())
    private val loading = Resource.Loading<EquipmentResponse>()
    private val error = Resource.Error<EquipmentResponse>("Error")

    @JvmField
    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = EquipmentViewModel(getFiltersUseCase, getHeatersUseCase, getUnitSettingsUseCase)
    }

    @Test
    fun equipmentViewModel_GetFiltersSuccess_FiltersUpdated() = runTest {
        `when`(getFiltersUseCase.execute()).thenReturn(success)

        assertThat(viewModel.filters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getFilters().invokeOnCompletion {
            assertThat(viewModel.filters.value).isEqualTo(success)
        }
    }

    @Test
    fun equipmentViewModel_GetHeatersSuccess_HeatersUpdated() = runTest {
        `when`(getHeatersUseCase.execute()).thenReturn(success)

        assertThat(viewModel.heaters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getHeaters().invokeOnCompletion {
            assertThat(viewModel.heaters.value).isEqualTo(success)
        }
    }

    @Test
    fun equipmentViewModel_GetFiltersLoading_FiltersLoading() = runTest  {
        `when`(getFiltersUseCase.execute()).thenReturn(loading)

        assertThat(viewModel.filters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getFilters().invokeOnCompletion {
            assertThat(viewModel.filters.value).isEqualTo(loading)
        }
    }

    @Test
    fun equipmentViewModel_GetHeatersLoading_HeatersLoading() = runTest {
        `when`(getHeatersUseCase.execute()).thenReturn(loading)

        assertThat(viewModel.heaters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getHeaters().invokeOnCompletion {
            assertThat(viewModel.heaters.value).isEqualTo(loading)
        }
    }

    @Test
    fun equipmentViewModel_GetFiltersError_FiltersShowsError() = runTest {
        `when`(getFiltersUseCase.execute()).thenReturn(error)

        assertThat(viewModel.filters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getFilters().invokeOnCompletion {
            assertThat(viewModel.filters.value).isEqualTo(error)
        }
    }

    @Test
    fun equipmentViewModel_GetHeatersError_HeatersShowsError() = runTest {
        `when`(getHeatersUseCase.execute()).thenReturn(error)

        assertThat(viewModel.heaters.value).isInstanceOf(Resource.Loading::class.java)
        viewModel.getHeaters().invokeOnCompletion {
            assertThat(viewModel.heaters.value).isEqualTo(error)
        }
    }

    @Test
    fun equipmentViewModel_InitialViewModel_DataShowsLoading() = runTest {
        assertThat(viewModel.filters.value).isInstanceOf(Resource.Loading::class.java)
        assertThat(viewModel.heaters.value).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun equipmentViewModel_Reset_DataShowsLoading() = runTest {
        viewModel.reset()
        assertThat(viewModel.filters.value).isInstanceOf(Resource.Loading::class.java)
        assertThat(viewModel.heaters.value).isInstanceOf(Resource.Loading::class.java)
    }
}