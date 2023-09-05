package com.loftydev.aquariumcalculator.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.UnitSystem
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnitSettingsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: UnitSettingsDao
    private lateinit var database: AquariumCalculatorDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AquariumCalculatorDatabase::class.java
        ).build()
        dao = database.getUnitSettingsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertSelectReturnExpected() = runBlocking{
        val unitSetting = unitSetting(UnitSystemType.IMPERIAL, UnitSystemType.METRIC, UnitSystemType.METRIC)
        dao.insert(unitSetting)

        val savedSetting = dao.getUnitSetting().map { it.last() }.last()
        assertThat(unitSetting).isEqualTo(savedSetting)
    }

    private fun unitSetting(
        temp: UnitSystemType = UnitSystemType.METRIC,
        vol: UnitSystemType = UnitSystemType.METRIC,
        dist: UnitSystemType = UnitSystemType.METRIC
    ) = UnitSystem(
        temperature = temp,
        volume = vol,
        distance = dist
    )

}