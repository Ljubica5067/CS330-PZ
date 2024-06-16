package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookByTitle

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksByTitleUseCase @Inject constructor(private val bookRepository: BookRepository) {
    suspend operator fun invoke(title: String): Book? {
        return bookRepository.getBookByTitle(title)
    }
}