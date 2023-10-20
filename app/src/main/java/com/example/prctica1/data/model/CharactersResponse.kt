package com.example.prctica1.data.model

data class CharactersResponse(
    val docs: List<Character>,
    val total: Int,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int
)