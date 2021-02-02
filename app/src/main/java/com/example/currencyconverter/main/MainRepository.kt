package com.example.currencyconverter.main

import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.utlis.Resource

interface MainRepository {

    suspend fun getCurrencyRate(currencyBase : String) : Resource<CurrencyResponse>
}