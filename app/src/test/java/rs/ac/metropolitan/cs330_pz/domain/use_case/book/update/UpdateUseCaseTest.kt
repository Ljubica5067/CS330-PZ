package rs.ac.metropolitan.cs330_pz.domain.use_case.book.update

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.update.UpdateUseCase

class UpdateUseCaseTest {

    private lateinit var updateUseCase: UpdateUseCase
    private val bookRepository: BookRepository = mockk()

    @Before
    fun setUp() {
        updateUseCase = UpdateUseCase(bookRepository)
    }

    @Test
    fun `should call update on repository when invoked`() = runBlocking {
        // Arrange
        val book = Book(id = 2, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        coEvery { bookRepository.update(book) } returns Unit

        // Act
        updateUseCase(book)

        // Assert
        coVerify(exactly = 1) { bookRepository.update(book) }
    }
}