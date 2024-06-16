package rs.ac.metropolitan.cs330_pz.domain.repository

import rs.ac.metropolitan.cs330_pz.data.entities.Book

interface BookRepository {
    suspend fun insert(book: Book)
    suspend fun update(book: Book)
    suspend fun delete(id:Int)
    suspend fun getAllBooks(): List<Book>?
    suspend fun getBookByTitle(title: String): Book?
    suspend fun updateBookProgress(id:Int,progress:Int)
    suspend fun updateFavourite(id:Int,favourite:Boolean)
    suspend fun getBookById(id:Int):Book
    suspend fun getAllFav():List<Book>
}