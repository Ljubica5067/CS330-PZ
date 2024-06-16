package rs.ac.metropolitan.cs330_pz.domain.use_case.book.insert

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.insert.InsertUseCase

class InsertUseCaseTest {

    private lateinit var insertUseCase: InsertUseCase
    private val bookRepository: BookRepositoryImpl = mockk()

    @Before
    fun setUp() {
        insertUseCase = InsertUseCase(bookRepository)
    }

    @Test
    fun `should call insert on repository when invoked`() = runBlocking {
        // Arrange
        val book = Book(id = 2, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        coEvery { bookRepository.insert(book) } returns Unit

        // Act
        insertUseCase(book)

        // Assert
        coVerify(exactly = 1) { bookRepository.insert(book) }
    }
}