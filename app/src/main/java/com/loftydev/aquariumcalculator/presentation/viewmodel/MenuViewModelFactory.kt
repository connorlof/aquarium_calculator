package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase

class MenuViewModelFactory(
    private val app: Application,
    private val getFiltersUseCase: GetFiltersUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuViewModel(
            app,
            getFiltersUseCase
        ) as T
    }
}
