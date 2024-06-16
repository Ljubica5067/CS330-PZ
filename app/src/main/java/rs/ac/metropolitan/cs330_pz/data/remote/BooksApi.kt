package rs.ac.metropolitan.cs330_pz.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto

interface BooksApi {
    @GET("/books")
    suspend fun getAll():List<BookDto>

    @GET("/books/id")
    fun getBookById(@Path("id")id:Int):BookDto

    @GET("/books")
    suspend fun getBookByGenre(
        @Query("genres_like") genre:String="",
        @Query("title_regex_flags") flags:String="i"
    ):List<BookDto>

    @GET("/books")
    suspend fun getBookByTitle(
        @Query("fullBookName_like") title:String="",
        @Query("title_regex_flags") flags:String="i"
    ):List<BookDto>


}