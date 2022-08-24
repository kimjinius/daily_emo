package com.example.daiily_emo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo") // 데이터베이스 테이블
data class CalData(  // 테이블 항목
    var title : String,  // 일정 제목
    var startDate : Date,  // 일정 시작날짜
    var endDate : Date,  // 일정 끝나는 날짜
    var place : String?,  // 장소 (null 가능)
    var memo : String?  // 메모 (null 가능)
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0  // 키값 자동생성 (지정 안해도 됨)
}