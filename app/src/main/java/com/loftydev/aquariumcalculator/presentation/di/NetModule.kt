package com.loftydev.aquariumcalculator.presentation.di

import com.loftydev.aquariumcalculator.BuildConfig
import com.loftydev.aquariumcalculator.data.api.EquipmentAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideEquipmentAPIService(retrofit: Retrofit): EquipmentAPIService {
        return retrofit.create(EquipmentAPIService::class.java)
    }

}