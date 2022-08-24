package com.example.daiily_emo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface CalDao {  // 데이터베이스 접근 후 항목 추가, 삭제, 수정 등을 해주는 함수 모음

    @Query("SELECT * FROM todo")
    fun getTodoAll() : List<CalData>  // 모든 일정을 불러와줌(날짜 상관없이)

    @Query("SELECT * FROM todo WHERE :date >= startDate and :date <= endDate")
    fun getTodoToday(date : Date) : List<CalData>  // 매개변수 date에 쓴 날짜가 DB에 저장된 일정의 시작날짜, 끝 날짜 사이에 있으면 해당 일정을 불러옴

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(vararg CalData: CalData)  // 일정 추가

    @Query("UPDATE todo set title = :title, startDate = :startDate, endDate = :endDate, place = :place, memo = :memo WHERE id = :id")
    fun update(id : Int, title : String, startDate : Date, endDate : Date, place : String?, memo : String?)  // 매개변수로 모든 정보를 수정해줌

    @Query("DELETE FROM todo WHERE id = :id")
    fun delete(id : Int)  // 일정 삭제
}