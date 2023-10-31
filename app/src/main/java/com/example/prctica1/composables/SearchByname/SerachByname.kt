package com.example.prctica1.composables.SearchByname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prctica1.composables.CreaturesList.CharacterItem
import com.example.prctica1.data.model.Character
import com.example.prctica1.viewmodel.YourViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerachName(navController: NavController) {
    val viewModel: YourViewModel = viewModel()
    val charactersResponseState by produceState<List<Character>?>(initialValue = null) {
        value = viewModel.fetchAllData()
    }
    val charactersResponse = charactersResponseState

    var searchQuery by remember { mutableStateOf("") }

    val filteredCharacters = charactersResponse?.filter { character ->
        character.name.contains(searchQuery, ignoreCase = true)
    } ?: emptyList()

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Torna al menu"
                    )
                }
            },
            title = { Text("Criaturas Fantásticas", style = MaterialTheme.typography.bodyLarge,) }
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
            },
            label = { Text("Search by name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        if (filteredCharacters != null) {
            LazyColumn {
                items(filteredCharacters) { character ->
                    CharacterItem(character, navController)
                }
            }
        } else {
            LinearProgressIndicator()
        }
    }
}

