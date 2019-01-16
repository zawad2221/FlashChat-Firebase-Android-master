package com.dhakanewsclub.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Button mTrueButton,mFalseButton;
    TextView mTextView;
    int mIndex;
    int mScore;
    ProgressBar mProgressBar;
    TextView mScoreTextView;

    // TODO: Declare constants here


    // TODO: Declare member variables here:


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    final int mProgressBarIncriment=(int)Math.ceil(100.0/mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mTrueButton=findViewById(R.id.trueButton);
        mFalseButton=findViewById(R.id.falseButton);
        mTextView=findViewById(R.id.questionTextView);
        mTextView.setText(mQuestionBank[mIndex].getQuestionId());
        mProgressBar=findViewById(R.id.progressBar);
        mScoreTextView=findViewById(R.id.scoreTextView);

        if(savedInstanceState!=null)
        {
            mScore=savedInstanceState.getInt("ScoreKey");
            mIndex=savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);


        }
        else
        {
            mIndex=0;
            mScore=0;
        }





        View.OnClickListener myListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"True Prassed",Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                changeTextView();

            }
        };
        mTrueButton.setOnClickListener(myListner);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast myTost= Toast.makeText(getApplicationContext(),"False Prassed",Toast.LENGTH_SHORT);
//                myTost.show();
                checkAnswer(false);
                changeTextView();
            }
        });




    }
    private void changeTextView()
    {
        mProgressBar.incrementProgressBy(mProgressBarIncriment);

        mIndex=(mIndex+1)%mQuestionBank.length;
        if(mIndex==0)
        {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Done");
            dialog.setCancelable(false);
            dialog.setMessage("You Score "+mScore);

            dialog.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   finish();
                }
            });
            dialog.show();


        }
        mTextView.setText(mQuestionBank[mIndex].getQuestionId());

        mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);
    }
    private void checkAnswer(boolean answer)
    {
        boolean correctAnswer=mQuestionBank[mIndex].isAnswer();
        if(answer==correctAnswer)
        {
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("IndexKey",mIndex);
        outState.putInt("ScoreKey",mScore);

    }




}
