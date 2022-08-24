package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.zip.Inflater

@Suppress("DEPRECATION")
class Emo : Fragment() {
    private var emoDb : DailyEmoDB? = null
    lateinit var rvAdapter : EmoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.emo, null).apply {
            var recycler1 = findViewById<RecyclerView>(R.id.recView)
            var floatingButton = findViewById<FloatingActionButton>(R.id.btnAdd)

            val gridLayManager = GridLayoutManager(context, 5)
            recycler1.layoutManager = gridLayManager
            recycler1.setHasFixedSize(true)

            initRecycler(recycler1)

            floatingButton.setOnClickListener{
                var newIntent = Intent(context, EmoAdd::class.java)
                startActivity(newIntent)
            }
        }
    }

    override fun onResume() {
        var recyTodo = this.requireView().findViewById<RecyclerView>(R.id.recView)
        initRecycler(recyTodo)  // 화면 다시 불러왔을때도 일정 불러오기
        super.onResume()
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

    private fun initRecycler(rv : RecyclerView) : RecyclerView {  // 리싸이클러뷰 불러오기 함수
        rvAdapter = context?.let { EmoAdapter(it) }!!
        emoDb = DailyEmoDB.getInstance(requireActivity())  // DB 접근

        rvAdapter.datas = emoDb?.getEmoDao()?.getTodoAll()!!

        rvAdapter.notifyDataSetChanged()

        rv.adapter = rvAdapter  // 어댑터 적용

        return rv  // 적용된 리싸이클러뷰 리턴
    }


}