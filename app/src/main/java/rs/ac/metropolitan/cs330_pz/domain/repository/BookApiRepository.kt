package rs.ac.metropolitan.cs330_pz.domain.repository

import rs.ac.metropolitan.cs330_pz.data.remote.dto.BookDto

interface BookApiRepository {

    suspend fun getAllBooks(): List<BookDto>

    fun getBookByID(id:Int):BookDto

    suspend fun getBookByTitle(title:String):List<BookDto>

    suspend fun getBookByGenre(genre:String):List<BookDto>

}