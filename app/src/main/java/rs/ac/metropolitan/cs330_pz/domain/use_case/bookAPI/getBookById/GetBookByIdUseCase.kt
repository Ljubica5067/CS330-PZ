package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getBookById

import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val bookRepository: BookApiRepository) {
    operator fun invoke(id: Int): BookDto {
        return bookRepository.getBookByID(id)
    }
}