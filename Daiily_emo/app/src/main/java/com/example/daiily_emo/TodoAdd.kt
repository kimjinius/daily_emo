package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptIntrinsicYuvToRGB
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array.set
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@Suppress("DEPRECATION")
class TodoAdd : Activity() {

    private var todoDb : DailyEmoDB? = null

    private var eDate : Date = Date()
    private var eeDate : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.todo_add)

        var eTitle = findViewById<EditText>(R.id.eTitle)
        var dPicker = findViewById<DatePicker>(R.id.dPicker)
        var eachieve = findViewById<EditText>(R.id.eachieve)
        var btncan = findViewById<Button>(R.id.btncan)
        var btnok = findViewById<Button>(R.id.btnok)

        todoDb = DailyEmoDB.getInstance(this)


        dPicker.minDate = System.currentTimeMillis()

        btncan.setOnClickListener {
            finish()
        }

        btnok.setOnClickListener {
            eDate.apply {
                year = dPicker.year
                month = dPicker.month
                date = dPicker.dayOfMonth
                hours = 0
                minutes = 0
                seconds = 0
            }
            eeDate.apply {
                year = dPicker.year
                month = dPicker.month
                date = dPicker.dayOfMonth
                hours = 23
                minutes = 59
                seconds = 59
            }

            if (eachieve.length()==0 ){
                var newTodo = TodoData(eTitle.text.toString(), eDate, 0)
                todoDb?.getTodoDao()?.insertTodo(newTodo)

                var newTodo1 = CalData(eTitle.text.toString()+" 마감", eDate, eeDate, null, null)

                todoDb?.getCalDao()?.insertTodo(newTodo1)  // 위에서 newTodo에 저장한 정보들을 일정 DB에 추가

                finish()
                overridePendingTransition(R.anim.none, R.anim.vertical_exit)

            } else{
                var taskA = Integer.parseInt(eachieve.text.toString())
                var newTodo = TodoData(eTitle.text.toString(), eDate, taskA)
                todoDb?.getTodoDao()?.insertTodo(newTodo)

                var newTodo1 = CalData(eTitle.text.toString()+" 마감", eDate, eeDate, null, null)

                todoDb?.getCalDao()?.insertTodo(newTodo1)  // 위에서 newTodo에 저장한 정보들을 일정 DB에 추가

                finish()
                overridePendingTransition(R.anim.none, R.anim.vertical_exit)
            }
        }
    }
    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

}