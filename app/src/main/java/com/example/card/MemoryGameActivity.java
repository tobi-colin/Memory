package com.example.card;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.card.models.Card;
import com.example.card.models.Deck;
import com.example.card.models.Memory;
import com.example.card.views.CardView;
import com.google.android.flexbox.FlexboxLayout;

public class MemoryGameActivity extends AppCompatActivity implements View.OnClickListener {

    static Deck deck;
    static FlexboxLayout flexboxlayout;
    Memory memory;
    Chronometer chronometer;
    TextView scoreView;

    public static Deck getDeck() {
        return deck;
    }

    public static void setDeck(Deck deck) {
        MemoryGameActivity.deck = deck;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memory = new Memory();
        deck = new Deck();
        deck.shuffle();

        flexboxlayout = (FlexboxLayout) findViewById(R.id.FlexboxLayoutCards);

        chronometer = (Chronometer) findViewById(R.id.chronometerView);
        chronometer.setText(00 + ":" + 00 + ":" + 00);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase() ;
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                //String hh = h < 10 ? "0" + h : h + "";
                String mm = m < 10 ? "0" + m : m + "";
                String ss = s < 10 ? "0" + s : s + "";
                cArg.setText(mm + ":" + ss);

            }
        });
        chronometer.start();

        while (deck.count() > 0) {
            Card c = deck.draw();
            CardView cardView = new CardView(this, c);
            cardView.setOnClickListener(MemoryGameActivity.this);
            flexboxlayout.addView(cardView);
        }

        scoreView = (TextView) findViewById(R.id.viewScore);

        //getting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        String highScoreSaved = prefs.getString(String.valueOf(MainActivity.difficulty) + MainActivity.isHardcore, null); //0 is the default value

        if(highScoreSaved != null) {
            scoreView.setText("Best score : " + highScoreSaved);
        } else {
            scoreView.setText("Best score : ");
        }
    }


    @Override
    public void onClick(View v) {

        CardView cardView = (CardView) v;
        memory.setSelectedCard(cardView);

        if(Memory.pairsFound == MainActivity.getDifficulty()*2) {
            chronometer.stop();

            //getting preferences
            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            String highScoreSaved = prefs.getString(String.valueOf(MainActivity.difficulty + MainActivity.isHardcore), null); //0 is the default value

            String actualScore = (String) chronometer.getText();

            if(highScoreSaved != null) {
                int highScore = Integer.parseInt(highScoreSaved.replaceAll(":", ""));
                int score = Integer.parseInt(actualScore.replaceAll(":", ""));

                if (highScore > score) {
                    //setting preferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(String.valueOf(MainActivity.difficulty) + MainActivity.isHardcore, actualScore);
                    editor.commit();
                    scoreView.setText("Best score : " + actualScore);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MemoryGameActivity.this);
                    builder.setTitle("Congratulation");
                    builder.setMessage("GGWP !\nYOU BEAT THE HIGH SCORE");
                    // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            } else {
                //setting preferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(String.valueOf(MainActivity.difficulty) + MainActivity.isHardcore, actualScore);
                editor.commit();
                scoreView.setText("Best score : " + actualScore);

                AlertDialog.Builder builder = new AlertDialog.Builder(MemoryGameActivity.this);
                builder.setTitle("Congratulation");
                builder.setMessage("GGWP !\nYOU DID THE HIGH SCORE");
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            Memory.pairsFound = 0;
        }
    }
}
