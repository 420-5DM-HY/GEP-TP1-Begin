package com.example.casualspy.tictactoeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView grille;
    ImageView A1;
    ImageView A2;
    ImageView A3;
    ImageView B1;
    ImageView B2;
    ImageView B3;
    ImageView C1;
    ImageView C2;
    ImageView C3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grille = findViewById(R.id.imageView);
        A1 = findViewById(R.id.imageA1);
        A2 = findViewById(R.id.imageA2);
        A3 = findViewById(R.id.imageA3);
        B1 = findViewById(R.id.imageB1);
        B2 = findViewById(R.id.imageB2);
        B3 = findViewById(R.id.imageB3);
        C1 = findViewById(R.id.imageC1);
        C2 = findViewById(R.id.imageC2);
        C3 = findViewById(R.id.imageC3);
    }
}
