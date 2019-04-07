package com.example.card;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    public static int difficulty = 0;
    public static String isHardcore = "";

    public static int getDifficulty() {
        return difficulty;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonScore = (Button) findViewById(R.id.buttonScore);
        Button buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        Button buttonHardcore = (Button) findViewById(R.id.buttonHardcore);

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Difficulty");

                // Set up the input
                final NumberPicker input = new NumberPicker(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setMinValue(1);
                input.setMaxValue(13);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        difficulty = (int) Integer.parseInt(String.valueOf(input.getValue()));

                        Intent myIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                        startActivity(myIntent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(myIntent);
            }
        });

        buttonHardcore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isHardcore = " - HARD";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Difficulty");
                builder.setMessage("WARNING !\nIN HARDCORE MODE CARDS WILL BE ALL TURN EVERY 2 MISTAKES");

                // Set up the input
                final NumberPicker input = new NumberPicker(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setMinValue(1);
                input.setMaxValue(13);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        difficulty = (int) Integer.parseInt(String.valueOf(input.getValue()));

                        Intent myIntent = new Intent(MainActivity.this, MemoryGameActivity.class);
                        startActivity(myIntent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}

