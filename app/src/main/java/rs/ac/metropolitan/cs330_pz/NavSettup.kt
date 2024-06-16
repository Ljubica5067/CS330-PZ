package rs.ac.metropolitan.cs330_pz

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import rs.ac.metropolitan.cs330_pz.presentation.main_page.MainPageViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_pz.presentation.Screen
import rs.ac.metropolitan.cs330_pz.presentation.books_detail_page.components.BookDetailPage
import rs.ac.metropolitan.cs330_pz.presentation.library_page.components.LibraryPage
import rs.ac.metropolitan.cs330_pz.presentation.main_page.components.MainScreen
import rs.ac.metropolitan.cs330_pz.presentation.menu_page.components.MenuPage

@Composable
fun NavSettup(navController: NavHostController = rememberNavController())
{
    NavHost(navController=navController,startDestination=BooksRoute.Home.route)
    {
composable(route=BooksRoute.Home.route)
{
MainScreen(navController = navController)
}
        composable(route=BooksRoute.BookDetailScreen.route)
        {
            navBackStackEntry ->
            val elementId=navBackStackEntry.arguments?.getString("Id")
            if(elementId!=null)
            {
                BookDetailPage(elementId.toInt(),navController = navController,)
            }
            else
            {
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
   composable(route=BooksRoute.Menu.route)
   {
        MenuPage(navController = navController)
   }

        composable(route=BooksRoute.Library.route)
        {
            LibraryPage(navController = navController)
        }

        composable(route=BooksRoute.Fave.route)
        {

        }

    }
}