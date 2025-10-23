package com.app.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    fun openSearchForm() {
        println("openSearchForm") // 테스트용
    }
}