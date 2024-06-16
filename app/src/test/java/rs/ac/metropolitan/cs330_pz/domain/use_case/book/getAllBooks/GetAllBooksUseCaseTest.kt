package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllBooks

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllBooks.GetAllBooksUseCase

class GetAllBooksUseCaseTest {

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private val bookRepository: BookRepository = mockk()

    @Before
    fun setUp() {
        getAllBooksUseCase = GetAllBooksUseCase(bookRepository)
    }

    @Test
    fun `should return list of books from repository`() = runBlocking {
        // Arrange
        val books = listOf(
            Book(id = 1, title = "Book One", image = "slika1", favourite = true, progress = 25),
            Book(id = 2, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        )
        coEvery { bookRepository.getAllBooks() } returns books

        // Act
        val result = getAllBooksUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllBooks() }
        assert(result == books)
    }

    @Test
    fun `should return null when repository returns null`() = runBlocking {
        // Arrange
        coEvery { bookRepository.getAllBooks() } returns null

        // Act
        val result = getAllBooksUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllBooks() }
        assert(result == null)
    }
}
