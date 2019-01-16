package com.dhakanewsclub.destini;

import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int storyIndex = 0;

    private Story[] mStoryBank = new Story[]{
            new Story(R.string.T1_Story,R.string.T1_Ans1,R.string.T1_Ans2),
            new Story(R.string.T2_Story,R.string.T2_Ans1,R.string.T2_Ans2),
            new Story(R.string.T3_Story,R.string.T3_Ans1,R.string.T3_Ans2),
            new Story(R.string.T4_End),
            new Story(R.string.T5_End),
            new Story(R.string.T6_End)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final TextView textView = findViewById(R.id.textView);
        if(savedInstanceState!=null)
        {
            storyIndex=savedInstanceState.getInt("storyIndex");
            if(storyIndex!=3 && storyIndex!=4 && storyIndex!=5)
            {
                button1.setText(savedInstanceState.getInt("stroyAns_1"));
                button2.setText(savedInstanceState.getInt("stroyAns_2"));
            }
            else
            {
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }

            textView.setText(savedInstanceState.getInt("stroy"));

        }




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storyIndex == 1 || storyIndex == 2) {
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.GONE);

                }


                int index = storyIndex;
                if (storyIndex == 0) {
                    index = 2;
                    storyIndex = 2;
                    button1.setText(mStoryBank[index].getAnswerID_1());
                    button2.setText(mStoryBank[index].getAnswerID_2());
                } else if (storyIndex == 2) {
                    index = 4;
                    storyIndex = 4;

                }
                else if(storyIndex==1)
                {
                    index = 3;
                    storyIndex = 3;
                }
                textView.setText(mStoryBank[index].getQuestionId());



            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (storyIndex == 1 || storyIndex == 2) {
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.GONE);

                }

                int index = storyIndex;

                if (storyIndex == 0) {
                    index = 1;
                    storyIndex = 1;
                    button1.setText(mStoryBank[index].getAnswerID_1());
                    button2.setText(mStoryBank[index].getAnswerID_2());

                } else if (storyIndex == 2) {
                    index = 5;
                    storyIndex = 5;


                } else if (storyIndex == 1) {
                    index = 4;
                    storyIndex = 4;


                }

                textView.setText(mStoryBank[index].getQuestionId());

            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("storyIndex",storyIndex);
        outState.putInt("stroy",mStoryBank[storyIndex].getQuestionId());
        outState.putInt("stroyAns_1",mStoryBank[storyIndex].getAnswerID_1());
        outState.putInt("stroyAns_2",mStoryBank[storyIndex].getAnswerID_2());

    }
}
