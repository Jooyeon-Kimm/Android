package com.example.dependencyinjection.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjection.model.Repo
import com.example.dependencyinjection.service.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


// STEP 6) @HiltViewModel 어노테이션을 지정합니다.
// STEP 7) 생성자에 @Inject를 붙여줍시다.
@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService
) : ViewModel() {
    val repos = mutableStateListOf<Repo>() // composable에서 관찰 가능

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = githubService.listRepos("Jooyeon-Kimm")
            repos.addAll(result)
        }
    }
}