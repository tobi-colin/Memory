package com.example.card.models;

import android.support.v7.app.AppCompatActivity;

import com.example.card.MainActivity;
import com.example.card.views.CardView;

import java.util.ArrayList;

public class Memory extends AppCompatActivity {

    CardView selectedCard1, selectedCard2;
    public static int pairsFound = 0;
    private int errorCount = 0;
    ArrayList<CardView> cardViews = new ArrayList<CardView>();

    public Memory() {
        selectedCard1 = null;
        selectedCard2 = null;
    }

    public void setSelectedCard(CardView c) {
        c.setTurn(false);

        if(!cardViews.contains(c)) {
            cardViews.add(c);
        }

        if(selectedCard1 != null && selectedCard2 != null && !isSame(selectedCard1, selectedCard2)) {
            if(MainActivity.isHardcore != "" && errorCount > 1) {
                for(int i = 0; i<cardViews.size(); i++) {
                    cardViews.get(i).setTurn(true);
                }
                errorCount = 0;
                pairsFound = 0;
            } else {
                selectedCard1.setTurn(true);
                selectedCard2.setTurn(true);

                selectedCard1 = null;
                selectedCard2 = null;

                errorCount += 1;
            }
        }

        if(selectedCard1 == null) {
            selectedCard1 = c;
        } else {
            if (!isSame(selectedCard1, c)) {
                selectedCard2 = c;
            } else {
                pairsFound += 1;

                c.setTurn(false);
                selectedCard1.setTurn(false);

                selectedCard1 = null;
            }
        }
    }

    public boolean isBlack(CardView c) {
        return (c.getCardColor().equals("TREFLE") || c.getCardColor().equals("PIQUE"));
    }

    public boolean isSame(CardView c1, CardView c2) {
        return (isBlack(c1) && isBlack(c2) && c1.getCardValue().equals(c2.getCardValue())) ||
                (!isBlack(c1) && !isBlack(c2) && c1.getCardValue().equals(c2.getCardValue()));

    }
}
