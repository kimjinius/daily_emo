package com.example.daiily_emo

import android.content.Context
import androidx.room.*

@Database(entities = [CalData::class, TodoData::class, EmoData::class], version = 1)
@TypeConverters(CalConverter::class)
abstract class DailyEmoDB : RoomDatabase() {  // 데이터베이스 접근, 접근해제를 위한 클래스
    abstract fun getCalDao() : CalDao  // CalDao의 함수들을 사용할 수 있도록 해줌
    abstract fun getTodoDao() : TodoDao  // TodoDao의 함수들을 사용할 수 있도록 해줌
    abstract fun getEmoDao() : EmoDao  // EmoDao의 함수들을 사용할 수 있도록 해줌

    companion object{

        private var INSTANCE : DailyEmoDB? = null

        @Synchronized
        fun getInstance(context : Context) : DailyEmoDB? {  // 데이터베이스 접근 메소드
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, DailyEmoDB::class.java, "DailyEmo_db").allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        fun destroyInstance() {  // 데이터베이스 접근해제 메소드
            INSTANCE = null
        }
    }
}