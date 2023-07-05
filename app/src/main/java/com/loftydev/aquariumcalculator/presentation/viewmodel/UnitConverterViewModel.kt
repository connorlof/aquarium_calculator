package com.loftydev.aquariumcalculator.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loftydev.aquariumcalculator.data.model.ConversionType
import com.loftydev.aquariumcalculator.domain.usecase.GetConversionUseCase
import com.loftydev.aquariumcalculator.domain.usecase.SwapConversionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UnitConverterViewModel(
    app: Application,
    private val getConversionUseCase: GetConversionUseCase,
    private val swapConversionUseCase: SwapConversionUseCase,
) : AndroidViewModel(app) {

    val output: MutableLiveData<Double> = MutableLiveData()
    val conversionType: MutableLiveData<ConversionType> = MutableLiveData()

    fun convert(
        input: Double,
        type: ConversionType
    ) = viewModelScope.launch(Dispatchers.IO) {
        output.postValue(getConversionUseCase.execute(input, type))
        conversionType.postValue(type)
    }

    fun swapUnits() = viewModelScope.launch(Dispatchers.IO) {
        conversionType.value?.let {
            conversionType.postValue(swapConversionUseCase.execute(it))
        }
    }

}