package com.example.card;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ListView scoreListView;

        scoreListView = (ListView) findViewById(R.id.scoreList);

        ArrayList<String> scores = new ArrayList<>();
        //getting preferences
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        for(int i=1; i<14; i++) {
            if(prefs.getString(String.valueOf(i), null) != null) {
                scores.add("Difficulty : " + i + "\nHigh score :  " + prefs.getString(String.valueOf(i), null));
            } else {
                scores.add("Difficulty : " + i + "\nHigh score :  ");
            }

        }
        for(int i=1; i<14; i++) {
            if(prefs.getString(String.valueOf(i) + " - HARD", null) != null) {
                scores.add("Difficulty : " + i + " - HARD" + "\nHigh score :  " + prefs.getString(String.valueOf(i) + " - HARD", null));
            } else {
                scores.add("Difficulty : " + i + " - HARD" + "\nHigh score :  ");
            }

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.advanced_list_item, R.id.textView, scores);
        scoreListView.setAdapter(arrayAdapter);
    }
}
