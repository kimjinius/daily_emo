package com.example.daiily_emo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var handler = Handler()
        handler.postDelayed(Runnable(){
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}