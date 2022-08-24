package com.example.daiily_emo

import android.app.Activity
import android.app.TabActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import java.util.*

@Suppress("DEPRECATION")
class CalSelectDate : TabActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.cal_selectdate)

        var dpStart = findViewById<DatePicker>(R.id.dpStart)
        var dpEnd = findViewById<DatePicker>(R.id.dpEnd)
        var btnCancel = findViewById<Button>(R.id.btnCancel)
        var btnOK = findViewById<Button>(R.id.btnOk)

        var tabHost = this.tabHost

        var tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("시작")
        tabSpec1.setContent(R.id.dpStart)
        tabHost.addTab(tabSpec1)

        var tabSpec2 = tabHost.newTabSpec("TAG2").setIndicator("종료")
        tabSpec2.setContent(R.id.dpEnd)
        tabHost.addTab(tabSpec2)

        tabHost.currentTab = 0

        var today = Date()

        var intent = intent
        intent.getIntExtra("startYear", 0)

        dpStart.updateDate(intent.getIntExtra("startYear", 1900+today.year), intent.getIntExtra("startMonth", today.month), intent.getIntExtra("startDay", today.date))
        dpEnd.updateDate(intent.getIntExtra("endYear", 1900+today.year), intent.getIntExtra("endMonth", today.month), intent.getIntExtra("endDay", today.date))

        var start = Date()
        var end = Date()

        dpStart.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            start.apply {
                this.year = year-1900
                this.month = monthOfYear
                this.date = dayOfMonth
            }

            if(start > end) {
                dpEnd.updateDate(year, monthOfYear, dayOfMonth)
            }
        }

        dpEnd.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            end.apply {
                this.year = year-1900
                this.month = monthOfYear
                this.date = dayOfMonth
            }

            if(start > end) {
                dpStart.updateDate(year, monthOfYear, dayOfMonth)
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }

        btnOK.setOnClickListener {
            var intent = Intent()
            intent.putExtra("sYear", dpStart.year)
            intent.putExtra("sMonth", dpStart.month)
            intent.putExtra("sDay", dpStart.dayOfMonth)

            intent.putExtra("eYear", dpEnd.year)
            intent.putExtra("eMonth", dpEnd.month)
            intent.putExtra("eDay", dpEnd.dayOfMonth)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}