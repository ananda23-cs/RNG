package com.example.rng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText et_min, et_max;
    Button randomizeNumber;
    TextView message;

    Random r;

    int min, max, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        //Editable texts for setting the range of integers
        et_min = (EditText) findViewById(R.id.et_min);
        et_max = (EditText) findViewById(R.id.et_max);

        //Button created to generate the random number within the range specified by user
        randomizeNumber = (Button) findViewById(R.id.randomizeNumber);

        //Output displays random number generated
        message = (TextView) findViewById(R.id.message);

        //Waits for a button press from the user
        randomizeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempMin = et_min.getText().toString();
                String tempMax = et_max.getText().toString();
                if(!tempMin.equals("") && !tempMax.equals("")){
                    min = Integer.parseInt(tempMin);
                    max = Integer.parseInt(tempMax);
                    if(max > min) {
                        output = r.nextInt((max - min) + 1) + min;
                        message.setText("The number generated is: " + output);
                        message.setGravity(Gravity.CENTER);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Range invalid. Try again",
                                                            Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("generation", message.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        message.setText(savedInstanceState.getString("generation"));
    }
}