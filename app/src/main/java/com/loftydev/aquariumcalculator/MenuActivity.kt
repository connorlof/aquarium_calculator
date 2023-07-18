package com.loftydev.aquariumcalculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.loftydev.aquariumcalculator.databinding.ActivityMenuBinding
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.EquipmentViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.MenuViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.StockingViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitConverterViewModelFactory
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitSettingsViewModel
import com.loftydev.aquariumcalculator.presentation.viewmodel.UnitSettingsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    @Inject
    lateinit var unitConverterFactory: UnitConverterViewModelFactory
    lateinit var unitConverterViewModel: UnitConverterViewModel

    @Inject
    lateinit var stockingFactory: StockingViewModelFactory
    lateinit var stockingViewModel: StockingViewModel

    @Inject
    lateinit var equipmentFactory: EquipmentViewModelFactory
    lateinit var equipmentViewModel: EquipmentViewModel

    @Inject
    lateinit var unitSettingsFactory: UnitSettingsViewModelFactory
    lateinit var unitSettingsViewModel: UnitSettingsViewModel

    val menuViewModel: MenuViewModel = MenuViewModel()

    private lateinit var binding: ActivityMenuBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_menu) as NavHostFragment
        navController = navHostFragment.navController
        binding.myToolbar.setupWithNavController(navController)

        equipmentViewModel = ViewModelProvider(this, equipmentFactory)[EquipmentViewModel::class.java]
        stockingViewModel = ViewModelProvider(this, stockingFactory)[StockingViewModel::class.java]
        unitConverterViewModel = ViewModelProvider(this, unitConverterFactory)[UnitConverterViewModel::class.java]
        unitSettingsViewModel = ViewModelProvider(this, unitSettingsFactory)[UnitSettingsViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.header_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                navigateToSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToSettings() {
        when (navController.currentDestination?.id) {
            R.id.EquipmentFragment -> {
                navController.navigate(R.id.action_EquipmentFragment_to_unitSettingsFragment)
            }
            R.id.unitConverterFragment -> {
                navController.navigate(R.id.action_unitConverterFragment_to_unitSettingsFragment)
            }
            R.id.stockingFragment -> {
                navController.navigate(R.id.action_stockingFragment_to_unitSettingsFragment)
            }
            R.id.MenuFragment -> {
                navController.navigate(R.id.action_MenuFragment_to_unitSettingsFragment)
            }
        }
    }
}