package com.zakharov.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

/**
 *  @author Alex Zakharov, 2018/03/06
 */

public class Main extends AppCompatActivity {

    final String TAG = "lifecycle:";

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    TextView answerTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView secondsTextView;
    TextView resultTextView;
    TextView percentageTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    GridLayout buttonsGridLayout;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions;
    int percentageOfCorrectAnswers;
    int resultScore;

    // ------- answer buttons` logic ------- //
    public void chooseAnswer(View view) {
        answerTextView.setVisibility(View.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            answerTextView.setText(getString(R.string.correct));
            answerTextView.setTextColor(getResources().getColor(R.color.correctColor));
        } else {
            answerTextView.setText(getString(R.string.wrong));
            answerTextView.setTextColor(getResources().getColor(R.color.wrongColor));
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(49)+10;
        int b = rand.nextInt(49)+10;
        int incorrectAnswer;

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = (a + b) + rand.nextInt(35);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = (a + b) + rand.nextInt(35);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        pointsTextView.setText("0/0");
        resultTextView.setText("");
        percentageTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        buttonsGridLayout.setVisibility(GridLayout.VISIBLE);


        generateQuestion();
        new CountDownTimer(30100, 1000) {



            @Override
            public void onTick(long millisUntilFinished) {

                secondsTextView.setText(DateUtils.formatElapsedTime(millisUntilFinished / 1000));
                secondsTextView.setTextColor(getResources().getColor(R.color.white));

                if (millisUntilFinished < 10100) {
                    secondsTextView.setTextColor(getResources().getColor(R.color.wrongColor));
                }
            }

            @Override
            public void onFinish() {

                answerTextView.setText("");

                try {
                    percentageOfCorrectAnswers = score * 100 / numberOfQuestions;
                } catch (ArithmeticException ae) {
                    percentageOfCorrectAnswers = 0;
                    resultScore = 0;
                }

                // ------- The score, player achieves ------- //
                resultScore = score * percentageOfCorrectAnswers;

                buttonsGridLayout.setVisibility(GridLayout.INVISIBLE);
                secondsTextView.setText("00:00");
                sumTextView.setVisibility(View.INVISIBLE);
                percentageTextView.setText(getString(R.string.percent1) + "\n"
                        + (percentageOfCorrectAnswers) + getString(R.string.percent2));

                resultTextView.setText(getString(R.string.result) + "\n" + resultScore + "!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.varButton);
        button1 = (Button) findViewById(R.id.varButton1);
        button2 = (Button) findViewById(R.id.varButton2);
        button3 = (Button) findViewById(R.id.varButton3);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        percentageTextView = (TextView) findViewById(R.id.percentageTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        secondsTextView = (TextView) findViewById(R.id.secondsTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        buttonsGridLayout = (GridLayout) findViewById(R.id.buttonsGridLayout);

        playAgain(findViewById(R.id.playAgainButton));

        Log.i(TAG, " onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, " onDestroy");
    }

}