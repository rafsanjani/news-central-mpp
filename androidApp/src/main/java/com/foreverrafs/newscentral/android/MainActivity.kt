package com.foreverrafs.newscentral.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.foreverrafs.newscentral.Article
import com.foreverrafs.newscentral.PrettyDateFormatter
import com.foreverrafs.newscentral.Result

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCentral()
        }
    }
}

@Composable
fun NewsCentral(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.result.collectAsState()

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                is Result.Error -> {
                    Text(text = "Error loading news items", fontWeight = FontWeight.Bold)
                }
                is Result.Loading -> {
                    Text(text = "Loading news items", fontWeight = FontWeight.Bold)
                }
                is Result.Success -> {
                    ArticleList(articles = (state as Result.Success).data)
                }
                null -> {
                    Log.d("Rafs", "NewsCentral: Result is null")
                }
            }
        }
    }
}

@Composable
fun ArticleList(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = articles, key = { it.id }) { article ->
            ArticleCard(article = article)
        }
    }
}

@Composable
fun ArticleCard(article: Article) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl)
                    .crossfade(true).build(),
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Top)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.headline,
                    style = MaterialTheme.typography.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = PrettyDateFormatter.ofPattern("MMM dd, yyyy").format(article.date),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsCentralPreview() {
    val viewModel = MainViewModel()
    NewsCentral(viewModel = viewModel)
}
