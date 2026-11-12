package com.example.cardfolio_beta.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cardfolio_beta.data.catalog.deltaReignCards

private val Navy = Color(0xFF101827)
private val CardBackground = Color(0xFF1C293B)
private val Gold = Color(0xFFFFC857)
private val MutedText = Color(0xFF9CA8B8)

@Composable
fun SavedCardList(
    title: String,
    cardIds: Set<String>,
    emptyMessage: String,
    removeLabel: String,
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val cards = deltaReignCards.filter { it.id in cardIds }
    Column(modifier = modifier.fillMaxSize().background(Navy).padding(horizontal = 20.dp)) {
        Spacer(Modifier.height(24.dp))
        Text(title.uppercase(), color = Gold, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 3.sp)
        Spacer(Modifier.height(8.dp))
        Text("${cards.size} cards", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(18.dp))
        if (cards.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(emptyMessage, color = MutedText)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 24.dp)) {
                items(cards, key = { it.id }) { card ->
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(model = card.imageUrl, contentDescription = card.name, modifier = Modifier.size(width = 64.dp, height = 90.dp).background(Navy, RoundedCornerShape(8.dp)), contentScale = ContentScale.Fit)
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(card.name, color = Color.White, fontWeight = FontWeight.Bold)
                                Text("${card.expansionCode} #${card.cardNumber}", color = MutedText, fontSize = 12.sp)
                                Text(card.rarity, color = Gold, fontSize = 11.sp)
                            }
                            TextButton(onClick = { onRemove(card.id) }) { Text(removeLabel, color = Gold, fontSize = 11.sp) }
                        }
                    }
                }
            }
        }
    }
}
