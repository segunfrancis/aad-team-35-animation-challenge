package com.example.aad_team_35_animation_challenge
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.aad_team_35_animation_challenge.util.Question
import kotlinx.android.synthetic.main.activity_take_quiz.*

class TakeQuizActivity : AppCompatActivity() {

    var isChecked:Boolean = false
    var position: Int = 0
    var answer: String = ""
    var answerId: Int = 0
    var score:Int = 0
    var answerMap:HashMap<Int,Int> = hashMapOf()
    lateinit var question: Question

    val OPTION_ONE_ANSWERID:Int = 11
    val OPTION_TWO_ANSWERID:Int = 12
    val OPTION_THREE_ANSWERID:Int = 13
    val OPTION_FOUR_ANSWERID:Int = 14

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)
        question =  Question()
        setUpView(position)


        submitQuestionFAB.setOnClickListener {
            if (isChecked) {
                answerMap[position] = answerId
                checkAnswer(answer)
                clickable(false)
            }
        }
        nextQuestionFAB.setOnClickListener {

            if(position<9){
                position +=1
                setUpView(position)
                AnswerRadioGroup.clearCheck()
                clickable(true)
                isChecked = false
                removeBackgroundColor()
            }
            if(position == 9) {
                nextQuestionFAB.hide()

                invalidateOptionsMenu()

            }
        }
        previousQuestionFAB.setOnClickListener {
            if(position>0) {
                position -= 1
                nextQuestionFAB.show()
                setUpView(position)
                AnswerRadioGroup.clearCheck()
                when(answerMap[position]){
                    OPTION_ONE_ANSWERID ->
                    {AnswerRadioGroup.check(R.id.radio_button_1)
                        clickable(false)
                    }
                    OPTION_TWO_ANSWERID ->
                    {
                        AnswerRadioGroup.check(R.id.radio_button_2)
                        clickable(false)
                    }
                    OPTION_THREE_ANSWERID ->
                    {
                        AnswerRadioGroup.check(R.id.radio_button_3)
                        clickable(false)
                    }
                    OPTION_FOUR_ANSWERID ->{
                        AnswerRadioGroup.check(R.id.radio_button_4)
                        clickable(false)
                    }

                }
            }
        }

    }

    private fun clickable(boolValue: Boolean) {
        submitQuestionFAB.isClickable = boolValue
        radio_button_1.isClickable = boolValue
        radio_button_2.isClickable = boolValue
        radio_button_3.isClickable = boolValue
        radio_button_4.isClickable = boolValue
    }

    private fun setUpView(position: Int) {
        val questionPosition = position + 1
        question_number.text = "Question #$questionPosition"
            question_tv.text = question.getQuestion(position)
            radio_button_1.text = question.getchoice1(position)
            radio_button_2.text = question.getchoice2(position)
            radio_button_3.text = question.getchoice3(position)
            radio_button_4.text = question.getchoice4(position)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.take_quiz_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_menu_submit ->
                submitIntent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun submitIntent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_button_1 ->
                    if (checked) {
                        isChecked = true
                        answer = radio_button_1.text.toString()
                        answerId = OPTION_ONE_ANSWERID
                    }
                R.id.radio_button_2 ->
                    if (checked) {
                        isChecked = true
                        answer = radio_button_2.text.toString()
                        answerId = OPTION_TWO_ANSWERID
                        }
                R.id.radio_button_3 ->
                    if (checked) {
                        isChecked = true
                        answer = radio_button_3.text.toString()
                        answerId = OPTION_THREE_ANSWERID
                    }
                R.id.radio_button_4 ->
                    if (checked) {
                        isChecked = true
                        answer = radio_button_4.text.toString()
                        answerId = OPTION_FOUR_ANSWERID
                    }
            }
        }
    }
    fun checkAnswer(answer:String){
        if (answer.equals(question.getCorrectAnswer(position))){
            score +=1
            Toast.makeText(this,"correct answer",Toast.LENGTH_SHORT).show()
            val correct = R.color.correctColor
            setColor(answerId,correct)
        }else{
            Toast.makeText(this,"wrong answer",Toast.LENGTH_SHORT).show()
            val fail = R.color.failColor
            setColor(answerId,fail)
        }
    }

    private fun setColor(answerId: Int, result: Int) {
        when (answerId) {
            OPTION_ONE_ANSWERID -> radio_button_1.setBackgroundColor(resources.getColor(result))
            OPTION_TWO_ANSWERID -> radio_button_2.setBackgroundColor(resources.getColor(result))
            OPTION_THREE_ANSWERID -> radio_button_3.setBackgroundColor(resources.getColor(result))
            OPTION_FOUR_ANSWERID -> radio_button_4.setBackgroundColor(resources.getColor(result))
        }

    }
    private fun removeBackgroundColor(){
        radio_button_1.setBackgroundColor(resources.getColor(R.color.defaultBackgroundColor))
        radio_button_2.setBackgroundColor(resources.getColor(R.color.defaultBackgroundColor))
        radio_button_3.setBackgroundColor(resources.getColor(R.color.defaultBackgroundColor))
        radio_button_4.setBackgroundColor(resources.getColor(R.color.defaultBackgroundColor))
    }
}