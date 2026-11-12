package com.example.cardfolio_beta.view.screens.collection

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cardfolio_beta.view.components.SavedCardList

@Composable
fun CollectionScreen(cardIds: Set<String>, onRemove: (String) -> Unit, modifier: Modifier = Modifier) {
    SavedCardList("My Collection", cardIds, "Your collection is empty. Add cards from Catalog.", "Remove", onRemove, modifier)
}
