package rs.ac.metropolitan.cs330_pz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.compose.CS330PZTheme
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330_pz.presentation.main_page.components.MainScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330PZTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavSettup()
                    /*val genres = listOf(
                        "Adult",
                        "Adult Fiction",
                        "Coming Of Age",
                        "Contemporary",
                        "Fiction",
                        "Historical Fiction"
                    )
                    BookDetailPage(
                        book = Book(1,"knjiga","autor","bla blabla","https://media.oceanofpdf.com/2024/05/PDF-EPUB-Kittentits-by-Holly-Wilson-Download.jpg",genres),
                        onBackClick = { /*TODO*/ },
                        onFavoriteClick = { /*TODO*/ },
                        isFavorite = false
                    )*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CS330PZTheme {
        Greeting("Android")
    }
}