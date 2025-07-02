package com.example.dependencyinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.dependencyinjection.ui.theme.DependencyInjectionTheme
import com.example.dependencyinjection.viewmodel.GithubViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel

// STEP 3) Activity에 @AndroidEntryPoint를 넣어줍시다.
@AndroidEntryPoint // 주입받을 곳
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DependencyInjectionTheme  {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReposScreen()
                }
            }
        }
    }
}

@Composable
fun ReposScreen(viewModel: GithubViewModel = viewModel()) {
    LazyColumn {
        item {
            Button(onClick = {
                viewModel.getRepos()
            }) {
                Text("리포지토리 가져오기")
            }
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DependencyInjectionTheme {
        ReposScreen()
    }
}