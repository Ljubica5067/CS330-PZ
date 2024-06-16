package rs.ac.metropolitan.cs330_pz.domain.use_case.book.delete

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking


class DeleteUseCaseTest {

    private lateinit var deleteUseCase: DeleteUseCase
    private val bookRepository: BookRepositoryImpl = mockk()

    @Before
    fun setUp() {
        deleteUseCase = DeleteUseCase(bookRepository)
    }

    @Test
    fun `should call delete on repository when invoked`() = runBlocking {
        // Arrange
        val bookId = 1
        coEvery { bookRepository.delete(bookId) } returns Unit

        // Act
        deleteUseCase(bookId)

        // Assert
        coVerify(exactly = 1) { bookRepository.delete(bookId) }
    }
}
