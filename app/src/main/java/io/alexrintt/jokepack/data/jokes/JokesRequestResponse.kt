package io.alexrintt.jokepack.data.jokes

import io.alexrintt.jokepack.models.Joke

data class JokesRequestResponse(val error: Boolean, val amount: Int, val jokes: List<Joke>)