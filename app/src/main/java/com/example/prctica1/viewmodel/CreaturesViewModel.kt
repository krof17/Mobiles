package com.example.prctica1.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prctica1.data.model.Character
import com.example.prctica1.data.model.CharactersResponse
import com.example.prctica1.data.remote.ApiService
import com.example.prctica1.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YourViewModel : ViewModel() {
    suspend fun fetchData(): List<Character>? {
        val token = "Bearer fdPKI_bVDia5WrB8EZcV"
        try {
            val response = RetrofitClient.getCharacters(token)
            if (response.isSuccessful) {
                val charactersResponse = response.body()
                return charactersResponse?.docs
            } else {
                // Handle error response
                return null
            }
        } catch (e: Exception) {
            // Handle exception
            return null
        }
    }
}


