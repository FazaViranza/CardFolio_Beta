package com.example.cardfolio_beta.data.repository

import android.content.Context
import org.json.JSONArray

class CardListRepository(context: Context) {
    private val appContext = context.applicationContext
    private val collectionFile = "cardfolio_collection.json"
    private val wishlistFile = "cardfolio_wishlist.json"

    fun loadCollection(): Set<String> = readIds(collectionFile)
    fun loadWishlist(): Set<String> = readIds(wishlistFile)

    fun addToCollection(cardId: String): Set<String> =
        loadCollection().toMutableSet().apply { add(cardId) }.also { writeIds(collectionFile, it) }

    fun removeFromCollection(cardId: String): Set<String> =
        loadCollection().toMutableSet().apply { remove(cardId) }.also { writeIds(collectionFile, it) }

    fun addToWishlist(cardId: String): Set<String> =
        loadWishlist().toMutableSet().apply { add(cardId) }.also { writeIds(wishlistFile, it) }

    fun removeFromWishlist(cardId: String): Set<String> =
        loadWishlist().toMutableSet().apply { remove(cardId) }.also { writeIds(wishlistFile, it) }

    private fun readIds(fileName: String): Set<String> {
        return try {
            val json = appContext.openFileInput(fileName).bufferedReader().use { it.readText() }
            val array = JSONArray(json)
            buildSet {
                for (index in 0 until array.length()) add(array.getString(index))
            }
        } catch (_: Exception) {
            emptySet()
        }
    }

    private fun writeIds(fileName: String, ids: Set<String>) {
        val array = JSONArray()
        ids.sorted().forEach(array::put)
        appContext.openFileOutput(fileName, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(array.toString())
        }
    }
}
