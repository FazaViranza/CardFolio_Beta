
package com.example.cardfolio_beta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.cardfolio_beta.ui.theme.CardFolio_BetaTheme
import com.example.cardfolio_beta.view.screens.catalog.CatalogScreen

private val Navy = Color(0xFF101827)
private val CardBackground = Color(0xFF1C293B)
private val Gold = Color(0xFFFFC857)
private val MutedText = Color(0xFF9CA8B8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardFolio_BetaTheme {
                CardfolioApp()
            }
        }
    }
}

@Composable
fun CardfolioApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Navy,
        bottomBar = {
            NavigationBar(
                containerColor = CardBackground,
                contentColor = Gold
            ) {
                val tabs = listOf("Home", "Catalog", "Collection", "Wishlist", "Profile")

                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Text(
                                text = listOf("⌂", "⌕", "▣", "♡", "●")[index],
                                fontSize = 20.sp
                            )
                        },
                        label = { Text(title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Gold,
                            selectedTextColor = Gold,
                            unselectedIconColor = MutedText,
                            unselectedTextColor = MutedText,
                            indicatorColor = Navy
                        )
                    )
                }
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> DashboardScreen(Modifier.padding(padding))
            1 -> CatalogScreen(Modifier.padding(padding))
            2 -> SimplePlaceholder("My Collection", Modifier.padding(padding))
            3 -> SimplePlaceholder("My Wishlist", Modifier.padding(padding))
            4 -> SimplePlaceholder("My Profile", Modifier.padding(padding))
        }
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column {
                Text(
                    text = "CARDFOLIO",
                    color = Gold,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Your Collection,",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Your Story.",
                    color = Gold,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Welcome back, Collector!",
                    color = MutedText,
                    fontSize = 14.sp
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardBackground
                )
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = "YOUR COLLECTION",
                        color = MutedText,
                        fontSize = 12.sp,
                        letterSpacing = 2.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "128 Cards",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Your collection journey starts here.",
                        color = MutedText,
                        fontSize = 13.sp
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Wishlist",
                    value = "24",
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    title = "Expansion Sets",
                    value = "8",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Text(
                text = "Recently Added",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(
            listOf(
                CardItem("Charizard ex", "Obsidian Flames", "Special Illustration Rare"),
                CardItem("Pikachu", "Surging Sparks", "Illustration Rare"),
                CardItem("Mewtwo", "Pokémon GO", "Holo Rare")
            )
        ) { card ->
            CollectionCard(card)
        }

        item {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "CARDFOLIO • PERSONAL COLLECTION MANAGER",
                color = MutedText,
                fontSize = 10.sp,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, color = MutedText, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                value,
                color = Gold,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class CardItem(
    val name: String,
    val expansion: String,
    val rarity: String
)

@Composable
fun CollectionCard(card: CardItem) {
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
                    .size(54.dp)
                    .background(Navy, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("TCG", color = Gold, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    card.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    card.expansion,
                    color = MutedText,
                    fontSize = 12.sp
                )

                Text(
                    card.rarity,
                    color = Gold,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun SimplePlaceholder(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Navy),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title\nComing Soon",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp
        )
    }
}
