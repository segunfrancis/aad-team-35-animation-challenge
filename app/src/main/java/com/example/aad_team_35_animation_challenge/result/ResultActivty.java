package com.example.aad_team_35_animation_challenge.result;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.aad_team_35_animation_challenge.R;

public class ResultActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activty);

        final Button button = (Button) findViewById(R.id.button_retake);



        final Button buttonquittest = findViewById(R.id.button_quit_test);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(ResultActivty.this, R.anim.fadein);
               button.startAnimation(animation);

            }
        });


        buttonquittest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(ResultActivty.this,R.anim.fadein);

                buttonquittest.startAnimation(animation);
                ResultActivty.this.finishAffinity();
            }
        });

    }
}

