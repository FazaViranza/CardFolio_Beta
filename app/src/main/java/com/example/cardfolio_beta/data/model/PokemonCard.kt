package com.example.cardfolio_beta.data.model

data class PokemonCard(
    val id: String,
    val name: String,
    val expansionCode: String,
    val expansionName: String,
    val cardNumber: Int,
    val category: String,
    val types: List<String> = emptyList(),
    val rarity: String,
    val imageUrl: String? = null
)
