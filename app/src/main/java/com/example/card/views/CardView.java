package com.example.card.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.card.MemoryGameActivity;
import com.example.card.R;
import com.example.card.models.Card;


public class CardView extends View {

    String cardValue, cardColor;
    boolean isTurn;

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
        invalidate();
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CardView, 0, 0);
        cardValue = a.getString(R.styleable.CardView_valueText);
        cardColor = a.getString(R.styleable.CardView_colorText);
        isTurn = a.getBoolean(R.styleable.CardView_isTurn, false);
    }

    public CardView(MemoryGameActivity context, Card card) {
        super(context);
        cardColor = card.getColor();
        cardValue = card.getValue();
        isTurn = card.isTurn();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint= new Paint();
        /*paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getScreenWidth()/5, 300, paint);*/
        paint.setColor(Color.WHITE);
        canvas.drawRect(10, 10, (getScreenWidth()/5-10), 290, paint);
        paint.setTextSize(34);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create("@font/alfa_slab_one", Typeface.BOLD));
        if(isTurn() == false) {
            paint.setColor(getColorsPaint());
            canvas.drawText(getSymbol(cardColor), 100, 150, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(cardValue, 40, 50, paint);
            canvas.scale(-1f, -1f, 40, 50);
            canvas.drawText(cardValue, -80, -150, paint);
        } else {
            paint.setColor(Color.BLACK);
            canvas.drawText("MEMORY", 100, 150, paint);
        }
    }

    private String getSymbol(String value) {
        switch(value) {
            case "COEUR" :
                return new String(Character.toChars(0x2665));
            case "CARREAU" :
                return new String(Character.toChars(0x2666));
            case "PIQUE" :
                return new String(Character.toChars(0x2660));
            case "TREFLE" :
                return new String(Character.toChars(0x2663));
        }
        return "";
    }

    private int getColorsPaint() {
        if(cardColor=="COEUR" || cardColor=="CARREAU") {
            return Color.RED;
        }
        else {
            return Color.BLACK;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getScreenWidth()/5, 300);
    }


}
