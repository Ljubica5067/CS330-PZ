package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookById

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository

class GetBookByIdUseCaseTest {

    private lateinit var getBookByIdUseCase: GetBookByIdUseCase
    private val bookRepository: BookApiRepository = mockk()

    @Before
    fun setUp() {
        getBookByIdUseCase = GetBookByIdUseCase(bookRepository)
    }

    @Test
    fun `should return book for given id from repository`() {
        // Arrange
        val id = 1
        val book =  BookDto(id = 2, fullBookName = "Some Book Title", authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("zanr1","zanr2") )
        every { bookRepository.getBookByID(id) } returns book

        // Act
        val result = getBookByIdUseCase(id)

        // Assert
        verify(exactly = 1) { bookRepository.getBookByID(id) }
        assert(result == book)
    }
}