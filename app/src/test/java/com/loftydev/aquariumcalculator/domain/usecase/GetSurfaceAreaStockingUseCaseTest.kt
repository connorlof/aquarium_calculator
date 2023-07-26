package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import org.junit.Test

class GetSurfaceAreaStockingUseCaseTest {

    private val useCase = GetSurfaceAreaStockingUseCase()

    @Test
    fun correctValue_execute() {
        assertThat(useCase.execute(0.0, 288.0,  UnitSystemType.IMPERIAL)).isEqualTo(0.0)
        assertThat(useCase.execute(10.0, 288.0,  UnitSystemType.IMPERIAL)).isEqualTo(0.4166666666666667)
        assertThat(useCase.execute(20.0, 288.0,  UnitSystemType.IMPERIAL)).isEqualTo(0.8333333333333334)
        assertThat(useCase.execute(30.0, 288.0,  UnitSystemType.IMPERIAL)).isEqualTo(1.25)
    }

}