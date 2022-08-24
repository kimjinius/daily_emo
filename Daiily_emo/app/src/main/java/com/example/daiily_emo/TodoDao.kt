package com.example.daiily_emo
import android.widget.TextView
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface TodoDao {  // 데이터베이스 접근 후 항목 추가, 삭제, 수정 등을 해주는 함수 모음

    @Query("SELECT * FROM todolist")
    fun getTodoAll() : List<TodoData>  // 모든 일정을 불러와줌(날짜 상관없이)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(vararg TodoData : TodoData)  // 일정 추가

    @Query("UPDATE todolist set taskTitle = :taskTitle, dday = :dday, percent = :percent WHERE id = :id")
    fun update(id: Int, taskTitle: String, dday: Date, percent: Int?)  // 매개변수로 모든 정보를 수정해줌

    @Query("DELETE FROM todolist WHERE id = :id")
    fun delete(id : Int)  // 일정 삭제

}