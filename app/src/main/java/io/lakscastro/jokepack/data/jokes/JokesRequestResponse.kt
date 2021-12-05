package io.lakscastro.jokepack.data.jokes

import io.lakscastro.jokepack.models.Joke

data class JokesRequestResponse(val error: Boolean, val amount: Int, val jokes: List<Joke>)