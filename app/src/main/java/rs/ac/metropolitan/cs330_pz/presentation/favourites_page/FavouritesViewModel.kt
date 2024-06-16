package rs.ac.metropolitan.cs330_pz.presentation.favourites_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject
@HiltViewModel
class FavouritesViewModel@Inject constructor (private val bookRepository: BookRepositoryImpl):
    ViewModel(){
    private val _isFavourite = MutableStateFlow<Boolean>(false)
    val isFavourite: StateFlow<Boolean> = _isFavourite
    private val _favouriteBooks = MutableStateFlow<List<Book>>(emptyList())
    val favouriteBooks: StateFlow<List<Book>> = _favouriteBooks

    init {
        getAll()
    }

    fun checkIfBookIsFavourite(bookId: Int) {
        viewModelScope.launch {
            val book = bookRepository.getBookById(bookId)
            _isFavourite.value = book?.favourite ?: false
        }
    }

    fun updateFavourite(bookId: Int, favourite: Boolean) {
        viewModelScope.launch {
            bookRepository.updateFavourite(bookId, favourite)
            _isFavourite.value = favourite
        }
    }

    fun getAll()
    {
        viewModelScope.launch { _favouriteBooks.value = bookRepository.getAllFav() ?: emptyList() }
    }
}
