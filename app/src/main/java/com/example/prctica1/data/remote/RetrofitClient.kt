package com.example.prctica1.data.remote

import com.example.prctica1.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "https://the-one-api.dev/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = RetrofitClient.retrofit.create(ApiService::class.java)

    suspend fun getCharacters(token: String): Response<CharactersResponse> {
        return apiService.getCharacters(token)
    }
    suspend fun getAllCharacters(token: String): Response<CharactersResponse> {
        return apiService.getAllCharacters(token)
    }
    suspend fun getCharacterById(token: String, characterId: String?): Response<CharactersResponse> {
        return apiService.getCharacterById(token, characterId)
    }
}