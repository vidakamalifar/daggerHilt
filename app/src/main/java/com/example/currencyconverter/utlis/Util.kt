package com.example.currencyconverter.utlis

import com.example.currencyconverter.data.models.Rates

class Util {
    companion object{

         fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
            "CAD" -> rates.CAD
            "HKD" -> rates.HKD
            "ISK" -> rates.ISK
            "EUR" -> rates.EUR
            "PHP" -> rates.PHP
            "DKK" -> rates.DKK
            "HUF" -> rates.HUF
            "CZK" -> rates.CZK
            "AUD" -> rates.AUD
            "RON" -> rates.RON
            "SEK" -> rates.SEK
            "IDR" -> rates.IDR
            "INR" -> rates.INR
            "BRL" -> rates.BRL
            "RUB" -> rates.RUB
            "HRK" -> rates.HRK
            "JPY" -> rates.JPY
            "THB" -> rates.THB
            "CHF" -> rates.CHF
            "SGD" -> rates.SGD
            "PLN" -> rates.PLN
            "BGN" -> rates.BGN
            "CNY" -> rates.CNY
            "NOK" -> rates.NOK
            "NZD" -> rates.NZD
            "ZAR" -> rates.ZAR
            "USD" -> rates.USD
            "MXN" -> rates.MXN
            "ILS" -> rates.ILS
            "GBP" -> rates.GBP
            "KRW" -> rates.KRW
            "MYR" -> rates.MYR
            else -> null
        }
    }
}