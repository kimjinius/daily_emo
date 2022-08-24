package com.example.daiily_emo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoList : Fragment() {

    private var todoDb : DailyEmoDB? = null

    lateinit var rvAdapter : TodoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.todolist, container, false).apply {

            var recycler1 = findViewById<RecyclerView>(R.id.Recycler1)
            var floatingButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)


            recycler1 = initRecycler(recycler1)

            floatingButton.setOnClickListener{
                var newIntent = Intent(context, TodoAdd::class.java)
                startActivity(newIntent)
            }

        }
    }

    override fun onResume() {
        var recyTodo = this.requireView().findViewById<RecyclerView>(R.id.Recycler1)
        recyTodo = initRecycler(recyTodo)  // 화면 다시 불러왔을때도 일정 불러오기
        super.onResume()
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

    private fun initRecycler(rv : RecyclerView) : RecyclerView {  // 리싸이클러뷰 불러오기 함수
        rvAdapter = activity?.let { TodoAdapter(it) }!!
        todoDb = DailyEmoDB.getInstance(requireActivity())  // DB 접근

        rvAdapter.datas = todoDb?.getTodoDao()?.getTodoAll()!!

        rvAdapter.notifyDataSetChanged()

        rv.adapter = rvAdapter  // 어댑터 적용

        return rv  // 적용된 리싸이클러뷰 리턴
    }

}