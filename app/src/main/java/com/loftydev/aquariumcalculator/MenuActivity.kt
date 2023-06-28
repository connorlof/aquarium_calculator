package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.loftydev.aquariumcalculator.databinding.ActivityMenuBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: EquipmentViewModelFactory
    lateinit var viewModel: EquipmentViewModel

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[EquipmentViewModel::class.java]
    }
}