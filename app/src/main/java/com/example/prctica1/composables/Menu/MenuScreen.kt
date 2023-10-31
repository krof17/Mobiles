package com.example.prctica1.composables.Menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prctica1.LotrScreens
import com.example.prctica1.composables.Menu.components.MenuButton
import com.example.prctica1.R

@Composable
fun MenuScreen(onCreaturesButtonClick: () -> Unit, navigate : NavController) {
    val letterSpacing = with(LocalDensity.current) {
        dimensionResource(R.dimen.text_letter_spacing).toSp()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {

                Text(
                    text = "Criaturas del mundo Tolkien",
                    //letterSpacing = letterSpacing,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 35.dp)
                    )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.text_letter_spacing)))
                MenuButton(onCreaturesButtonClick, text = "Criaturas fantásticas")
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                Button(
                    onClick = { navigate.navigate(LotrScreens.SearchByName.name)},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "Buscar por nombre",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                Button(
                    onClick = { navigate.navigate(LotrScreens.RaceList.name)},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "Buscar por raza",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}
