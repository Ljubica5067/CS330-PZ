package rs.ac.metropolitan.cs330_pz.data.remote.dto

data class BookDto(
    val id:Int,
    val fullBookName:String,
    val authorName:String,
    val description:String,
    val coverImage:String,
    val genres: List<String>
)
