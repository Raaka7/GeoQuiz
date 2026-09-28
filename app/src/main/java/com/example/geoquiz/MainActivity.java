package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;

    private int currentIndex = 0;
    private int score = 0;

    private boolean answered = false;

    private Question[] questionBank = new Question[] {
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, false),
            new Question(R.string.question_6, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView = findViewById(R.id.score_text_view);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        cheatButton = findViewById(R.id.cheat_button);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex");
            score = savedInstanceState.getInt("score");
            answered = savedInstanceState.getBoolean("answered");
        }

        updateQuestion();
        updateScore();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNextQuestion();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,
                        CheatActivity.class
                );

                intent.putExtra(
                        CheatActivity.EXTRA_ANSWER_IS_TRUE,
                        questionBank[currentIndex].isAnswerTrue()
                );

                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {

        if (currentIndex < questionBank.length) {
            int question =
                    questionBank[currentIndex].getQuestionTextResId();

            questionTextView.setText(question);
        }
    }

    private void checkAnswer(boolean userAnswer) {

        if (answered) {
            Toast.makeText(
                    this,
                    "You already answered this question.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        boolean correctAnswer =
                questionBank[currentIndex].isAnswerTrue();

        if (userAnswer == correctAnswer) {

            score++;

            Toast.makeText(
                    this,
                    "Correct!",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            Toast.makeText(
                    this,
                    "Incorrect!",
                    Toast.LENGTH_SHORT
            ).show();
        }

        answered = true;
        updateScore();
    }

    private void moveToNextQuestion() {

        if (!answered) {
            Toast.makeText(
                    this,
                    "Please answer the question first.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (currentIndex < questionBank.length - 1) {

            currentIndex++;
            answered = false;

            updateQuestion();

        } else {

            questionTextView.setText(
                    "Quiz Complete! Score: " + score
                            + " / " + questionBank.length
            );

            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            nextButton.setEnabled(false);
            cheatButton.setEnabled(false);
        }

        updateScore();
    }

    private void updateScore() {

        scoreTextView.setText(
                "Score: " + score
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt("currentIndex", currentIndex);
        outState.putInt("score", score);
        outState.putBoolean("answered", answered);
    }
}