package com.example.cardfolio_beta.data.model

data class Expansion(
    val code: String,
    val name: String,
    val releaseDate: String? = null,
    val totalCards: Int
)
