package rs.ac.metropolitan.cs330_pz.domain.use_case.book.update

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val bookRepository: BookRepository) {
    suspend operator fun invoke(book: Book) {
        bookRepository.update(book)
    }
}