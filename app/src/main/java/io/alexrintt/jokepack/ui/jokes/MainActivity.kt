package io.alexrintt.jokepack.ui.jokes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.alexrintt.jokepack.ui.theme.C0B0B0D
import io.alexrintt.jokepack.ui.theme.JokepackTheme
import io.alexrintt.jokepack.ui.theme.fonts
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val viewModel = JokeViewModel()

    super.onCreate(savedInstanceState)

    setContent {
      JokeScaffold(viewModel)
    }
  }
}

@Composable
fun JokeScaffold(viewModel: JokeViewModel) {
  JokepackTheme {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
      topBar = {
        AppBar()
      },
      scaffoldState = scaffoldState,
      backgroundColor = C0B0B0D,
      content = {
        JokeList(viewModel)
      },
    )
  }
}

@Composable
fun JokeList(viewModel: JokeViewModel) {
  LaunchedEffect(Unit, block = {
    viewModel.getJokes()
  })

  val listState = rememberLazyListState()

  if (viewModel.jokes.isEmpty()) {
    if (viewModel.isLoading) {
      Box(
        modifier = Modifier
          .fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator(
          color = Color.LightGray
        )
      }
    } else if (viewModel.error != null) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(12.dp),
        contentAlignment = Alignment.Center,
      ) {
        Text(
          viewModel.error!!,
          style = TextStyle(
            color = Color.White.copy(alpha = 0.4f),
            fontFamily = fonts,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
          )
        )
      }
    }
  } else {
    SwipeRefresh(state = rememberSwipeRefreshState(viewModel.isRefreshing), onRefresh = { viewModel.refresh() }) {
      LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
        items(viewModel.jokes.size) { index ->
          JokeCard(viewModel.jokes[index])
        }
        if (viewModel.isLoading) {
          item {
            Box(
              modifier = Modifier
                .padding(12.dp, 48.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
            ) {
              CircularProgressIndicator(color = Color.LightGray)
            }
          }
        }
      }
    }

    listState.OnBottomReached(buffer = 5) {
      viewModel.getJokes()
    }
  }
}

@Composable
fun AppBar() {
  val context = LocalContext.current

  TopAppBar(
    title = {
      Row {
        val base = SpanStyle(color = Color(0x44FFFFFF), fontSize = 22.sp, fontFamily = fonts)

        Text(
          buildAnnotatedString {
            withStyle(
              style = base.copy(
                color = base.color.copy(alpha = 0.2f),
                textDecoration = TextDecoration.LineThrough
              ),
            ) {
              append("Jet")
            }
            withStyle(style = base.copy(base.color.copy(alpha = 0.6f))) {
              append("Pack")
            }
          },
          style = MaterialTheme.typography.h5.copy(
            fontFamily = fonts,
            color = Color(0x44FFFFFF)
          )
        )
      }
    },
    backgroundColor = Color(0xFF0B0B0D),
    actions = {
      IconButton(onClick = {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/alexrintt/jokepack"))
        ContextCompat.startActivity(context, browserIntent, null)
      }) {
        Icon(Icons.Filled.Android, "Open Github Repository Code", tint = Color.White.copy(alpha = 0.4f))
      }
    },
    elevation = 0.dp
  )
}


@Composable
fun LazyListState.OnBottomReached(
  // tells how many items before we reach the bottom of the list
  // to call onLoadMore function
  buffer: Int = 0,
  onLoadMore: () -> Unit
) {
  // Buffer must be positive.
  // Or our list will never reach the bottom.
  require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

  val shouldLoadMore = remember {
    derivedStateOf {
      val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
        ?: return@derivedStateOf true

      // subtract buffer from the total items
      lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
    }
  }

  LaunchedEffect(shouldLoadMore) {
    snapshotFlow { shouldLoadMore.value }
      .collect { if (it) onLoadMore() }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  JokeScaffold(JokeViewModel())
}