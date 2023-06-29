package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.data.model.FilterResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(
    app: Application,
    private val getFiltersUseCase: GetFiltersUseCase,
) : AndroidViewModel(app) {

    val filters: MutableLiveData<Resource<FilterResponse>> = MutableLiveData()

    fun getFilters() = viewModelScope.launch(Dispatchers.IO) {
        filters.postValue(Resource.Loading())
        try{
            val apiResult = getFiltersUseCase.execute()
            filters.postValue(apiResult)
            // TODO: Save filters locally upon first API call
        } catch (e:Exception){
            filters.postValue(Resource.Error(e.message.toString()))
        }
    }

}