package io.alexrintt.jokepack.ui.jokes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import io.alexrintt.jokepack.models.Joke
import io.alexrintt.jokepack.ui.theme.fonts
import io.alexrintt.jokepack.widget.FlowRow

@Composable
fun JokeCard(joke: Joke) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(
        onClick = {}
      ),
    color = Color.Transparent
  ) {
    Box(
      modifier = Modifier.padding(26.dp, 18.dp)
    ) {
      Column {
        FlowRow {
          for (tag in joke.flags.entries)
            if (tag.value) JokeTag(tag.key)
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Text(
          joke.text,
          style = TextStyle(
            color = Color(0xFFEEEEEE),
            fontFamily = fonts,
            fontSize = 16.sp
          )
        )
        JokeCategory(joke.subject, joke.text)
      }
    }
  }
}

@Composable
private fun JokeTag(tagName: String) {
  Box(
    modifier = Modifier.padding(0.dp, 4.dp, 4.dp, 0.dp)
  ) {
    Surface(
      color = Color(0xFF000000),
      shape = RoundedCornerShape(14.dp)
    ) {
      Box(
        modifier = Modifier.padding(14.dp, 4.dp)
      ) {
        Text(
          tagName,
          style = TextStyle(
            fontFamily = fonts,
            fontSize = 8.sp,
            color = Color(0x88FFFFFF)
          ),
        )
      }
    }
  }
}

fun showToast(context: Context, msg: String) {
  Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

@Composable
private fun JokeCategory(name: String, text: String) {
  val base = SpanStyle(color = Color(0x88FFFFFF), fontSize = 10.sp, fontFamily = fonts)
  val baseTextStyle = TextStyle(
    color = base.color,
    fontSize = base.fontSize,
    fontFamily = base.fontFamily
  )

  val localClipboardManager = LocalClipboardManager.current
  val context = LocalContext.current

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      buildAnnotatedString {
        withStyle(style = base.copy(color = base.color.copy(alpha = 0.2f))) {
          append("Subject ")
        }
        withStyle(style = base) {
          append(name)
        }
      }
    )
    TextButton(onClick = {
      localClipboardManager.setText(AnnotatedString(text))
      showToast(context, "Copied to clipboard")
    }) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(Icons.Filled.ContentCopy, "Copy to clipboard", tint = base.color)
        Spacer(modifier = Modifier.padding(4.dp))
        Text("Copy", style = baseTextStyle)
      }
    }
  }
}