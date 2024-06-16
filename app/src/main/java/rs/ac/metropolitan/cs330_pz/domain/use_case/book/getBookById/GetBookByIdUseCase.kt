package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById

import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val bookRepository: BookRepositoryImpl) {
    suspend operator fun invoke(bookId: Int): Boolean {
        return bookRepository.getBookById(bookId) != null
    }
}