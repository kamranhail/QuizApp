package eu.tutorials.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Resultactivity : AppCompatActivity() {


    private var tvusername: TextView? = null
    private var tvscore: TextView? = null
    private var btnFinish: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultactivity)

tvscore=findViewById(R.id.tv_score)
        tvusername=findViewById(R.id.tv_name)

      //  val data=

        btnFinish=findViewById(R.id.btn_finish)

   tvscore?.text="you score : ${intent.getStringExtra(Constants.CORRECT_ANSWERS.toString())} out of" +
            " ${intent.getStringExtra(Constants.TOTAL_QUESTIONS.toString())} "
        tvusername?.text=intent.getStringExtra(Constants.USER_NAME.toString())


        btnFinish?.setOnClickListener{

            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}