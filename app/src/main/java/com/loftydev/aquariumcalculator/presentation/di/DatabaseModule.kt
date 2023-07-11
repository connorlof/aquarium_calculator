package com.loftydev.aquariumcalculator.presentation.di

import android.app.Application
import androidx.room.Room
import com.loftydev.aquariumcalculator.data.db.AquariumCalculatorDatabase
import com.loftydev.aquariumcalculator.data.db.UnitSettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAquariumCalculatorDatabase(app: Application): AquariumCalculatorDatabase {
        return Room.databaseBuilder(app, AquariumCalculatorDatabase::class.java, "aquarium_calculator_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUnitSettingsDao(aquariumCalculatorDatabase: AquariumCalculatorDatabase): UnitSettingsDao {
        return aquariumCalculatorDatabase.getUnitSettingsDao()
    }

}