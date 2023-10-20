package com.example.prctica1

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prctica1.composables.CreaturesList.CharacterList
import com.example.prctica1.composables.Menu.MenuScreen

enum class LotrScreens(@StringRes val title: Int){
    Menu(title = R.string.mecnu),
    CreaturesList(title = R.string.CreaturesList)
}


@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = LotrScreens.Menu.name,
    ) {
        composable(route = LotrScreens.Menu.name) {
            MenuScreen(onCreaturesButtonClick = {
                navController.navigate(LotrScreens.CreaturesList.name)
            })
        }
        composable(route = LotrScreens.CreaturesList.name){
            CharacterList(navController)
        }
    }
}