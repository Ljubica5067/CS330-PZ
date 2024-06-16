package rs.ac.metropolitan.cs330_pz.presentation.favourites_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllFav.GetAllFavUseCase
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById.GetBookByIdUseCase
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateFavourite.UpdateFavouriteUseCase
import javax.inject.Inject
@HiltViewModel
class FavouritesViewModel@Inject constructor (private val uc1:BookRepositoryImpl,private val uc2:UpdateFavouriteUseCase,private val uc3:GetAllFavUseCase):
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
            val book = uc1.getBookById(bookId)
            _isFavourite.value = book?.favourite ?: false
        }
    }

    fun updateFavourite(bookId: Int, favourite: Boolean) {
        viewModelScope.launch {
            uc2(bookId, favourite)
            _isFavourite.value = favourite
        }
    }

    fun getAll()
    {
        viewModelScope.launch { _favouriteBooks.value = uc3() ?: emptyList() }
    }
}
