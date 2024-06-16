package rs.ac.metropolitan.cs330_pz.presentation.library_page

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllBooks.GetAllBooksUseCase
import javax.inject.Inject
@HiltViewModel
class LibraryViewModel @Inject constructor(private val uc1:GetAllBooksUseCase): ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            _books.value = uc1() ?: emptyList()
        }
    }
}