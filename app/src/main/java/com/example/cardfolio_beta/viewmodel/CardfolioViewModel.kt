package com.example.cardfolio_beta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cardfolio_beta.data.repository.CardListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardfolioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CardListRepository(application)

    private val _collectionIds = MutableStateFlow<Set<String>>(emptySet())
    val collectionIds: StateFlow<Set<String>> = _collectionIds.asStateFlow()

    private val _wishlistIds = MutableStateFlow<Set<String>>(emptySet())
    val wishlistIds: StateFlow<Set<String>> = _wishlistIds.asStateFlow()

    init {
        viewModelScope.launch {
            val saved = withContext(Dispatchers.IO) {
                repository.loadCollection() to repository.loadWishlist()
            }
            _collectionIds.value = saved.first
            _wishlistIds.value = saved.second
        }
    }

    fun addToCollection(cardId: String) = updateCollection(cardId, true)
    fun removeFromCollection(cardId: String) = updateCollection(cardId, false)
    fun addToWishlist(cardId: String) = updateWishlist(cardId, true)
    fun removeFromWishlist(cardId: String) = updateWishlist(cardId, false)

    private fun updateCollection(cardId: String, add: Boolean) {
        viewModelScope.launch {
            val updated = withContext(Dispatchers.IO) {
                if (add) repository.addToCollection(cardId)
                else repository.removeFromCollection(cardId)
            }
            _collectionIds.value = updated
        }
    }

    private fun updateWishlist(cardId: String, add: Boolean) {
        viewModelScope.launch {
            val updated = withContext(Dispatchers.IO) {
                if (add) repository.addToWishlist(cardId)
                else repository.removeFromWishlist(cardId)
            }
            _wishlistIds.value = updated
        }
    }
}
