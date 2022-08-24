package com.example.daiily_emo

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class SaturdayDecorator : DayViewDecorator {

    private val cal = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(cal)
        var weekDay = cal.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object:ForegroundColorSpan(Color.BLUE){})
    }

}

class SundayDecorator : DayViewDecorator {

    private val cal = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(cal)
        var weekDay = cal.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object:ForegroundColorSpan(Color.RED){})
    }

}

class SelectDecorator(context : Context) : DayViewDecorator {

    private val draw = context.resources.getDrawable(R.drawable.cal_selector)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(draw)
    }

}


// RecyclerView Decoration
class RecyDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = 10
    }
}