package io.alexrintt.jokepack.ui.jokes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.alexrintt.jokepack.data.jokes.JokesRepository
import io.alexrintt.jokepack.models.Joke
import kotlinx.coroutines.launch

class JokeViewModel() : ViewModel() {
  val jokes: MutableList<Joke> = mutableListOf()
  var isLoading: Boolean by mutableStateOf(true)
  var error: String? by mutableStateOf(null)
  var isRefreshing: Boolean by mutableStateOf(false)

  fun getJokes(clear: Boolean = false) {
    isLoading = true

    viewModelScope.launch {
      val repository = JokesRepository.retrofitService

      try {
        val response = repository.getJokes()

        if(clear) {
          jokes.clear()
        }

        jokes.addAll(response.jokes)
      } catch (e: Exception) {
        print(e.message)
        error = e.message
      } finally {
        isLoading = false
        isRefreshing = false
      }
    }
  }

  fun refresh() {
    isRefreshing = true

    getJokes(true)
  }
}