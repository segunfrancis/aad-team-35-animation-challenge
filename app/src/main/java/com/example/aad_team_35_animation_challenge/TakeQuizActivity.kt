package com.example.aad_team_35_animation_challenge
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.aad_team_35_animation_challenge.util.Question
import kotlinx.android.synthetic.main.activity_take_quiz.*

class TakeQuizActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)
        var question =  Question()
        question_tv.setText(question.getQuestion(0))
        radio_button_1.setText(question.getchoice1(0))
        radio_button_2.setText(question.getchoice2(0))
        radio_button_3.setText(question.getchoice3(0))
        radio_button_4.setText(question.getchoice4(0))
    }




    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_button_1 ->
                    if (checked) {
                        // Pirates are the best
                    }
                R.id.radio_button_2 ->
                    if (checked) {
                        // Ninjas rule
                    }
                R.id.radio_button_3 ->
                    if (checked) {
                        // Ninjas rule
                    }
                R.id.radio_button_4 ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }
}