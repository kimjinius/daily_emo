package com.example.daiily_emo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmoDao {  // 데이터베이스 접근 후 항목 추가, 삭제, 수정 등을 해주는 함수 모음

    @Query("SELECT * FROM emolist")
    fun getTodoAll() : List<EmoData>  // 모든 일정을 불러와줌(날짜 상관없이)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(vararg CalEntity: EmoData)  // 일정 추가

    @Query("UPDATE emolist set date = :date, img = :img, memo = :memo WHERE id = :id")
    fun update(id: Int, date: String, img: Int, memo: String?)  // 매개변수로 모든 정보를 수정해줌

    @Query("DELETE FROM emolist WHERE id = :id")
    fun delete(id : Int)  // 일정 삭제

}