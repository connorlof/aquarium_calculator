package com.loftydev.aquariumcalculator.data.repository

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.data.repository.datasource.UnitSettingsLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class UnitSettingsRepositoryImplTest {

    private lateinit var repository: UnitSettingsRepositoryImpl

    private val unitSettingsLocalDataSource = Mockito.mock(UnitSettingsLocalDataSource::class.java)

    @Before
    fun setup() {
        repository = UnitSettingsRepositoryImpl(unitSettingsLocalDataSource)
    }

    @Test
    fun getUnitSetting_correctValue() = runBlocking {
        val unitSystem = UnitSystem(0, UnitSystemType.METRIC, UnitSystemType.METRIC, UnitSystemType.METRIC)
        `when`(unitSettingsLocalDataSource.getUnitSetting()).thenReturn(
            flow { emit(listOf(unitSystem)) }
        )

        val setting = repository.getUnitSetting().firstOrNull()
        assertThat(setting).isNotNull()
        assertThat(setting!!.size).isEqualTo(1)
        assertThat(setting[0]).isEqualTo(unitSystem)
    }

    @Test
    fun getUnitSetting_noSettingStored() = runBlocking {
        val setting = repository.getUnitSetting()
        assertThat(setting).isNull()
    }
}