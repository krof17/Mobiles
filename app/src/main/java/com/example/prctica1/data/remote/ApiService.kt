package com.example.prctica1.data.remote

import com.example.prctica1.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Header("Authorization") token: String): Response<CharactersResponse>
}