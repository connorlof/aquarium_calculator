package com.loftydev.aquariumcalculator.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import org.junit.Test

class GetInchPerGallonUseCaseTest {

    private val useCase = GetInchPerGallonUseCase()

    @Test
    fun correctValue_execute() {
        assertThat(useCase.execute(0.0, UnitSystemType.IMPERIAL,20.0, UnitSystemType.IMPERIAL))
            .isEqualTo(0.0)
        assertThat(useCase.execute(10.0, UnitSystemType.IMPERIAL, 20.0,  UnitSystemType.IMPERIAL))
            .isEqualTo(0.5)
        assertThat(useCase.execute(20.0, UnitSystemType.IMPERIAL, 20.0,  UnitSystemType.IMPERIAL,))
            .isEqualTo(1.0)
        assertThat(useCase.execute(30.0, UnitSystemType.IMPERIAL, 20.0,  UnitSystemType.IMPERIAL,))
            .isEqualTo(1.5)
    }

}