package io.lakscastro.jokepack.data.jokes

import io.lakscastro.jokepack.models.Joke
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesRepository {
  @GET("/joke/{subject}")
  suspend fun getJokes(
    @Path("subject") subject: String = "Any",
    @Query("amount") amount: Int = 10,
    @Query("type") type: String = "single"
  ): JokesRequestResponse

  companion object {
    private const val BASE_URL = "https://v2.jokeapi.dev"

    val retrofitService : JokesRepository by lazy {
      Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokesRepository::class.java)
    }
  }
}