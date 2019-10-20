package com.example.aad_team_35_animation_challenge;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;

public class ResultActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final Button button = (Button) findViewById(R.id.button_retake);



        final Button buttonquittest = findViewById(R.id.button_quit_test);


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(ResultActivity.this, R.anim.fadein);
                button.startAnimation(animation);
            }
        });


        buttonquittest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(ResultActivity.this,R.anim.fadein);

                buttonquittest.startAnimation(animation);
            }
        });

    }
}
