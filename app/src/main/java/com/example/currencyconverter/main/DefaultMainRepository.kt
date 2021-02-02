package com.example.currencyconverter.main

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.utlis.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val currencyApi: CurrencyApi

) : MainRepository{
    override suspend fun getCurrencyRate(currencyBase: String): Resource<CurrencyResponse> {
        return try {

            val response = currencyApi.getCurrencyRate(currencyBase)
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.Success(result)

            else
                Resource.Error(response.message())


        } catch (e : Exception){
            Resource.Error(e.message ?: "An error occured")
        }
    }
}