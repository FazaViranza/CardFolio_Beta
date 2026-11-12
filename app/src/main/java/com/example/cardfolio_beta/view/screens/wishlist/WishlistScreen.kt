package com.example.cardfolio_beta.view.screens.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cardfolio_beta.view.components.SavedCardList

@Composable
fun WishlistScreen(cardIds: Set<String>, onRemove: (String) -> Unit, modifier: Modifier = Modifier) {
    SavedCardList("My Wishlist", cardIds, "Your wishlist is empty. Save cards you want to find.", "Remove", onRemove, modifier)
}
