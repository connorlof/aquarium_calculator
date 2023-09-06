package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.repository.EquipmentRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetFiltersUseCaseTest {

    private lateinit var useCase: GetFiltersUseCase

    private val repository = Mockito.mock(EquipmentRepository::class.java)

    @Before
    fun setup() {
        useCase = GetFiltersUseCase(repository)
    }

    @Test
    fun execute_correctResourceType() = runBlocking {
        // Error
        val errorResource = Resource.Error<EquipmentResponse>("Error")
        `when`(repository.getFilters()).thenReturn(errorResource)
        assertThat(useCase.execute()).isEqualTo(errorResource)

        // Loading
        val loadingResource = Resource.Loading<EquipmentResponse>()
        `when`(repository.getFilters()).thenReturn(loadingResource)
        assertThat(useCase.execute()).isEqualTo(loadingResource)

        // Success
        val successResource = Resource.Success(EquipmentResponse())
        `when`(repository.getFilters()).thenReturn(successResource)
        assertThat(useCase.execute()).isEqualTo(successResource)
    }
}