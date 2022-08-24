package com.example.daiily_emo

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Window
import android.widget.*

class EmoEdit : Activity() {

    private var emoDb : DailyEmoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.emo_listedit)

        var tvDate = findViewById<TextView>(R.id.tvDate)
        var editMemo = findViewById<EditText>(R.id.editMemo)
        var radioGroup = findViewById<RadioGroup>(R.id.rgEmo)
        var btncan = findViewById<Button>(R.id.btncan)
        var btnok = findViewById<Button>(R.id.btnok)
        var trash = findViewById<Button>(R.id.trash)

        emoDb = DailyEmoDB.getInstance(this)

        var intent = intent

        tvDate.text = intent.getStringExtra("DATE")
        editMemo.setText(intent.getStringExtra("MEMO"))

        btncan.setOnClickListener {
            finish()
        }
        trash.setOnClickListener {
            emoDb?.getEmoDao()?.delete(intent.getIntExtra("ID", 0))
            finish()
        }

        btnok.setOnClickListener {
            var radioBtnId = radioGroup.checkedRadioButtonId

            if(radioBtnId == R.id.rbEmo1){
                emoDb?.getEmoDao()?.update(intent.getIntExtra("ID", 0), tvDate.toString(),R.drawable.emo1 ,editMemo.text.toString())
                finish()
            } else if (radioBtnId == R.id.rbEmo2){
                emoDb?.getEmoDao()?.update(intent.getIntExtra("ID", 0), tvDate.toString(),R.drawable.emo2 ,editMemo.text.toString())
                finish()
            } else if (radioBtnId == R.id.rbEmo3){
                emoDb?.getEmoDao()?.update(intent.getIntExtra("ID", 0), tvDate.toString(),R.drawable.emo3 ,editMemo.text.toString())
                finish()
            } else if (radioBtnId == R.id.rbEmo4){
                emoDb?.getEmoDao()?.update(intent.getIntExtra("ID", 0), tvDate.toString(),R.drawable.emo4 ,editMemo.text.toString())
                finish()
            } else if (radioBtnId == R.id.rbEmo5){
                emoDb?.getEmoDao()?.update(intent.getIntExtra("ID", 0), tvDate.toString(),R.drawable.emo5 ,editMemo.text.toString())
                finish()
            } else {
                Toast.makeText(this@EmoEdit, "기분을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroy() {
        DailyEmoDB.destroyInstance()
        super.onDestroy()
    }
}