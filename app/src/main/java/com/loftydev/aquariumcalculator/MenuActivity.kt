package com.loftydev.aquariumcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.loftydev.aquariumcalculator.databinding.ActivityMenuBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModelFactory

class MenuActivity : AppCompatActivity() {

    private lateinit var factory: MenuViewModelFactory
    private lateinit var viewModel: MenuViewModel

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_menu) as NavHostFragment

        viewModel = ViewModelProvider(this, factory)[MenuViewModel::class.java]
    }
}