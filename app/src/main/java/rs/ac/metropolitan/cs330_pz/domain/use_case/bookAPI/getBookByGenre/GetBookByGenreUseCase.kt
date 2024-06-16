package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByGenre

import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Inject

class GetBookByGenreUseCase @Inject constructor(private val bookRepository: BookApiRepository) {
    suspend operator fun invoke(genre: String): List<BookDto> {
        return bookRepository.getBookByGenre(genre)
    }
}