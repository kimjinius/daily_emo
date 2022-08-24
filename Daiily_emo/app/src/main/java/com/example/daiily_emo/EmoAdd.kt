package com.example.daiily_emo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.*
import java.util.*

@Suppress("DEPRECATION")
class EmoAdd : Activity() {

    private var emoDb : DailyEmoDB? = null
    var datas = listOf<EmoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.emo_add)

        var btncan = findViewById<Button>(R.id.btncan)
        var btnok = findViewById<Button>(R.id.btnok)

        emoDb = DailyEmoDB.getInstance(this)

        btncan.setOnClickListener {
            finish()
        }

        btnok.setOnClickListener {
            var dPicker = findViewById<DatePicker>(R.id.datePicker)
            var date = "${dPicker.year}" + "년 " + "${dPicker.month + 1}" + "월 " + "${dPicker.dayOfMonth}" + "일"

            var etMemo = findViewById<EditText>(R.id.etMemo)

            var radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            var radioBtnId = radioGroup.checkedRadioButtonId

            if(radioBtnId == R.id.emo1){
                var newEmo = EmoData(date, R.drawable.emo1, etMemo.text.toString())
                emoDb?.getEmoDao()?.insertTodo(newEmo)
                finish()
            } else if (radioBtnId == R.id.emo2){
                var newEmo = EmoData(date, R.drawable.emo2 ,etMemo.text.toString())
                emoDb?.getEmoDao()?.insertTodo(newEmo)
                finish()
            } else if (radioBtnId == R.id.emo3){
                var newEmo = EmoData(date, R.drawable.emo3 ,etMemo.text.toString())
                emoDb?.getEmoDao()?.insertTodo(newEmo)
                finish()
            } else if (radioBtnId == R.id.emo4){
                var newEmo = EmoData(date, R.drawable.emo4 ,etMemo.text.toString())
                emoDb?.getEmoDao()?.insertTodo(newEmo)
                finish()
            } else if (radioBtnId == R.id.emo5){
                var newEmo = EmoData(date, R.drawable.emo5 ,etMemo.text.toString())
                emoDb?.getEmoDao()?.insertTodo(newEmo)
                finish()
            } else {
                Toast.makeText(this@EmoAdd, "기분을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }

}