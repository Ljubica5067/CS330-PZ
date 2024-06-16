package rs.ac.metropolitan.cs330_pz.presentation.books_detail_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject
@HiltViewModel
class BookDetailPageViewModel @Inject constructor(private val bookRepository: BookRepositoryImpl):
    ViewModel(){
    private val _isInLibrary = MutableStateFlow(false)
    val isInLibrary: StateFlow<Boolean> = _isInLibrary

    private val _books = MutableStateFlow<List<BookDto>>(emptyList())


    fun addBookToLibrary(book: Book) {
        viewModelScope.launch {
            bookRepository.insert(book)
            _isInLibrary.value = true
        }
    }

    fun remove(id:Int)
    {
        viewModelScope.launch{bookRepository.delete(id)
            _isInLibrary.value = false}
    }

    fun checkIfBookIsInLibrary(bookId: Int) {
        viewModelScope.launch {
            val book = bookRepository.getBookById(bookId)
            _isInLibrary.value = book != null
        }

    fun getBookById(bookId: Int): BookDto? {
        return _books.value.find { it.id == bookId }
    }

    suspend fun exists(title:String):Boolean
    {
        if(bookRepository.getBookByTitle(title)!=null)
        {
            return true
        }
        else return false
    }
}}