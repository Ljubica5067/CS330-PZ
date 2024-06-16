package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookByTitle

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookByTitle.GetBooksByTitleUseCase

class GetBooksByTitleUseCaseTest {

    private lateinit var getBooksByTitleUseCase: GetBooksByTitleUseCase
    private val bookRepository: BookRepository = mockk()

    @Before
    fun setUp() {
        getBooksByTitleUseCase = GetBooksByTitleUseCase(bookRepository)
    }

    @Test
    fun `should return book when book with given title exists`() = runBlocking {
        // Arrange
        val title = "Existing Book Title"
        val book = Book(id = 2, title = title, image = "slika2", favourite = true, progress = 1)
        coEvery { bookRepository.getBookByTitle(title) } returns book

        // Act
        val result = getBooksByTitleUseCase(title)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByTitle(title) }
        assert(result == book)
    }

    @Test
    fun `should return null when book with given title does not exist`() = runBlocking {
        // Arrange
        val title = "Nonexistent Book Title"
        coEvery { bookRepository.getBookByTitle(title) } returns null

        // Act
        val result = getBooksByTitleUseCase(title)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByTitle(title) }
        assert(result == null)
    }
}