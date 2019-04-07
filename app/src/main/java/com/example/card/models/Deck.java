package com.example.card.models;

import com.example.card.MainActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private int difficulty;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Deck() {
        cards = new ArrayList<Card>();
        String[] colors = {"PIQUE", "TREFLE", "COEUR", "CARREAU"};
        difficulty = MainActivity.getDifficulty();
        for(String c : colors) {
            for(int i=difficulty; i>0; i--) {
                switch(i) {
                    case 1 :
                        cards.add(new Card(c, "A"));
                        break;
                    case 11 :
                        cards.add(new Card(c, "V"));
                        break;
                    case 12 :
                        cards.add(new Card(c, "D"));
                        break;
                    case 13 :
                        cards.add(new Card(c, "R"));
                        break;
                    case 2 :
                        default :
                            cards.add(new Card(c, i+""));
                            break;
                }
            }
        }
    }

    public void shuffle() { Collections.shuffle(cards); }

    public Card draw() {
        Card c = cards.get(0);
        cards.remove(c);
        return c;
    }

    public int count() {
        return cards.size();
    }
}
