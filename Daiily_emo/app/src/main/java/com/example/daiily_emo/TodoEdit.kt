package com.example.daiily_emo
import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.widget.*
import java.util.*

class TodoEdit : Activity() {

    private var todoDb : DailyEmoDB? = null

    private var eDate : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.todo_listedit)

        var title = findViewById<TextView>(R.id.Title)
        var trash = findViewById<Button>(R.id.trash)
        var percent = findViewById<EditText>(R.id.eachieve)
        var btncan = findViewById<Button>(R.id.btncan)
        var btnok = findViewById<Button>(R.id.btnok)

        var dPicker = findViewById<DatePicker>(R.id.dPicker)

        todoDb = DailyEmoDB.getInstance(this)

        var intent = intent

        title.setText(intent.getStringExtra("Title"))
        percent.setText(intent.getIntExtra("Percent",0).toString())


        btncan.setOnClickListener {
            finish()
        }
        trash.setOnClickListener {
            todoDb?.getTodoDao()?.delete(intent.getIntExtra("ID", 0))
            todoDb?.getCalDao()?.delete(intent.getIntExtra("ID", 0))  // ID에 해당하는 일정 삭제

            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
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
            var taskA = Integer.parseInt(percent.text.toString())

            if (taskA<100){
                todoDb?.getTodoDao()?.update(intent.getIntExtra("ID", 0), title.text.toString(), eDate, taskA)
                finish()
            } else{
                todoDb?.getTodoDao()?.delete(intent.getIntExtra("ID", 0))
                finish()
            }
        }


    }
    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }
}