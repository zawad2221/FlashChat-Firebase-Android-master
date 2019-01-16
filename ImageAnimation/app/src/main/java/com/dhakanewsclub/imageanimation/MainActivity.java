package com.dhakanewsclub.imageanimation;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView dear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dear=(ImageView) findViewById(R.id.dearImageView);



    }
    void dearTap(View view)
    {


        dear.animate().scaleX(.2f).scaleY(.2f).setDuration(100);
        dear.animate().translationXBy(399).translationYBy(888).setDuration(2000);














    }

}
