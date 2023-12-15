package com.d121211008.brown.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211008.brown.MyApplication
import com.d121211008.brown.data.model.Brown
import com.d121211008.brown.data.repository.BrownRepository
import com.d121211008.brown.ui.theme.BrownThemePhotoTheme
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val brown: List<Brown>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val brownRepository: BrownRepository) : ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getBrown() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = brownRepository.getBrown()
            mainUiState = MainUiState.Success(result.hits.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getBrown()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val brownRepository = application.container.brownRepository
                MainViewModel(brownRepository)
            }
        }
    }
}