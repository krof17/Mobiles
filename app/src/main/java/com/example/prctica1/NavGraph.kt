package com.example.prctica1

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prctica1.composables.CharcterDetail.CharacterDetail
import com.example.prctica1.composables.CreaturesList.CharacterList
import com.example.prctica1.composables.Menu.MenuScreen
import com.example.prctica1.composables.SearchByType.searchByType
import com.example.prctica1.data.model.Character
import com.example.prctica1.data.model.CharactersResponse
import com.example.prctica1.data.remote.RetrofitClient.getCharacterById
import com.example.prctica1.viewmodel.YourViewModel

enum class LotrScreens(@StringRes val title: Int){
    Menu(title = R.string.mecnu),
    CreaturesList(title = R.string.CreaturesList),
    CreatureDetail(title = R.string.creatureDetail),
    RaceList(title = R.string.RaceList)
}

@Composable
fun NavGraph (navController: NavHostController, viewModel: YourViewModel){
    NavHost(
        navController = navController,
        startDestination = LotrScreens.Menu.name,
    ) {
        composable(route = LotrScreens.Menu.name) {
            MenuScreen(onCreaturesButtonClick = {
                navController.navigate(LotrScreens.CreaturesList.name)
            }, navController)
        }
        composable(route = LotrScreens.CreaturesList.name){
            CharacterList(navController)
        }
        composable(route = LotrScreens.RaceList.name){
            searchByType(navController)
        }
        composable("${LotrScreens.CreatureDetail.name}/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")
            Log.e("FetchCharacterByIdd", "Error response: $characterId")
            val charactersResponseState by produceState<Character?>(initialValue = null) {
                value = viewModel.fetchCharacterById(characterId)
            }
            val character = charactersResponseState
            Log.e("FetchCharacterByIdd", "Error response: $character")
            if (character != null) {
                CharacterDetail(character, navController)
            }
        }
    }
}