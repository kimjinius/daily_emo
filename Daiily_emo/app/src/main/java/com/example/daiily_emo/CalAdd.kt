package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.*
import java.util.*


@Suppress("DEPRECATION")
class CalAdd : Activity() {

    private var todoDb : DailyEmoDB? = null

    private var sDate : Date = Date()
    private var eDate : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        overridePendingTransition(R.anim.vertical_enter, R.anim.none)
        setContentView(R.layout.cal_add)

        var edtTitle = findViewById<EditText>(R.id.add_editTitle)
        var btnAccept = findViewById<Button>(R.id.btnok)
        var edtPlace= findViewById<EditText>(R.id.edt_addwhere)
        var edtmemo=findViewById<EditText>(R.id.add_edt_memo)
        var btnDate=findViewById<ImageButton>(R.id.btn_adddate)
        var btncan = findViewById<Button>(R.id.btncan)

        todoDb = DailyEmoDB.getInstance(this)  // DB 접속

        sDate.apply {
            year += 1900
            hours = 0
            minutes = 0
            seconds = 0
        }
        eDate.apply {
            year += 1900
            hours = 23
            minutes = 59
            seconds = 59
        }

        btncan.setOnClickListener {
            finish()
        }

        btnDate.setOnClickListener {
            var newIntent = Intent(applicationContext, CalSelectDate::class.java)  // 날짜 선택 창(선택한 날짜 불러옴)
            startActivityForResult(newIntent, 1)
        }

        btnAccept.setOnClickListener {
            var  getEdit = edtTitle.getText().toString()
            getEdit = getEdit.trim()

            if(getEdit.isEmpty()){
                Toast.makeText(applicationContext,"제목을 입력해주세요.",Toast.LENGTH_LONG).show()
            }else{
                var newTodo = CalData(edtTitle.text.toString(), sDate, eDate, edtPlace.text.toString(), edtmemo.text.toString())

                todoDb?.getCalDao()?.insertTodo(newTodo)  // 위에서 newTodo에 저장한 정보들을 일정 DB에 추가

                finish()
                overridePendingTransition(R.anim.none, R.anim.vertical_exit)
            }
        }
    }

    override fun onResume() {
        var tvdate=findViewById<TextView>(R.id.tv_date)
        tvdate.setText("${sDate.month+1} 월 ${sDate.date} 일 ~ ${eDate.month+1} 월 ${eDate.date} 일")
        super.onResume()
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  // 선택한 날짜 불러올 함수
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1 && resultCode === RESULT_OK) {
            sDate.apply {
                year = data!!.getIntExtra("sYear", 0)
                month = data!!.getIntExtra("sMonth", 0)
                date = data!!.getIntExtra("sDay", 0)
                hours = 0
                minutes = 0
                seconds = 0
            }
            eDate.apply {
                year = data!!.getIntExtra("eYear", 0)
                month = data!!.getIntExtra("eMonth", 0)
                date = data!!.getIntExtra("eDay", 0)
                hours = 23
                minutes = 59
                seconds = 59
            }
        } else {

        }
    }
}