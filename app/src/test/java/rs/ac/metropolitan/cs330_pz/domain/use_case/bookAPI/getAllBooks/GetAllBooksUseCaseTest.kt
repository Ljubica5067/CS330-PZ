package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getAllBooks

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getAllBooks.GetAllBooksUseCase

class GetAllBooksUseCaseTest {

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private val bookRepository: BookApiRepository = mockk()

    @Before
    fun setUp() {
        getAllBooksUseCase = GetAllBooksUseCase(bookRepository)
    }

    @Test
    fun `should return list of books from repository`() = runBlocking {
        // Arrange
        val books = listOf(
            BookDto(id = 1, fullBookName = "Book One", authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("zanr1","zanr2") ),
            BookDto(id = 2, fullBookName = "Book One", authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("zanr1","zanr2") )
        )
        coEvery { bookRepository.getAllBooks() } returns books

        // Act
        val result = getAllBooksUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllBooks() }
        assert(result == books)
    }

    @Test
    fun `should return empty list when repository returns empty list`() = runBlocking {
        // Arrange
        val emptyList = emptyList<BookDto>()
        coEvery { bookRepository.getAllBooks() } returns emptyList

        // Act
        val result = getAllBooksUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllBooks() }
        assert(result == emptyList)
    }
}