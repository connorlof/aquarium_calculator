package com.loftydev.aquariumcalculator.data.repository.datasourceimpl

import com.loftydev.aquariumcalculator.data.api.EquipmentAPIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EquipmentRemoteDataSourceImplTest {

    private lateinit var dataSource: EquipmentRemoteDataSourceImpl
    private lateinit var service: EquipmentAPIService
    private lateinit var server: MockWebServer

    private val filterMockFile = "filterresponse.json"
    private val heaterMockFile = "heaterresponse.json"

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EquipmentAPIService::class.java)

        dataSource = EquipmentRemoteDataSourceImpl(service)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun loadMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getFilters_correctValue() {
        runBlocking {
            loadMockResponse(filterMockFile)
            val response = dataSource.getFilters()
            assertThat(response.isSuccessful).isTrue()

            val filters = response.body()
            assertThat(filters).isNotNull()
            assertThat(filters!!.size).isEqualTo(2)

            val filter = filters[0]
            assertThat(filter.ratedGallons).isEqualTo(5)
            assertThat(filter.type).isEqualTo("HOB")
            assertThat(filter.quantity).isEqualTo("1")
            assertThat(filter.productName).isEqualTo("Tetra Whisper IQ 10")
            assertThat(filter.imageLink).isEqualTo("https://m.media-amazon.com/test1.jpg")
            assertThat(filter.associatesLink).isEqualTo("https://amzn.to/test1")
        }
    }

    @Test
    fun getHeaters_correctValue() {
        runBlocking {
            loadMockResponse(heaterMockFile)
            val response = dataSource.getHeaters()
            assertThat(response.isSuccessful).isTrue()

            val heaters = response.body()
            assertThat(heaters).isNotNull()
            assertThat(heaters!!.size).isEqualTo(3)

            val heater = heaters[2]
            assertThat(heater.ratedGallons).isEqualTo(10)
            assertThat(heater.type).isEqualTo("50W")
            assertThat(heater.quantity).isEqualTo("1")
            assertThat(heater.productName).isEqualTo("Hitop Adjustable Heater")
            assertThat(heater.imageLink).isEqualTo("https://m.media-amazon.com/test3.jpg")
            assertThat(heater.associatesLink).isEqualTo("https://amzn.to/test3")
        }
    }
}