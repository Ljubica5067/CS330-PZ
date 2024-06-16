package rs.ac.metropolitan.cs330_pz.domain.use_case.book.delete

import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject

class DeleteUseCase @Inject constructor(private val bookRepository: BookRepositoryImpl) {
    suspend operator fun invoke(bookId: Int) {
        bookRepository.delete(bookId)
    }
}