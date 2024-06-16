package rs.ac.metropolitan.cs330_pz.presentation

sealed class Screen(val route:String) {
    object MainPage:Screen("home")
    object MenuPage:Screen("menu_page")
    object BooksDetailPage:Screen("bookDetails")
}