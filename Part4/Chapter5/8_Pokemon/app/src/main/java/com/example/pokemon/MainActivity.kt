package com.example.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemon.ui.theme.PokemonTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemon.screen.DetailScreen
import com.example.pokemon.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Composable
fun TopLevel(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        "Home",
        modifier = modifier
    ) {
        composable("Home") {
            MainScreen(
                onPokemonClick = {
                    val pokemonId = it.substringAfter("pokemon/")
                        .substringBefore("/")
                        .toInt()
                    navController.navigate("Detail/${pokemonId}")
                }
            )
        }

        // STEP 3) arguments 파라미터를 설정하자.
        // ```
        // navArgument("pokemenId") {
        // type = NavType.IntType
        // }
        // ```
        // 리스트로 전달해야 한다.
        composable(
            route = "Detail/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) {
            // STEP 4) `pokemonId`를 `Int`값으로 가져오자. (`arguments?.getInt`를 이용)
            val pokemonId = it.arguments?.getInt("pokemonId") as Int // nullable을 as Int로 not-nullable로 변경
            DetailScreen(
                pokemonId = pokemonId,
                onUpButtonClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                },
                viewModel = hiltViewModel()
            )
        }
    }
}