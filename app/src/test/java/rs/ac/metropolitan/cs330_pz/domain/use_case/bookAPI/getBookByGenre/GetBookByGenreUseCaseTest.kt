package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByGenre
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByGenre.GetBookByGenreUseCase

class GetBookByGenreUseCaseTest {

    private lateinit var getBookByGenreUseCase: GetBookByGenreUseCase
    private val bookRepository: BookApiRepository = mockk()

    @Before
    fun setUp() {
        getBookByGenreUseCase = GetBookByGenreUseCase(bookRepository)
    }

    @Test
    fun `should return list of books for given genre from repository`() = runBlocking {
        // Arrange
        val genre = "Science Fiction"
        val books = listOf(
            BookDto(id = 1, fullBookName = "Book One", authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("Science Fiction","zanr2") ),
            BookDto(id = 2, fullBookName = "Book One", authorName = "Author One", description = "opis", coverImage = "slika", genres = listOf("Science Fiction","zanr2") )
        )
        coEvery { bookRepository.getBookByGenre(genre) } returns books

        // Act
        val result = getBookByGenreUseCase(genre)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByGenre(genre) }
        assert(result == books)
    }

    @Test
    fun `should return empty list when repository returns empty list for given genre`() = runBlocking {
        // Arrange
        val genre = "Nonexistent Genre"
        val emptyList = emptyList<BookDto>()
        coEvery { bookRepository.getBookByGenre(genre) } returns emptyList

        // Act
        val result = getBookByGenreUseCase(genre)

        // Assert
        coVerify(exactly = 1) { bookRepository.getBookByGenre(genre) }
        assert(result == emptyList)
    }
}