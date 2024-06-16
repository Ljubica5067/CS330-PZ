package rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateBookProgress

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateBookProgress.UpdateBookProgressUseCase

class UpdateBookProgressUseCaseTest {

    private lateinit var updateBookProgressUseCase: UpdateBookProgressUseCase
    private val bookRepository: BookRepository = mockk()

    @Before
    fun setUp() {
        updateBookProgressUseCase = UpdateBookProgressUseCase(bookRepository)
    }

    @Test
    fun `should call updateBookProgress on repository when invoked`() = runBlocking {
        // Arrange
        val bookId = 1
        val progress = 50
        coEvery { bookRepository.updateBookProgress(bookId, progress) } returns Unit

        // Act
        updateBookProgressUseCase(bookId, progress)

        // Assert
        coVerify(exactly = 1) { bookRepository.updateBookProgress(bookId, progress) }
    }
}