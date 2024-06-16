package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById.GetBookByIdUseCase

class GetBookByIdUseCaseTest {

    private lateinit var getBookByIdUseCase: GetBookByIdUseCase
    private val bookRepository: BookRepositoryImpl = mockk()

    @Before
    fun setUp() {
        getBookByIdUseCase = GetBookByIdUseCase(bookRepository)
    }

    @Test
    fun `should return true when book exists`() = runBlocking {
        // Arrange
        val bookId = 1
        val book = Book(id = 1, title = "Book One", image = "slika1", favourite = true, progress = 25)
        coEvery { bookRepository.getBookById(bookId) } returns book

        // Act
        val result = getBookByIdUseCase(bookId)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookById(bookId) }
        assert(result)
    }
}