package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetHeatersUseCaseTest {

    private lateinit var useCase: GetHeatersUseCase

    private val repository = Mockito.mock(EquipmentRepository::class.java)

    @Before
    fun setup() {
        useCase = GetHeatersUseCase(repository)
    }

    @Test
    fun execute_correctResourceType() = runBlocking {
        // Error
        val errorResource = Resource.Error<EquipmentResponse>("Error")
        Mockito.`when`(repository.getHeaters()).thenReturn(errorResource)
        Truth.assertThat(useCase.execute()).isEqualTo(errorResource)

        // Loading
        val loadingResource = Resource.Loading<EquipmentResponse>()
        Mockito.`when`(repository.getHeaters()).thenReturn(loadingResource)
        Truth.assertThat(useCase.execute()).isEqualTo(loadingResource)

        // Success
        val successResource = Resource.Success(EquipmentResponse())
        Mockito.`when`(repository.getHeaters()).thenReturn(successResource)
        Truth.assertThat(useCase.execute()).isEqualTo(successResource)
    }
}