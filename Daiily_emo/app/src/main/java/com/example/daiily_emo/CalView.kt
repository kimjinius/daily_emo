package com.example.daiily_emo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class CalView : Fragment() {

    private var calDb : DailyEmoDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.calendar, null).apply {
            var calView = findViewById<MaterialCalendarView>(R.id.calView)

            calView.selectedDate = CalendarDay.today()

            val sunday = SundayDecorator()
            val saturday = SaturdayDecorator()
            val select = SelectDecorator(context)

            calView.addDecorators(sunday, saturday, select)

            var selDate = calView.selectedDate.date

            calView.setOnDateChangedListener { widget, date, selected ->
                var intent = Intent(context, CalTodo::class.java)
                intent.putExtra("Year", date.year)
                intent.putExtra("Month", date.month)
                intent.putExtra("Day", date.day)
                startActivity(intent)
            }

            var calDay = CalendarDay.today()

            var calList = mutableListOf<CalendarDay>()

            calDay.calendar.timeInMillis += 24*60*60*1000

            calDb = DailyEmoDB.getInstance(context)  // DB 접근

            var dbList = calDb?.getCalDao()?.getTodoAll()!!
        }
    }
}