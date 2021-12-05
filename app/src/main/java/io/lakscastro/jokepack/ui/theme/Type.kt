package io.lakscastro.jokepack.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.lakscastro.jokepack.R

val fonts = FontFamily(
  Font(R.font.roboto_mono_bold, weight = FontWeight.Bold),
  Font(R.font.roboto_mono_medium, weight = FontWeight.Medium),
  Font(R.font.roboto_mono_regular, weight = FontWeight.Normal),
  Font(R.font.roboto_mono_semibold, weight = FontWeight.SemiBold),
  Font(R.font.roboto_mono_thin, weight = FontWeight.Thin),
)

// Set of Material typography styles to start with
val Typography = Typography(
  body1 = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  )
  /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)