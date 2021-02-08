package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;
    private static final int REQUEST_CODE_CHEAT = 0;


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;

    private TextView mQuestionTextView;

    public static final String EXTRA_MESSAGE = "com.example.geoquiz.MESSAGE";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.d("In onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private static final String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        //Logger.d("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Logger.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Logger.d("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Logger.d("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Logger.d("onResume");
    }


    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(requestCode);
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (resultCode == Activity.RESULT_OK) {
                Logger.d("Result okay and code is 0");
            } else {
                Logger.d("Result Cancelled ");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());


        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mCheatButton = findViewById(R.id.cheat_button);


        mQuestionTextView = findViewById(R.id.question_text_view);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivty.class);
                String message = "Hello from Main Activity";
                i.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        // this is the click event for the true button and it do this and this and this
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        // this is the click event for the false button and it do this and this and this
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);


            }
        });
    }

    private void checkAnswer(boolean userAnswer) {
        boolean answer = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        String message = "";
        if (userAnswer == answer) {
            message = "You Win!";
        } else {
            message = "you lost!";
        }

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        Logger.d(answer);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }


}


