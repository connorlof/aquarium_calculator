package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.databinding.ActivityMenuBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    @Inject
    lateinit var unitConverterFactory: UnitConverterViewModelFactory
    lateinit var unitConverterViewModel: UnitConverterViewModel

    @Inject
    lateinit var equipmentFactory: EquipmentViewModelFactory
    lateinit var equipmentViewModel: EquipmentViewModel

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        equipmentViewModel = ViewModelProvider(this, equipmentFactory)[EquipmentViewModel::class.java]
        unitConverterViewModel = ViewModelProvider(this, unitConverterFactory)[UnitConverterViewModel::class.java]
    }
}