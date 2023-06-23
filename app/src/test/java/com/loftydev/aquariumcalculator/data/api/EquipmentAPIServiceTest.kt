package com.loftydev.aquariumcalculator.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EquipmentAPIServiceTest {
    private lateinit var service: EquipmentAPIService
    private lateinit var server: MockWebServer

    private val filterMockFile = "filterresponse.json"

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EquipmentAPIService::class.java)
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
    fun getFilters_sentRequest_receivedExpected() {
        runBlocking {
            loadMockResponse(filterMockFile)
            val responseBody = service.getFilters().body()
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/data/filters")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            loadMockResponse(filterMockFile)
            val responseBody = service.getFilters().body()
            val filterList = responseBody!!
            val filter = filterList[0]

            assertThat(filter.ratedGallons).isEqualTo(5)
            assertThat(filter.type).isEqualTo("HOB")
            assertThat(filter.quantity).isEqualTo(1)
            assertThat(filter.productName).isEqualTo("Tetra Whisper IQ 10")
            assertThat(filter.imageLink).isEqualTo("https://m.media-amazon.com/test1.jpg")
            assertThat(filter.associatesLink).isEqualTo("https://amzn.to/test1")
        }
    }

}