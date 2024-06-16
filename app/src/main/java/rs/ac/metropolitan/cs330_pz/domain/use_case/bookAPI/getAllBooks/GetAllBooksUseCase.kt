package rs.ac.metropolitan.cs330_pz.domain.use_case.bookAPI.getAllBooks

import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Inject

    class GetAllBooksUseCase @Inject constructor(private val bookRepository: BookApiRepository) {
        suspend operator fun invoke(): List<BookDto> {
            return bookRepository.getAllBooks()
        }
    }