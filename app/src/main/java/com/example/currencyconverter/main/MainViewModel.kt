package com.example.currencyconverter.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.utlis.DispatcherProvider
import com.example.currencyconverter.utlis.Resource
import com.example.currencyconverter.utlis.Util
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Math.round

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val coroutineDispatcher: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent {

        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object EmptySate : CurrencyEvent()
    }

    private val _convention = MutableStateFlow<CurrencyEvent>(CurrencyEvent.EmptySate)
    val convention: StateFlow<CurrencyEvent> = _convention

    fun convert(
        amount: String,
        fromCurrency: String,
        toCurrency: String
    ) {
        val fromAmount = amount.toFloatOrNull()
        if (fromAmount == null) {
            _convention.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(coroutineDispatcher.io) {
            _convention.value = CurrencyEvent.Loading

            when (val response = repository.getCurrencyRate(fromCurrency)) {

                is Resource.Error -> _convention.value = CurrencyEvent.Failure(response.message!!)
                is Resource.Success -> {

                    val rates = response.data!!.rates
                    val rate = Util.getRateForCurrency(toCurrency, rates)

                    if (rate == null) {
                        _convention.value = CurrencyEvent.Failure("Error")

                    } else {
                        val convertedCurrency = round(fromAmount * rate * 100) /100
                        _convention.value = CurrencyEvent.Success(
                            "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                        )
                    }
                }
            }
        }

    }
}