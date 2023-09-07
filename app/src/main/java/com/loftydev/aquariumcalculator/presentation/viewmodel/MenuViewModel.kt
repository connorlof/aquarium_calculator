package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    val lastSelectedItem = MutableLiveData<String>()

    fun select(item: String) {
        lastSelectedItem.value = item
    }

}