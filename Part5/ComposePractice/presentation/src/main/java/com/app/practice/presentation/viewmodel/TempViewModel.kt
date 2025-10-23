package com.app.practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.app.practice.domain.model.TempModel
import com.app.practice.domain.usecase.TempUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(
    private val useCase: TempUseCase
): ViewModel() {

    fun getTempModel() : TempModel {
        return useCase.getTempModel()
    }
}