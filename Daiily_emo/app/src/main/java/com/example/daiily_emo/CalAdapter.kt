package com.example.daiily_emo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalAdapter(private val context: Context) : RecyclerView.Adapter<CalAdapter.ViewHolder>() {

    var datas = listOf<CalData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cal_listview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTodoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)

        fun bind(item: CalData) {
            tvTodoTitle.text = item.title

            itemView.setOnClickListener {
                var newIntent = Intent(context, CalEdit::class.java)  // 리싸이클러뷰 항목 클릭시 ModifyTodo 화면 띄움
                newIntent.putExtra("Title", item.title)
                newIntent.putExtra("Place", item.place)

                newIntent.putExtra("startYear", item.startDate.year)
                newIntent.putExtra("startMonth", item.startDate.month)
                newIntent.putExtra("startDay", item.startDate.date)

                newIntent.putExtra("endYear", item.endDate.year)
                newIntent.putExtra("endMonth", item.endDate.month)
                newIntent.putExtra("endDay", item.endDate.date)

                newIntent.putExtra("Memo", item.memo)

                newIntent.putExtra("ID", item.id)
                context.startActivity(newIntent)  // 선택한 일정 정보들을 모두 넘겨주고 화면 띄워줌
            }
        }
    }


}

