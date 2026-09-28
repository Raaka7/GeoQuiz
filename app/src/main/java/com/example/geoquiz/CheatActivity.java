package com.example.geoquiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.geoquiz.answer_is_true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        TextView answerTextView =
                findViewById(R.id.cheat_answer_text_view);

        boolean answerIsTrue =
                getIntent().getBooleanExtra(
                        EXTRA_ANSWER_IS_TRUE,
                        false
                );

        if (answerIsTrue) {
            answerTextView.setText("TRUE");
        } else {
            answerTextView.setText("FALSE");
        }
    }
}