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
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.delete.DeleteUseCase
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById.GetBookByIdUseCase
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.insert.InsertUseCase
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookByTitle.GetBooksByTitleUseCase
import javax.inject.Inject
@HiltViewModel
class BookDetailPageViewModel @Inject constructor(private val uc1:InsertUseCase,private val uc2:DeleteUseCase,private val uc3:GetBookByIdUseCase,private val uc4:GetBooksByTitleUseCase):
    ViewModel(){
    private val _isInLibrary = MutableStateFlow(false)
    val isInLibrary: StateFlow<Boolean> = _isInLibrary

    private val _books = MutableStateFlow<List<BookDto>>(emptyList())


    fun addBookToLibrary(book: Book) {
        viewModelScope.launch {
            uc1(book)
            _isInLibrary.value = true
        }
    }

    fun remove(id:Int)
    {
        viewModelScope.launch{uc2(id)
            _isInLibrary.value = false}
    }

    fun checkIfBookIsInLibrary(bookId: Int) {
        viewModelScope.launch {
            val book = uc3(bookId)
            _isInLibrary.value = book?.id ==bookId

        }

    fun getBookById(bookId: Int): BookDto? {
        return _books.value.find { it.id == bookId }
    }

    suspend fun exists(title:String):Boolean
    {
        if(uc4(title)!=null)
        {
            return true
        }
        else return false
    }
}}