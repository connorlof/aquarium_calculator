package com.loftydev.aquariumcalculator.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.model.UnitSystemType
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetUnitSettingsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getHeatersUseCase: GetHeatersUseCase,
    private val getUnitSettingsUseCase: GetUnitSettingsUseCase,
) : ViewModel() {

    val filters: MutableLiveData<Resource<EquipmentResponse>> = MutableLiveData()
    val heaters: MutableLiveData<Resource<EquipmentResponse>> = MutableLiveData()
    val volumeUnit: MutableLiveData<UnitSystemType> = MutableLiveData()

    fun reset() = viewModelScope.launch(Dispatchers.IO) {
        filters.postValue(Resource.Loading())
        heaters.postValue(Resource.Loading())
        loadUnitSettings()
    }
    private fun loadUnitSettings() = viewModelScope.launch(Dispatchers.IO) {
        getUnitSettingsUseCase.execute().collect {
            volumeUnit.postValue(it.volume)
        }
    }

    fun getFilters() = viewModelScope.launch(Dispatchers.IO) {
        filters.postValue(Resource.Loading())
        try{
            val apiResult = getFiltersUseCase.execute()
            filters.postValue(apiResult)
        } catch (e:Exception){
            filters.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getHeaters() = viewModelScope.launch(Dispatchers.IO) {
        heaters.postValue(Resource.Loading())
        try{
            val apiResult = getHeatersUseCase.execute()
            heaters.postValue(apiResult)
        } catch (e:Exception){
            heaters.postValue(Resource.Error(e.message.toString()))
        }
    }

}