package rs.ac.metropolitan.cs330_pz.presentation.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.data.repository.BookApiRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getAllBooks.GetAllBooksUseCase
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(private val uc1:GetAllBooksUseCase) : ViewModel(){
    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres: StateFlow<List<String>> = _genres
    private val _books = MutableStateFlow<List<BookDto>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    private val _selectedGenres = MutableStateFlow<Set<String>>(emptySet())
    val selectedGenres: StateFlow<Set<String>> = _selectedGenres

    init {
        fetchGenres()
        fetchBooks()
    }

    private fun fetchGenres()
    {
        viewModelScope.launch {
            val books = uc1().map { bookDto ->
            BookDto(
                id=bookDto.id,
                fullBookName = bookDto.fullBookName,
                authorName = bookDto.authorName,
                description = bookDto.description,
                coverImage = bookDto.coverImage,
                genres=bookDto.genres)  }
            val genresSet=books.flatMap { it.genres }.toSet()
            _genres.value=genresSet.toList()
        }
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            try {
                val bookDtos = uc1()
                val books = bookDtos.map { dto ->
                    BookDto(
                        id = dto.id,
                        fullBookName = dto.fullBookName,
                        description = dto.description,
                        authorName = dto.authorName,
                      coverImage = dto.coverImage,
                        genres = dto.genres
                    )
                }
                _books.value = books
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun toggleGenreSelection(genre: String) {
        _selectedGenres.value = if (_selectedGenres.value.contains(genre)) {
            _selectedGenres.value - genre
        } else {
            _selectedGenres.value + genre
        }
    }

    val filteredBooks: StateFlow<List<BookDto>> = combine(_searchQuery, _books, _selectedGenres) { query, books, selectedGenres ->
        books.filter { book ->
            (query.isEmpty() || book.fullBookName.contains(query, ignoreCase = true)) &&
            (selectedGenres.isEmpty() || book.genres.any { it in selectedGenres })
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getBookById(bookId: Int): BookDto? {
        return _books.value.find { it.id == bookId }
    }


}





