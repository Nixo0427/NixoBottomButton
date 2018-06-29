package com.example.nixo.nixoview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleNumberProgessBar mNixoProgessBar = findViewById(R.id.mNixo);
        NixoBottomButton bottomButton = findViewById(R.id.BottomButton);
        bottomButton.setIcon(R.mipmap.plus);
        bottomButton.setOnClickListener(v ->
        {
            ObjectAnimator.ofInt(mNixoProgessBar, "progress", 0, 100).setDuration(3000).start();
        });


    }
}