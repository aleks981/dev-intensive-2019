package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bander


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var banderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView
    lateinit var banderObj : Bander


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onCreate")

        banderImage = iv_bander
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bander.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bander.Question.NAME.name
        banderObj = Bander(Bander.Status.valueOf(status), Bander.Question.valueOf(question))

        textTxt.text = banderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }

    override fun onRestart(){
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", banderObj.status.name)
        outState?.putString("QUESTION", banderObj.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${banderObj.status.name} ${banderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if (v?.id==R.id.iv_send){
         val (phrase, color) = banderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) = color
            banderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
    }
}
