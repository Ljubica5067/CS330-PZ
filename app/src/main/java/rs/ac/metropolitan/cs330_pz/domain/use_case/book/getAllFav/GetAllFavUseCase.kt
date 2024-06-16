package rs.ac.metropolitan.cs330_pz.domain.use_case.book.getAllFav

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject

class GetAllFavUseCase @Inject constructor(private val bookRepository: BookRepository) {
    suspend operator fun invoke(): List<Book> {
        return bookRepository.getAllFav()
    }
}
