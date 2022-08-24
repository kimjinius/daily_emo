package com.example.daiily_emo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "emolist")
data class EmoData(
    val date : String,
    val img: Int,
    val memo : String?
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}