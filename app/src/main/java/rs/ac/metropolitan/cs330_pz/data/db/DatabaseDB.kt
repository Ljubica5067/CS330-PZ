package rs.ac.metropolitan.cs330_pz.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rs.ac.metropolitan.cs330_pz.data.dao.BookDao
import rs.ac.metropolitan.cs330_pz.data.entities.Book
import kotlin.concurrent.Volatile
@Database(entities = [Book::class],version=1)
abstract class DatabaseDB :RoomDatabase(){
    abstract fun bookDao():BookDao
}