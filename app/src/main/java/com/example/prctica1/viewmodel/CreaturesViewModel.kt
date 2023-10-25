package com.example.prctica1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.prctica1.data.model.Character
import com.example.prctica1.data.remote.RetrofitClient

class YourViewModel : ViewModel() {

    private val token = "Bearer fdPKI_bVDia5WrB8EZcV"
    suspend fun fetchData(): List<Character>? {
        return try {
            val response = RetrofitClient.getCharacters(token)
            if (response.isSuccessful) {
                val charactersResponse = response.body()
                charactersResponse?.docs
            } else {
                // Handle error response
                null
            }
        } catch (e: Exception) {
            // Handle exception
            null
        }
    }
    suspend fun fetchCharacterById(characterId: String?): Character? {
        return try {
            val response = RetrofitClient.getCharacterById(token, characterId)
            if (response.isSuccessful) {
                val charactersResponse = response.body()
                val characterList = charactersResponse?.docs
                if (!characterList.isNullOrEmpty()) {
                    characterList[0]
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}


