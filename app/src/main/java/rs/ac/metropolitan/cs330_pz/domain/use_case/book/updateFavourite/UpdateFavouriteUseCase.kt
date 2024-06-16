package rs.ac.metropolitan.cs330_pz.domain.use_case.book.updateFavourite

import rs.ac.metropolitan.cs330_pz.data.repository.BookRepositoryImpl
import javax.inject.Inject

class UpdateFavouriteUseCase @Inject constructor(private val bookRepository: BookRepositoryImpl) {
    suspend operator fun invoke(bookId: Int, favourite: Boolean) {
        bookRepository.updateFavourite(bookId, favourite)
    }
}