package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import java.util.*


class CalEdit : Activity() {

    private var todoDb : DailyEmoDB? = null

    private var sDate : Date = Date()
    private var eDate : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        overridePendingTransition(R.anim.vertical_enter, R.anim.none)
        setContentView(R.layout.cal_listedit)

        var edtTitle = findViewById<EditText>(R.id.mod_editTitle)
        var edtPlace = findViewById<EditText>(R.id.edt_modwhere)
        var btnDate = findViewById<ImageButton>(R.id.btn_moddate)
        var edtMemo = findViewById<EditText>(R.id.mod_edt_memo)
        var btnDelete = findViewById<Button>(R.id.trash)
        var btnRevert = findViewById<Button>(R.id.btncan)
        var btnModify = findViewById<Button>(R.id.btnok)

        todoDb = DailyEmoDB.getInstance(this)  // DB 접근

        var intent = intent

        edtTitle.setText(intent.getStringExtra("Title"))
        edtPlace.setText(intent.getStringExtra("Place"))
        edtMemo.setText(intent.getStringExtra("Memo"))

        var startYear = intent.getIntExtra("startYear", 0)
        var startMonth = intent.getIntExtra("startMonth", 0)
        var startDay = intent.getIntExtra("startDay", 0)
        var endYear = intent.getIntExtra("endYear", 0)
        var endMonth = intent.getIntExtra("endMonth", 0)
        var endDay = intent.getIntExtra("endDay", 0)
        //리싸이클러뷰로부터 선택한 일정 정보 받아옴

        sDate.apply {
            year = startYear
            month = startMonth
            date = startDay
        }
        eDate.apply {
            year = endYear
            month = endMonth
            date = endDay
        }

        btnDate.setOnClickListener {
            var newIntent = Intent(applicationContext, CalSelectDate::class.java)  // 날짜 선택 버튼 클릭시
            newIntent.putExtra("startYear", startYear)
            newIntent.putExtra("startMonth", startMonth)
            newIntent.putExtra("startDay", startDay)

            newIntent.putExtra("endYear", endYear)
            newIntent.putExtra("endMonth", endMonth)
            newIntent.putExtra("endDay", endDay)
            startActivityForResult(newIntent, 2)  // 원래 저장되어있던 시작,종료 날짜를 전달하고 선택 화면 띄움
        }

        btnRevert.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }
        btnModify.setOnClickListener {
            todoDb?.getCalDao()?.update(intent.getIntExtra("ID", 0), edtTitle.text.toString(), sDate, eDate, edtPlace.text.toString(), edtMemo.text.toString())
            // ID에 해당하는 일정(리싸이클러뷰에서 선택한 일정) 수정

            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }

        btnDelete.setOnClickListener {
            todoDb?.getCalDao()?.delete(intent.getIntExtra("ID", 0))  // ID에 해당하는 일정 삭제

            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  // 선택한 시작,종료 날짜 받아옴
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 2 && resultCode === RESULT_OK) {
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