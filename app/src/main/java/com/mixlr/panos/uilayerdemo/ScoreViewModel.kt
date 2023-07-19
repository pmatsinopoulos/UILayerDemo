package com.mixlr.panos.uilayerdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScoreViewModel : ViewModel() {
    fun homeGoal() {
        val (home, guest) = _uiState.value.score.split("-")
        _uiState.value = ScoreUiState("${home.toInt() + 1}-$guest")
    }

    fun guestGoal() {
        val (home, guest) = _uiState.value.score.split("-")
        _uiState.value = ScoreUiState("$home-${guest.toInt() + 1}")
    }

    private val _uiState = MutableStateFlow(ScoreUiState("0-0"))

    val scoreUiState: StateFlow<ScoreUiState> = _uiState.asStateFlow()
}
