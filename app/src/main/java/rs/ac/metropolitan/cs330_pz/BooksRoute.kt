package rs.ac.metropolitan.cs330_pz

sealed class BooksRoute(val route: String) {
    object Home : BooksRoute(route = "home")
    object BookDetailScreen: BooksRoute(route = "detail/{Id}"){
        fun createRoute(id: Int) = "detail/$id"
}
    object Menu:BooksRoute(route="menu")

    object Library:BooksRoute(route = "library")
    object Fave:BooksRoute(route = "favourites")
}