package rs.ac.metropolitan.cs330_pz.data.repository

import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.data.remote.BooksApi
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Inject

class BookApiRepositoryImpl @Inject constructor(private val api:BooksApi):BookApiRepository {
    override suspend fun getAllBooks(): List<BookDto> {
        return api.getAll()
    }

    override fun getBookByID(id: Int): BookDto {
        return api.getBookById(id)
    }

    override suspend fun getBookByTitle(title: String): List<BookDto> {
        return api.getBookByTitle(title)
    }

    override suspend fun getBookByGenre(genre: String): List<BookDto> {
        return api.getBookByGenre(genre)
    }

}