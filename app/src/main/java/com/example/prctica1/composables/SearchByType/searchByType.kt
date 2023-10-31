package com.example.prctica1.composables.SearchByType

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prctica1.LotrScreens
import com.example.prctica1.R
import com.example.prctica1.data.model.Character
import com.example.prctica1.viewmodel.YourViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchByType(navController: NavController) {
    val viewModel: YourViewModel = viewModel()
    val charactersResponseState by produceState<List<Character>?>(initialValue = null) {
        value = viewModel.fetchAllData()
    }

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
            title = { Text("Buscador", style = MaterialTheme.typography.bodyLarge) }
        )
        SearchScreen(characters = charactersResponseState, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(characters: List<Character>?, navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedRace by remember { mutableStateOf<String?>(null) }
    var showRaceButtons by remember { mutableStateOf(true) }

    val matchingRaces = characters
        ?.filter { it.race != null }
        ?.mapNotNull { it.race }
        ?.distinct()
        ?.filter { it.contains(searchText, ignoreCase = true) }
        ?: emptyList()

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        // Condición para mostrar u ocultar la barra de búsqueda
        if (showRaceButtons) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { "Buscar por raza" },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (showRaceButtons && matchingRaces.isNotEmpty()) {
            Text("Nombres de Razas:", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))

            // Lista de nombres de razas debajo del buscador siempre
            Column {
                matchingRaces.forEach { race ->
                    Button(
                        onClick = {
                            selectedRace = race
                            showRaceButtons = false // Oculta los botones de razas
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Text(text = race)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra la lista de razas si se ha seleccionado una
        if (selectedRace != null) {
            if (characters != null) {
                RaceList(characters, selectedRace ?: "", navController)
            }
        }
    }
}


@Composable
fun RaceList(characters: List<Character>, selectedRace: String, navController: NavController) {

    val filteredCharacters = characters.filter { it.race == selectedRace }

    if (filteredCharacters.isNotEmpty()) {
        Text("Personajes de la raza $selectedRace:", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn {
            items(filteredCharacters) { character ->

                val backgroundResource = when (character.race) {
                    "Human" -> R.drawable.human
                    "Humans" -> R.drawable.human
                    "Hobbit" -> R.drawable.hobbit
                    "Elf" -> R.drawable.elfs
                    "Elves" -> R.drawable.elfs
                    "Dwarf" -> R.drawable.dwarfs
                    "Ent" -> R.drawable.ents
                    "Ents" -> R.drawable.ents
                    "Maiar" -> R.drawable.maiar
                    "Dragon" -> R.drawable.dragons
                    "Dragons" -> R.drawable.dragons
                    "Orc" -> R.drawable.orcs
                    "Orcs" -> R.drawable.orcs
                    "Great Spiders" -> R.drawable.spider
                    "God" -> R.drawable.god
                    else -> R.drawable.hobbit
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, top = 6.dp, end = 15.dp, start = 15.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { navController.navigate("${LotrScreens.CreatureDetail.name}/${character._id}") },
                ) {
                    Image(
                        painter = painterResource(id = backgroundResource),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 4.dp, bottom = 5.dp, top = 5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = character.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Race: ${character.race}",
                            color = Color.White
                        )
                    }
                }

            }
        }
    } else {
        Text("No se encontraron personajes con esta raza.")
    }
}









