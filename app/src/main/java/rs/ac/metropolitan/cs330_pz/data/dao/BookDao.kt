package rs.ac.metropolitan.cs330_pz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import rs.ac.metropolitan.cs330_pz.data.entities.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteBook(id: Int)

    @Query("SELECT * FROM books WHERE title = :title")
    suspend fun getBookByTitle(title: String): Book?

    @Query("UPDATE books SET progress = :progress WHERE id = :bookId")
    suspend fun updateBookProgress(bookId: Int, progress: Int)

    @Query("SELECT * FROM books WHERE favourite= 1")
    suspend fun getFavouriteBooks():List<Book>

    @Query("UPDATE books SET favourite=:fave WHERE id=:bookId")
    suspend fun updateFavourite(bookId: Int,fave:Boolean)

    @Query("SELECT * FROM books where id=:bookId")
        suspend fun getBookById(bookId:Int):Book


}