package com.loftydev.aquariumcalculator.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

class MenuViewModelTest {

    private val viewModel = MenuViewModel()

    @JvmField
    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun select_itemSetAsLastSelected() {
        viewModel.select("myItem1")
        assertThat(viewModel.lastSelectedItem.value).isEqualTo("myItem1")

        viewModel.select("myItem2")
        assertThat(viewModel.lastSelectedItem.value).isEqualTo("myItem2")

        viewModel.select("myItem1")
        assertThat(viewModel.lastSelectedItem.value).isEqualTo("myItem1")
    }

    @Test
    fun lastSelectedItem_neverSelected_lastSelectedIsNull() {
        assertThat(viewModel.lastSelectedItem.value).isNull()
    }
}