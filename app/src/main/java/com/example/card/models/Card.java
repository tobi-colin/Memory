package com.example.card.models;

public class Card {

    private String color, value;
    private boolean isTurn;

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
        this.isTurn = true;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

}
