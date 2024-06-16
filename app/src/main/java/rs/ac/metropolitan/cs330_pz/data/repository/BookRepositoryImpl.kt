package rs.ac.metropolitan.cs330_pz.data.repository

import rs.ac.metropolitan.cs330_pz.data.dao.BookDao
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import rs.ac.metropolitan.cs330_pz.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDao:BookDao): BookRepository {
    override suspend fun insert(book: Book){
        return bookDao.insert(book)
    }




    override suspend fun update(book: Book) {
        bookDao.update(book)
    }

    override suspend fun delete(id:Int) {
        bookDao.deleteBook(id)
    }

    override suspend fun getAllBooks(): List<Book>? {
        return bookDao.getAllBooks()
    }

    override suspend fun getBookByTitle(title: String): Book? {
        return bookDao.getBookByTitle(title)
    }

    override suspend fun updateBookProgress(id:Int,progress:Int)
    {
        bookDao.updateBookProgress(id,progress)
    }

    override suspend fun updateFavourite(id: Int, favourite: Boolean) {
        bookDao.updateFavourite(id,favourite)
    }

    override suspend fun getBookById(id: Int):Book {
        return bookDao.getBookById(id)
    }

    override suspend fun getAllFav(): List<Book> {
        return bookDao.getFavouriteBooks()
    }
}