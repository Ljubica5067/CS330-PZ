package rs.ac.metropolitan.cs330_pz.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import rs.ac.metropolitan.cs330_pz.common.Constants
import rs.ac.metropolitan.cs330_pz.data.dao.BookDao
import rs.ac.metropolitan.cs330_pz.data.db.DatabaseDB
import rs.ac.metropolitan.cs330_pz.data.remote.BooksApi
import rs.ac.metropolitan.cs330_pz.data.repository.BookApiRepositoryImpl
import rs.ac.metropolitan.cs330_pz.domain.repository.BookApiRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookApi():BooksApi
    {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookApiRepository(api:BooksApi):BookApiRepository{
        return BookApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application):Context
    {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DatabaseDB {
        return Room.databaseBuilder(
            appContext,
            DatabaseDB::class.java,
            "book.db"
        ).build()
    }

    @Provides
    fun provideBookDao(database: DatabaseDB): BookDao {
        return database.bookDao()
    }
}