
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

private val Navy = Color(0xFF101827)
private val CardBackground = Color(0xFF1C293B)
private val Gold = Color(0xFFFFC857)
private val MutedText = Color(0xFF9CA8B8)

private data class CatalogCard(
    val name: String,
    val expansion: String,
    val number: String,
    val type: String,
    val rarity: String
)

private val sampleCards = listOf(
    CatalogCard("Heracross", "Delta Reign", "DLR #1", "Grass", "Common"),
    CatalogCard("Surskit", "Delta Reign", "DLR #2", "Grass", "Common"),
    CatalogCard("Masquerain", "Delta Reign", "DLR #3", "Grass", "Common")
)

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("All") }
    var selectedRarity by remember { mutableStateOf("All") }

    val filteredCards = sampleCards.filter { card ->
        val matchesSearch =
            card.name.contains(searchQuery, ignoreCase = true)

        val matchesType =
            selectedType == "All" || card.type == selectedType

        val matchesRarity =
            selectedRarity == "All" || card.rarity == selectedRarity

        matchesSearch && matchesType && matchesRarity
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Navy)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            text = "CARD CATALOG",
            color = Gold,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Explore Cards",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Find your next favorite card.",
            color = MutedText,
            fontSize = 14.sp
        )

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

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CatalogFilter(
                label = "Type",
                options = listOf("All", "Grass"),
                selected = selectedType,
                onSelected = { selectedType = it },
                modifier = Modifier.weight(1f)
            )

            CatalogFilter(
                label = "Rarity",
                options = listOf("All", "Common"),
                selected = selectedRarity,
                onSelected = { selectedRarity = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "${filteredCards.size} cards found",
            color = MutedText,
            fontSize = 13.sp
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(filteredCards) { card ->
                CatalogCardItem(card)
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
            Text(
                text = "$label: $selected",
                color = Gold,
                fontSize = 12.sp
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
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
private fun CatalogCardItem(card: CatalogCard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .background(Navy, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = card.number,
                    color = Gold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(14.dp))

            Column {
                Text(
                    text = card.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = card.expansion,
                    color = MutedText,
                    fontSize = 12.sp
                )

                Text(
                    text = "${card.type} • ${card.rarity}",
                    color = Gold,
                    fontSize = 11.sp
                )
            }
        }
    }
}
