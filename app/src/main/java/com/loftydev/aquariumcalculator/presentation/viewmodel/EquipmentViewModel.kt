package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.data.model.EquipmentResponse
import com.loftydev.aquariumcalculator.data.util.Resource
import com.loftydev.aquariumcalculator.domain.usecase.GetFiltersUseCase
import com.loftydev.aquariumcalculator.domain.usecase.GetHeatersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(
    app: Application,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val getHeatersUseCase: GetHeatersUseCase,
) : AndroidViewModel(app) {

    val filters: MutableLiveData<Resource<EquipmentResponse>> = MutableLiveData()
    val heaters: MutableLiveData<Resource<EquipmentResponse>> = MutableLiveData()

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