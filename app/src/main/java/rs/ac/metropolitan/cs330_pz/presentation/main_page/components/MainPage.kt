package rs.ac.metropolitan.cs330_pz.presentation.main_page.components
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.io.File
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import rs.ac.metropolitan.cs330_pz.data.dao.BookDao
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.data.repository.BookApiRepositoryImpl
import rs.ac.metropolitan.cs330_pz.presentation.main_page.MainPageViewModel

@ExperimentalMaterial3Api
@Composable
fun AppBar(onMenuClick: () -> Unit)
{
   TopAppBar(
        title = {
            Row(
            modifier = Modifier.fillMaxWidth(),
        ) {Text("NarrativeNook",modifier = Modifier.padding(start = 65.dp))}},

        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Hamburger Menu")
            }
        },
        )
}

@Composable
fun SearchInput(viewModel: MainPageViewModel = hiltViewModel())
{
    var searchQuery by remember{ mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    OutlinedTextField(
        value=searchQuery,
        onValueChange={ searchQuery=it
            viewModel.updateSearchQuery(it.text)},
        leadingIcon={Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")},
        placeholder={ Text(text = "Search")},
        shape = RoundedCornerShape(18.dp),
        modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
@ExperimentalMaterial3Api
@Composable
fun GenreChips(viewModel: MainPageViewModel = hiltViewModel())
{
    val genres by viewModel.genres.collectAsState()
    val selectedGenres by viewModel.selectedGenres.collectAsState()

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        genres.forEach { genre ->
            val selected = selectedGenres.contains(genre)
            ElevatedFilterChip(
                onClick = { viewModel.toggleGenreSelection(genre) },
                label = { Text(genre) },
                selected = selected,
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainPageViewModel = hiltViewModel(),navController: NavController)
{
    val books by viewModel.filteredBooks.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppBar(onMenuClick = {
                navController.navigate(route = "menu")
                scope.launch {
                    snackbarHostState.showSnackbar("Menu clicked")
                }
            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SearchInput()
            GenreChips(viewModel = viewModel)
            BookGrid(books = books,navController)
        }
    }

}

@Composable
fun BookCard(book: BookDto,navController: NavController)
{
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(250.dp)
            .clickable {
                navController.navigate("detail/${book.id}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.coverImage),
                contentDescription = "Book image",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.fullBookName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )
            Text(
                text = book.authorName,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        }
    }
}


@Composable
fun BookGrid(books: List<BookDto>,navController: NavController)
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyRow {
            items(books) { book ->
                BookCard(book = book,navController = navController)
            }
        }
    }
}

