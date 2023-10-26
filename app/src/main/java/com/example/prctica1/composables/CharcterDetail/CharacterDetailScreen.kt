package com.example.prctica1.composables.CharcterDetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prctica1.R
import com.example.prctica1.data.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(character: Character?, navController: NavController){

    val backgroundResource = when (character?.name) {
        "Gandalf" -> R.drawable.gandalf3
        "Saruman" -> R.drawable.saruman
        else -> R.drawable.gandalf
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = 50.dp)
    )
    {
        if (character != null) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Image(
            painter = painterResource(id = backgroundResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxHeight()) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Torna al menu",
                    )
                }
            },
            title = {
                if (character != null) {
                    Text("", style = MaterialTheme.typography.bodyLarge)
                }
            }
        )
        /*
        Card(modifier = Modifier.background(color = Color.White)){}
        */
    }
}