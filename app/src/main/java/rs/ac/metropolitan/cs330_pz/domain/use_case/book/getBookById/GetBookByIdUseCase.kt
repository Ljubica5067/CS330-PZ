package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getBookById

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val bookRepository: BookRepositoryImpl) {
    suspend operator fun invoke(id: Int): Book {
        return bookRepository.getBookById(id)
    }
}