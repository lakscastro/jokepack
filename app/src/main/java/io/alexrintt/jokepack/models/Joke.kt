package io.alexrintt.jokepack.models

import com.google.gson.annotations.SerializedName

data class Joke(
  @SerializedName("category") val subject: String,
  val type: String,
  @SerializedName("joke") val text: String,
  val id: Int,
  val safe: Boolean,
  val lang: String,
  val flags: Map<String, Boolean>
)
