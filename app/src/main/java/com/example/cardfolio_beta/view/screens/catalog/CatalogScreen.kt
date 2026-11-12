package com.example.cardfolio_beta.view.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cardfolio_beta.data.catalog.deltaReignCards
import com.example.cardfolio_beta.data.model.PokemonCard

private val Navy = Color(0xFF101827)
private val CardBackground = Color(0xFF1C293B)
private val Gold = Color(0xFFFFC857)
private val MutedText = Color(0xFF9CA8B8)

private val catalogCards = deltaReignCards

@Composable
fun CatalogScreen(\n    modifier: Modifier = Modifier,\n    collectionIds: Set<String> = emptySet(),\n    wishlistIds: Set<String> = emptySet(),\n    onAddToCollection: (String) -> Unit = {},\n    onRemoveFromCollection: (String) -> Unit = {},\n    onAddToWishlist: (String) -> Unit = {},\n    onRemoveFromWishlist: (String) -> Unit = {}\n) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedExpansion by remember { mutableStateOf("All") }
    var selectedType by remember { mutableStateOf("All") }
    var selectedRarity by remember { mutableStateOf("All") }
    var selectedCard by remember { mutableStateOf<PokemonCard?>(null) }

    if (selectedCard != null) {
        CardDetailScreen(
            card = selectedCard!!,
            onBack = { selectedCard = null },
            isInCollection = selectedCard!!.id in collectionIds,
            isInWishlist = selectedCard!!.id in wishlistIds,
            onCollectionClick = { if (selectedCard!!.id in collectionIds) onRemoveFromCollection(selectedCard!!.id) else onAddToCollection(selectedCard!!.id) },
            onWishlistClick = { if (selectedCard!!.id in wishlistIds) onRemoveFromWishlist(selectedCard!!.id) else onAddToWishlist(selectedCard!!.id) },
            modifier = modifier
        )
        return
    }

    val expansionOptions = listOf("All") + catalogCards.map { it.expansionName }.distinct()
    val typeOptions = listOf("All", "Grass", "Fire", "Water", "Lightning", "Psychic", "Fighting", "Darkness", "Metal", "Dragon", "Colorless")
    val rarityOptions = listOf("All") + catalogCards.map { it.rarity }.distinct()

    val filteredCards = catalogCards.filter { card ->
        val matchesSearch = card.name.contains(searchQuery, ignoreCase = true)
        val matchesExpansion = selectedExpansion == "All" || card.expansionName == selectedExpansion
        val matchesType = selectedType == "All" || selectedType in card.types
        val matchesRarity = selectedRarity == "All" || card.rarity == selectedRarity
        matchesSearch && matchesExpansion && matchesType && matchesRarity
    }

    Column(
        modifier = modifier.fillMaxSize().background(Navy).padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        Text("CARD CATALOG", color = Gold, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 3.sp)
        Spacer(Modifier.height(8.dp))
        Text("Explore Cards", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("Find your next favorite card.", color = MutedText, fontSize = 14.sp)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search Pokémon or card...") },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Gold,
                unfocusedBorderColor = MutedText,
                focusedPlaceholderColor = MutedText,
                unfocusedPlaceholderColor = MutedText,
                cursorColor = Gold
            )
        )

        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CatalogFilter("Expansion", expansionOptions, selectedExpansion, { selectedExpansion = it }, Modifier.weight(1f))
            CatalogFilter("Type", typeOptions, selectedType, { selectedType = it }, Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        CatalogFilter("Rarity", rarityOptions, selectedRarity, { selectedRarity = it }, Modifier.fillMaxWidth())

        Spacer(Modifier.height(20.dp))
        Text("${filteredCards.size} cards found", color = MutedText, fontSize = 13.sp)
        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(filteredCards, key = { it.id }) { card ->
                CatalogCardItem(card = card, onClick = { selectedCard = card })
            }
        }
    }
}

@Composable
private fun CatalogFilter(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            Text("$label: $selected", color = Gold, fontSize = 12.sp, maxLines = 1)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun CatalogCardItem(card: PokemonCard, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = card.imageUrl,
                contentDescription = card.name,
                modifier = Modifier.size(width = 76.dp, height = 106.dp).background(Navy, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(card.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(5.dp))
                Text("${card.expansionCode} #${card.cardNumber}", color = MutedText, fontSize = 12.sp)
                Text(card.category, color = MutedText, fontSize = 12.sp)
                Text(card.rarity, color = Gold, fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun CardDetailScreen(
    card: PokemonCard,
    onBack: () -> Unit,
    isInCollection: Boolean,
    isInWishlist: Boolean,
    onCollectionClick: () -> Unit,
    onWishlistClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(Navy).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("← Back to Catalog", color = Gold) }
        }
        Spacer(Modifier.height(12.dp))
        AsyncImage(
            model = card.imageUrl,
            contentDescription = card.name,
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(16.dp))
        Text(card.name, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("${card.expansionName} • ${card.expansionCode} #${card.cardNumber}", color = MutedText)
        Spacer(Modifier.height(8.dp))
        Text(card.category, color = MutedText)
        Text(card.types.joinToString().ifBlank { "Elemental type not verified" }, color = Gold)
        Text(card.rarity, color = Gold, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(20.dp))
        Button(onClick = onCollectionClick, modifier = Modifier.fillMaxWidth()) {
            Text(if (isInCollection) "Remove from Collection" else "Add to Collection")
        }
        OutlinedButton(onClick = onWishlistClick, modifier = Modifier.fillMaxWidth()) {
            Text(if (isInWishlist) "Remove from Wishlist" else "Add to Wishlist")
        }
        Spacer(Modifier.height(24.dp))
    }
}
