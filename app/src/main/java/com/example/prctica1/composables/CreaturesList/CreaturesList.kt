package com.example.prctica1.composables.CreaturesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
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
import com.example.prctica1.data.model.Character
import com.example.prctica1.viewmodel.YourViewModel
import com.example.prctica1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterList(navController: NavController) {
    val viewModel: YourViewModel = viewModel()
    val charactersResponseState by produceState<List<Character>?>(initialValue = null) {
        value = viewModel.fetchData()
    }
    val charactersResponse = charactersResponseState

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
        if (charactersResponse != null) {
            val characters = charactersResponse
            LazyColumn {
                items(characters) { character ->
                    CharacterItem(character, navController)
                }
            }
        } else {
            LinearProgressIndicator()
        }
    }
}

@Composable
fun CharacterItem(character: Character, navController: NavController) {

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
            .clip( RoundedCornerShape(16.dp))
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





