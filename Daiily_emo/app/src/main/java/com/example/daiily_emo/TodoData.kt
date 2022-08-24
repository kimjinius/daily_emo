package com.example.daiily_emo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todolist")
class TodoData(
    val taskTitle:String,
    val dday:Date,
    val percent: Int){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}