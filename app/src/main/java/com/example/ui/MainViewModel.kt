package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Country
import com.example.myapplication.repository.CountryRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countries = MutableLiveData<Result<List<Country>>>()
    val countries: LiveData<Result<List<Country>>> get() = _countries

    fun loadCountries() {
        viewModelScope.launch {
            _countries.value = repository.fetchCountries()
        }
    }
}