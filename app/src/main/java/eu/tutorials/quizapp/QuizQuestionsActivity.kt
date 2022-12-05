package eu.tutorials.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {


    //Create global variables for the views in the layout
    private var progressBar: ProgressBar?=null
    private var tvProgress: TextView? = null
    private var tvQuestion:TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne:TextView? = null
    private var tvOptionTwo:TextView? = null
    private var tvOptionThree:TextView? = null
    private var tvOptionFour:TextView? = null
    private var btnSubmit: Button? = null

    private  var UserName : String? =null
    /**
     * This function is auto created by Android when the Activity Class is created.
     */

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null
    private var mcurrentQuestion : Question?=null
    private var mtotal_CorrectAnswer: Int =0





    private var mSelectedOptionPosition: Int = 0
    // END


    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        UserName=intent.getStringExtra(Constants.USER_NAME)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_quiz_questions)
val Tv_username : TextView=findViewById(R.id.tv_username)

        Tv_username.text="Welcome $UserName"

        progressBar=findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit=findViewById(R.id.btn_SUBMIT)


        mQuestionsList = Constants.getQuestions()
        // END

        Constants.TOTAL_QUESTIONS
        setQuestion()


        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
    }




    private fun setQuestion() {


        btnSubmit?.text="Submit"
        defaultOptionsView()
        val question: Question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.
             mcurrentQuestion=question
        progressBar?.progress =
            mCurrentPosition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$mCurrentPosition" + "/" + progressBar?.max // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    override fun onClick(view: View?) {
      //  Log.e("question", "enter the fun ${view?.id}")
        when (view?.id) {

            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }

            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            }
            R.id.btn_SUBMIT-> {

                if(mSelectedOptionPosition==0)
                {
                    mCurrentPosition++


                    when{
                        mCurrentPosition<= mQuestionsList!!.size ->
                        {
                            setQuestion()
                        }
                        else ->
                        {
                            //when questions list in sompleted
                            Toast.makeText(this,"YOU Make it to end",Toast.LENGTH_LONG).show()
                   val intent =Intent(this,Resultactivity::class.java)

                           intent.putExtra(Constants.USER_NAME,"$UserName")
                            intent.putExtra(Constants.CORRECT_ANSWERS, mtotal_CorrectAnswer.toString())
                           intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size.toString())
                            startActivity(intent)

                        }


                    }
                }else {
                        val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOptionPosition)
                    {
                        Log.e("question", "enter thsssse fun")
                        check_answers(mSelectedOptionPosition,R.drawable.false_option_border_bg)
                        rightorwrong("false")
                    }else{
                        Log.i("question", "essssss")
                        check_answers(question.correctAnswer,R.drawable.correct_option_border_bg)
                        rightorwrong("true")
                    }

                }


             //   check_answers()
             //   defaultOptionsView()
            }


        }
    }

    private fun  rightorwrong(result : String )
    {

        when (result)
        {
            "false"->
            {


             btnSubmit?.text="Next question"
              //  mSelectedOptionPosition=0
                check_answers(mcurrentQuestion!!.correctAnswer,R.drawable.correct_option_border_bg)

            }

            "true"->
            {
                btnSubmit!!.text="Next question"
              //

                mtotal_CorrectAnswer++


            }

        }
        mSelectedOptionPosition=0
    }






    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        Log.i("question", "enter the fun")
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)  // make text bold
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )  //make background
    }

 fun  check_answers(answer : Int , drawableview : Int)
{
    when(answer) {
        1 ->{
            tvOptionOne?.background=ContextCompat.getDrawable(this,drawableview)

        }
        2 ->{
            tvOptionTwo?.background=ContextCompat.getDrawable(this,drawableview)

        }  3 ->{
        tvOptionThree?.setBackgroundResource(drawableview)

    }
        4 ->{
            tvOptionFour?.setBackgroundResource(drawableview)

        }



    }






}




    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )

        }
    }
}
// END