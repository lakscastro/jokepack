package io.alexrintt.jokepack.data

import io.alexrintt.jokepack.models.Joke

//
//import android.util.Range
//import io.alexrintt.jokepack.models.Flag
//import io.alexrintt.jokepack.models.Joke
//import io.alexrintt.jokepack.models.Lang
//import io.alexrintt.jokepack.models.Subject
//import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
//import retrofit2.http.GET
//
//enum class JokeFormat {
//  SINGLE,
//  TWOPART,
//}
//
//
//private val BASE_URL = "https://v2.jokeapi.dev"
//private val PATH = "/joke"
//
//private val moshi = Moshi.Builder()
//  .add(KotlinJsonAdapterFactory())
//  .build()
//
//private val retrofit = Retrofit.Builder()
//  .addConverterFactory(ScalarsConverterFactory.create())
//  .baseUrl(BASE_URL)
//  .build()
//
///// A standard and simple Restful API to get random jokes
/////
///// [Refer to details](https://sv443.net/jokeapi/v2/)
//class JokeService {
//
//  fun fetch(
//  ): Array<Joke> {
//
//  }
//}
//
//interface JokeService {
//  @GET("joke")
//  fun getJokes(
//    amount: Int = 10,
//    type: JokeFormat = JokeFormat.SINGLE,
//    range: Range<Int>? = null,
//    blacklist: Set<Flag> = emptySet(),
//    lang: Lang = Lang.EN,
//    subjects: Array<String>? = null
//  ): String
//}
//
//object JokeApi {
//  val retrofitService :JokeService by lazy {
//    retrofit.create(JokeService::class.java)
//  }
//}

