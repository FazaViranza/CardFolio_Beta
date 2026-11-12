package com.example.cardfolio_beta.view.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardfolio_beta.data.model.PokemonCard

private val Navy = Color(0xFF101827)
private val CardBackground = Color(0xFF1C293B)
private val Gold = Color(0xFFFFC857)
private val MutedText = Color(0xFF9CA8B8)

// Verified sample records. The remaining set records will be added from the checklist.
private val sampleCards = listOf(
    PokemonCard("DLR-001", "Heracross", "DLR", "Delta Reign", 1, "Pokemon", listOf("Grass"), "Common"),
    PokemonCard("DLR-002", "Surskit", "DLR", "Delta Reign", 2, "Pokemon", listOf("Grass"), "Common"),
    PokemonCard("DLR-003", "Masquerain", "DLR", "Delta Reign", 3, "Pokemon", listOf("Grass"), "Common")
)

@Composable
fun CatalogScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedExpansion by remember { mutableStateOf("All") }
    var selectedType by remember { mutableStateOf("All") }
    var selectedRarity by remember { mutableStateOf("All") }

    val expansionOptions = listOf("All") + sampleCards.map { it.expansionName }.distinct()
    val typeOptions = listOf("All") + sampleCards.flatMap { it.types }.distinct()
    val rarityOptions = listOf("All") + sampleCards.map { it.rarity }.distinct()

    val filteredCards = sampleCards.filter { card ->
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
            placeholder = { Text("Search Pokémon...") },
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
            items(filteredCards, key = { it.id }) { card -> CatalogCardItem(card) }
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
private fun CatalogCardItem(card: PokemonCard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(76.dp).background(Navy, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("${card.expansionCode} #${card.cardNumber}", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text(card.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(5.dp))
                Text(card.expansionName, color = MutedText, fontSize = 12.sp)
                val typeLabel = card.types.joinToString().ifBlank { card.category }
                Text("$typeLabel • ${card.rarity}", color = Gold, fontSize = 11.sp)
            }
        }
    }
}
