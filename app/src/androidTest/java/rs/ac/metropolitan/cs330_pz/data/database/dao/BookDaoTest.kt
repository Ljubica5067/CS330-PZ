package rs.ac.metropolitan.cs330_pz.data.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_pz.data.dao.BookDao
import rs.ac.metropolitan.cs330_pz.data.db.DatabaseDB
import rs.ac.metropolitan.cs330_pz.data.entities.Book

@RunWith(AndroidJUnit4::class)
@SmallTest
class BookDaoTest {



    private lateinit var database: DatabaseDB
    private lateinit var bookDao: BookDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseDB::class.java
        ).allowMainThreadQueries().build()
        bookDao = database.bookDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertBookAndGetById() = runBlocking {
        val book =Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)

        val loaded = bookDao.getBookById(1)
        assert(loaded == book)
    }

    @Test
    fun updateBook() = runBlocking {
        val book = Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)

        val updatedBook = book.copy(title = "Updated Test Book")
        bookDao.update(updatedBook)

        val loaded = bookDao.getBookById(1)
        assert(loaded?.title == "Updated Test Book")
    }

    @Test
    fun deleteBook() = runBlocking {
        val book = Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)
        bookDao.deleteBook(1)

        val loaded = bookDao.getBookById(1)
        assert(loaded == null)
    }

    @Test
    fun getAllBooks() = runBlocking {
        val book1 = Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        val book2 = Book(id = 2, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book1)
        bookDao.insert(book2)

        val books = bookDao.getAllBooks()
        assert(books.size == 2)
        assert(books.contains(book1))
        assert(books.contains(book2))
    }

    @Test
    fun getBookByTitle() = runBlocking {
        val book = Book(id = 2, title = "Unique Title", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)

        val loaded = bookDao.getBookByTitle("Unique Title")
        assert(loaded == book)
    }

    @Test
    fun updateBookProgress() = runBlocking {
        val book = Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)

        bookDao.updateBookProgress(1, 50)
        val loaded = bookDao.getBookById(1)
        assert(loaded?.progress == 50)
    }

    @Test
    fun getFavouriteBooks() = runBlocking {
        val book1 = Book(id = 1, title = "Book Two", image = "slika2", favourite = true, progress = 1)
        val book2 = Book(id = 2, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book1)
        bookDao.insert(book2)

        val favouriteBooks = bookDao.getFavouriteBooks()
        assert(favouriteBooks.size == 1)
        assert(favouriteBooks[0] == book1)
    }

    @Test
    fun updateFavourite() = runBlocking {
        val book = Book(id = 1, title = "Book Two", image = "slika2", favourite = false, progress = 1)
        bookDao.insert(book)

        bookDao.updateFavourite(1, true)
        val loaded = bookDao.getBookById(1)
        assert(loaded?.favourite == true)
    }
}