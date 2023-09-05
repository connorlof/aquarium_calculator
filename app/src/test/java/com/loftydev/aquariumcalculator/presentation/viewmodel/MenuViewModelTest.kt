package com.loftydev.aquariumcalculator.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

class MenuViewModelTest {

    private lateinit var viewModel: MenuViewModel

    @JvmField
    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

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