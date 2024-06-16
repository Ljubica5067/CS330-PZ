package rs.ac.metropolitan.cs330_pz.domain.use_case.book.insert

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject

class InsertUseCase @Inject constructor(private val bookRepository: BookRepositoryImpl) {
    suspend operator fun invoke(book: Book) {
        bookRepository.insert(book)
    }
}