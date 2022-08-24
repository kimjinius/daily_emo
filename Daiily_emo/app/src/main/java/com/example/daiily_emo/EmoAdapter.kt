package com.example.daiily_emo

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class EmoAdapter(private val context: Context) : RecyclerView.Adapter<EmoAdapter.ViewHolder>() {

    var datas = listOf<EmoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.emo_listview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgBtn: ImageButton = itemView.findViewById(R.id.imgBtn)

        fun bind(item: EmoData) {

            imgBtn.setImageResource(item.img)

            imgBtn.setOnClickListener {
                var newIntent = Intent(context, EmoEdit::class.java)  // 리싸이클러뷰 항목 클릭시 ModifyTodo 화면 띄움
                newIntent.putExtra("DATE", item.date)
                newIntent.putExtra("IMAGE", item.img)
                newIntent.putExtra("MEMO",item.memo)
                newIntent.putExtra("ID", item.id)
                context.startActivity(newIntent)  // 선택한 일정 정보들을 모두 넘겨주고 화면 띄워줌
            }
        }
    }
}
