package rs.ac.metropolitan.cs330_pz.presentation.books_detail_page.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.presentation.books_detail_page.BookDetailPageViewModel
import rs.ac.metropolitan.cs330_pz.presentation.main_page.MainPageViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookDetailPage(bookId: Int, navController: NavController, viewModel: MainPageViewModel = hiltViewModel(),vm:BookDetailPageViewModel= hiltViewModel()) {
    val book = viewModel.getBookById(bookId)

    val book1= book?.let { Book(it.id, book.fullBookName, Random.nextInt(0, 101), false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(text = "Book Details")
                },
                actions = {
                    IconButton(onClick ={navController.popBackStack()}) {
                        Icon(
                            imageVector = if (true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,//prepraviti if
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (book != null) {
                Image(
                    painter = rememberImagePainter(data = book.coverImage),
                    contentDescription = "Book Image",
                    modifier = Modifier
                        .height(400.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,

                    )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (book != null) {
                Text(
                    text = book.fullBookName,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "by:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            if (book != null) {
                Text(
                    text = book.authorName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (book != null) {
                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                if (book != null) {
                    book.genres.forEach { genre ->
                        Chip(genre = genre)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (book1 != null) {
                    vm.addBookToLibrary(book1)
                }
                Log.d("Unos u bazu","$book1")
            }) {
                Text(text = "Add to library")
            }
        }
    }
}

@Composable
fun Chip(genre: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = genre,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}
