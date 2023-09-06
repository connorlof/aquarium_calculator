package com.loftydev.aquariumcalculator.data.repository

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.model.EquipmentResponseItem
import com.loftydev.aquariumcalculator.data.repository.datasource.EquipmentRemoteDataSource
import com.loftydev.aquariumcalculator.data.util.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

class EquipmentRepositoryImplTest {

    private lateinit var repository: EquipmentRepositoryImpl

    private val equipmentRemoteDataSource = Mockito.mock(EquipmentRemoteDataSource::class.java)

    private val emptyBodyErrorMessage = "OK"
    private val unsuccessfulErrorMessage = "Response.error()"
    private val filterResponseItem = EquipmentResponseItem(
        associatesLink = "https://amzn.to/test1",
        imageLink = "https://m.media-amazon.com/test1.jpg",
        productName = "Tetra Whisper IQ 10",
        quantity = "1",
        ratedGallons = 5,
        type = "HOB"
    )
    private val heaterResponseItem = EquipmentResponseItem(
        associatesLink = "https://amzn.to/test2",
        imageLink = "https://m.media-amazon.com/test2.jpg",
        productName = "HiTauing Aquarium Heater",
        quantity = "1",
        ratedGallons = 10,
        type = "50W"
    )

    @Before
    fun setup() {
        repository = EquipmentRepositoryImpl(equipmentRemoteDataSource)
    }

    @Test
    fun getFilters_correctValue() = runBlocking {
        val equipmentResponse = EquipmentResponse()
        equipmentResponse.add(filterResponseItem)
        `when`(equipmentRemoteDataSource.getFilters()).thenReturn(Response.success(equipmentResponse))

        val resource = repository.getFilters()
        assertThat(resource).isInstanceOf(Resource.Success::class.java)
        assertThat(resource.data).isEqualTo(equipmentResponse)
    }

    @Test
    fun getFilters_emptyBody_returnError() = runBlocking {
        `when`(equipmentRemoteDataSource.getFilters()).thenReturn(Response.success(null))

        val resource = repository.getFilters()
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat(resource.message).isEqualTo(emptyBodyErrorMessage)
    }

    @Test
    fun getFilters_responseNotSuccessful_returnError() = runBlocking {
        `when`(equipmentRemoteDataSource.getFilters()).thenReturn(Response.error(500, String().toResponseBody()))

        val resource = repository.getFilters()
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat(resource.message).isEqualTo(unsuccessfulErrorMessage)
    }

    @Test
    fun getHeaters_correctValue() = runBlocking {
        val equipmentResponse = EquipmentResponse()
        equipmentResponse.add(heaterResponseItem)
        `when`(equipmentRemoteDataSource.getHeaters()).thenReturn(Response.success(equipmentResponse))

        val resource = repository.getHeaters()
        assertThat(resource).isInstanceOf(Resource.Success::class.java)
        assertThat(resource.data).isEqualTo(equipmentResponse)
    }

    @Test
    fun getHeaters_emptyBody_returnError() = runBlocking {
        `when`(equipmentRemoteDataSource.getHeaters()).thenReturn(Response.success(null))

        val resource = repository.getHeaters()
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat(resource.message).isEqualTo(emptyBodyErrorMessage)
    }

    @Test
    fun getHeaters_responseNotSuccessful_returnError() = runBlocking {
        `when`(equipmentRemoteDataSource.getHeaters()).thenReturn(Response.error(500, String().toResponseBody()))

        val resource = repository.getHeaters()
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat(resource.message).isEqualTo(unsuccessfulErrorMessage)
    }

}