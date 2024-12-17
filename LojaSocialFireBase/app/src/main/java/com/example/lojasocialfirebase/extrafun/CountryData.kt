package com.example.lojasocialfirebase.extrafun

import com.example.lojasocialfirebase.R

data class Country(
    val code: String,
    val name: String,
    val flag: Int  // ID do recurso da bandeira
)

object CountryData {
    val countries = listOf(
        Country(code = "LB", name = "Líbano", flag = R.drawable.libano),
        Country(code = "BR", name = "Brasil", flag = R.drawable.brazil),
        Country(code = "MR", name = "Marrocos", flag = R.drawable.marrocos),
        Country(code = "SR", name = "Síria", flag = R.drawable.siria),
        Country(code = "PT", name = "Portugal", flag = R.drawable.portugal),
        Country(code = "UC", name = "Ucrânia", flag = R.drawable.ucrania),
        Country(code = "RS", name = "Rússia", flag = R.drawable.russia)
    )

    fun getCountryByCode(code: String): Country? {
        return countries.find { it.code.equals(code, ignoreCase = true) }
    }
}
