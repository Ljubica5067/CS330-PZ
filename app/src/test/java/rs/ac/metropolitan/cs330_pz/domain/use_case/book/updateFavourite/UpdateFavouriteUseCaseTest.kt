package rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateFavourite

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateFavourite.UpdateFavouriteUseCase

class UpdateFavouriteUseCaseTest {

    private lateinit var updateFavouriteUseCase: UpdateFavouriteUseCase
    private val bookRepository: BookRepositoryImpl = mockk()

    @Before
    fun setUp() {
        updateFavouriteUseCase = UpdateFavouriteUseCase(bookRepository)
    }

    @Test
    fun `should call updateFavourite on repository when invoked`() = runBlocking {
        // Arrange
        val bookId = 1
        val favourite = true
        coEvery { bookRepository.updateFavourite(bookId, favourite) } returns Unit

        // Act
        updateFavouriteUseCase(bookId, favourite)

        // Assert
        coVerify(exactly = 1) { bookRepository.updateFavourite(bookId, favourite) }
    }
}