package com.example.myapplication.repository

import com.example.myapplication.api.CountryApi
import com.example.myapplication.data.model.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRepository {
    private val api: CountryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/peymano-wmt/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    suspend fun fetchCountries(): Result<List<Country>> {
        return try {
            val response = api.getCountries()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}