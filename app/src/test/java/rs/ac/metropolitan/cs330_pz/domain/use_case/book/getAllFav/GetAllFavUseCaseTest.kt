package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllFav

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllFav.GetAllFavUseCase

class GetAllFavUseCaseTest {

    private lateinit var getAllFavUseCase: GetAllFavUseCase
    private val bookRepository: BookRepository = mockk()

    @Before
    fun setUp() {
        getAllFavUseCase = GetAllFavUseCase(bookRepository)
    }

    @Test
    fun `should return list of favorite books from repository`() = runBlocking {
        // Arrange
        val favoriteBooks = listOf(
            Book(id = 1, title = "Book One", image = "slika1", favourite = true, progress = 25),
            Book(id = 2, title = "Book Two", image = "slika2", favourite = true, progress = 1)
        )
        coEvery { bookRepository.getAllFav() } returns favoriteBooks

        // Act
        val result = getAllFavUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllFav() }
        assert(result == favoriteBooks)
    }

    @Test
    fun `should return empty list when repository returns empty list`() = runBlocking {
        // Arrange
        val emptyList = emptyList<Book>()
        coEvery { bookRepository.getAllFav() } returns emptyList

        // Act
        val result = getAllFavUseCase()

        // Assert
        coVerify(exactly = 1) { bookRepository.getAllFav() }
        assert(result == emptyList)
    }
}