package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookByTitle

import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Inject

class GetBookByTitleUseCase @Inject constructor(private val bookRepository: BookApiRepository) {
    suspend operator fun invoke(title: String): List<BookDto> {
        return bookRepository.getBookByTitle(title)
    }
}