package com.example.prctica1.composables.CharcterDetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CharacterDetail(character: Character?, navController: NavController) {

    val backgroundResource = when (character?.name) {
        "Gandalf" -> R.drawable.gandalf3
        "Saruman" -> R.drawable.saruman2
        "Aragorn II Elessar" -> R.drawable.aragorn
        "Boromir" -> R.drawable.boromir
        "Legolas" -> R.drawable.legolas
        "Arwen" -> R.drawable.arwen
        "Gimli" -> R.drawable.gimli
        "Thorin II Oakenshield" -> R.drawable.thorin
        "Treebeard" -> R.drawable.treebeard
        "Gollum" -> R.drawable.gollum
        "Smaug" -> R.drawable.smaug
        "Azog" -> R.drawable.azog
        "Shelob" -> R.drawable.shelob
        "Eru Ilúvatar" -> R.drawable.eru
        else -> R.drawable.desconocido
    }
    Column(
        modifier = Modifier
            //.padding(horizontal = 15.dp)
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Torna al menu",
                        tint = Color.White
                    )
                }
            },
            title = {
                if (character != null) {
                    Text("Vuelve a la lista de criaturas", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                }
            }
        )
        /*
        if (character != null) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
                //modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        */
        Image(
            painter = painterResource(id = backgroundResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF00FF00),
                    shape = RoundedCornerShape(10.dp)
                )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF00FF00),
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column (/*modifier = Modifier.padding(start = 5.dp)*/){
                if (character != null) {
                    Text(
                        text = "Name: " + character.name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Race: " + character.race,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Born: " + character.birth.toString() + " -  Died: " + character.death.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Gender: " + character.gender,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Hair: " + character.hair.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Height: " + character.height.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Spouse: " + character.spouse.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Realm: " + character.realm.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}