package com.example.lojasocialbd.factories

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lojasocialbd.viewmodels.LoginViewModel

class LoginViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(application) as T // Use Application context
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}