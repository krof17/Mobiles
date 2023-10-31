package com.example.prctica1.data.remote

import com.example.prctica1.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("character?name=Gandalf,Gollum,Saruman,Aragorn II Elessar,Boromir,Legolas,Arwen,Gimli,Treebeard,Thorin II Oakenshield," +
            "Smaug,Azog,Shelob,Eru Ilúvatar")
    suspend fun getCharacters(
        @Header("Authorization") token: String
    ): Response<CharactersResponse>

    @GET("character")
    suspend fun getAllCharacters(
        @Header("Authorization") token: String
    ): Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Header("Authorization") token: String,
        @Path("id") characterId: String?
    ): Response<CharactersResponse>
}