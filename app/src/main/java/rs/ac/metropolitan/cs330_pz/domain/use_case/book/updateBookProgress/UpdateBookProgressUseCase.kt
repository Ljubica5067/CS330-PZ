package rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateBookProgress

import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject

class UpdateBookProgressUseCase @Inject constructor(private val bookRepository: BookRepository) {
    suspend operator fun invoke(id: Int, progress: Int) {
        bookRepository.updateBookProgress(id, progress)
    }
}