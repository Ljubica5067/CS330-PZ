package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByTitle

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByTitle.GetBookByTitleUseCase

class GetBookByTitleUseCaseTest {

    private lateinit var getBookByTitleUseCase: GetBookByTitleUseCase
    private val bookRepository: BookApiRepository = mockk()

    @Before
    fun setUp() {
        getBookByTitleUseCase = GetBookByTitleUseCase(bookRepository)
    }

    @Test
    fun `should return list of books for given title from repository`() = runBlocking {
        // Arrange
        val title = "Some Book Title"
        val books = listOf(
            BookDto(id = 1, fullBookName = title, authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("zanr1","zanr2") ),
            BookDto(id = 2, fullBookName = title, authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("zanr1","zanr2") )
        )
        coEvery { bookRepository.getBookByTitle(title) } returns books

        // Act
        val result = getBookByTitleUseCase(title)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByTitle(title) }
        assert(result == books)
    }

    @Test
    fun `should return empty list when repository returns empty list for given title`() = runBlocking {
        // Arrange
        val title = "Nonexistent Book Title"
        val emptyList = emptyList<BookDto>()
        coEvery { bookRepository.getBookByTitle(title) } returns emptyList

        // Act
        val result = getBookByTitleUseCase(title)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByTitle(title) }
        assert(result == emptyList)
    }
}