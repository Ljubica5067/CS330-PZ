package rs.ac.metropolitan.cs330_pz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="books")
data class Book(
    @PrimaryKey val id: Int,
    val image:String,
    val title: String,
    val progress: Int,
    val favourite:Boolean
)


