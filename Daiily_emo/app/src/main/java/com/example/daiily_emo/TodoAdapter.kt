package com.example.daiily_emo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dinuscxj.progressbar.CircleProgressBar
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(private val context: Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var datas = listOf<TodoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_listview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val taskTitle: TextView = itemView.findViewById(R.id.taskTitle)
        private val dday: TextView = itemView.findViewById(R.id.dday)
        private val progress: CircleProgressBar = itemView.findViewById(R.id.progress)


        fun bind(item: TodoData) {
            taskTitle.text = item.taskTitle
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time.time

            val dateFormat = SimpleDateFormat("yyyyMMdd")
            var endday = (item.dday.year*10000).plus((item.dday.month+1)*100).plus(item.dday.date).toString()
            var enddate = dateFormat.parse(endday).time
            var diffDays = (enddate - today) / (24*60*60*1000)

            dday.text = "D-$diffDays"
            progress.setProgress(item.percent)

            itemView.setOnClickListener {
                var newIntent = Intent(context, TodoEdit::class.java)  // 리싸이클러뷰 항목 클릭시 ModifyTodo 화면 띄움
                newIntent.putExtra("Title", item.taskTitle)

                newIntent.putExtra("Dyear", item.dday.year)
                newIntent.putExtra("Dmonth", item.dday.month)
                newIntent.putExtra("Ddate", item.dday.date)
                newIntent.putExtra("Percent",item.percent)

                newIntent.putExtra("ID", item.id)
                context.startActivity(newIntent)  // 선택한 일정 정보들을 모두 넘겨주고 화면 띄워줌
            }
        }
    }


}
