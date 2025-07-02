package com.example.pokemon.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.material3.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemon.viewmodel.PokemonViewModel

@Composable
fun MainScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.pokemonList.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = pagingItems.itemCount,
            key = { index -> pagingItems[index]?.url ?: index }
        ) { index ->
            val item = pagingItems[index]
            item?.let {
                Card(
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("포케몬: ${it.name}")
                            Text(
                                text = it.url,
                                modifier = Modifier.alpha(0.4f)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { onPokemonClick(it.url) }) {
                            Text("보기")
                        }
                    }
                }
            }
        }
    }
}
