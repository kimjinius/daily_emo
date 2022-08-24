package com.example.daiily_emo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var after_login_viewpager = findViewById<ViewPager>(R.id.dlg_date_viewpager)
        var after_login_tablayout = findViewById<TabLayout>(R.id.dlg_date_tablayout)

        val adapter = ViewpagerAdapter(supportFragmentManager)
        adapter.addFragment(CalView(), "캘린더")
        adapter.addFragment(TodoList(), "할 일")
        adapter.addFragment(Emo(), "기분")
        after_login_viewpager.adapter = adapter
        after_login_tablayout.setupWithViewPager(after_login_viewpager)
    }
}