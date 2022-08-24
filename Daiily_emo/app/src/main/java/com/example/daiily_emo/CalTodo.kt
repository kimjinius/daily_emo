package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class CalTodo : Activity() {

    private var calDb : DailyEmoDB? = null

    lateinit var rvAdapter : CalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        overridePendingTransition(R.anim.vertical_enter, R.anim.none)
        setContentView(R.layout.cal_daytodo)

        var btnAdd = findViewById<FloatingActionButton>(R.id.floatingAction)
        var btnBack = findViewById<Button>(R.id.btncan)
        var recyTodo = findViewById<RecyclerView>(R.id.recyTodo)

        val recyDeco = RecyDecorator()
        recyTodo.addItemDecoration(recyDeco)
        recyTodo.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        recyTodo = initRecycler(recyTodo)  // 화면 시작시 리싸이클러뷰에 선택 날짜 일정 불러옴

        btnAdd.setOnClickListener {
            var newIntent = Intent(applicationContext, CalAdd::class.java)
            startActivity(newIntent)
        }

        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }

    }

    override fun onResume() {
        var recyTodo = findViewById<RecyclerView>(R.id.recyTodo)
        recyTodo = initRecycler(recyTodo)  // 화면 다시 불러왔을때도 일정 불러오기
        super.onResume()
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

    private fun initRecycler(rv : RecyclerView) : RecyclerView {  // 리싸이클러뷰 불러오기 함수
        rvAdapter = CalAdapter(this)
        calDb = DailyEmoDB.getInstance(this)  // DB 접근

        var todo = listOf<CalData>()

        var today = Date()

        today.apply {  // 캘린더뷰(메인화면)에서 선택한 날짜 불러옴
            var intent = intent
            year = intent.getIntExtra("Year", 0)
            month = intent.getIntExtra("Month", 0)
            date = intent.getIntExtra("Day", 0)
        }

        rvAdapter.datas = calDb?.getCalDao()?.getTodoToday(today)!!  // 선택한 날짜에 해당하는 일정 불러옴

        rvAdapter.notifyDataSetChanged()

        rv.adapter = rvAdapter  // 어댑터 적용

        return rv  // 적용된 리싸이클러뷰 리턴
    }
}