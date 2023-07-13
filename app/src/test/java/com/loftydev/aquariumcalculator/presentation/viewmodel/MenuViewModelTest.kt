package com.loftydev.aquariumcalculator.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MenuViewModelTest {

    private lateinit var viewModel: MenuViewModel

    @Before
    fun setUp() {
        viewModel = MenuViewModel()
    }

    @Test
    fun selectEmitsExpectedValue() {
        viewModel.select("myItem")
        assertThat(viewModel.lastSelectedItem.value).isEqualTo("myItem")
    }

}