package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {

    private Paint paint = new Paint();
    private ArrayList<Line> lines = new ArrayList<>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);  // Default color is black
        paint.setStrokeWidth(8f);     // Set stroke width
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Line line : lines) {
            canvas.drawLine(line.startX, line.startY, line.endX, line.endY, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lines.add(new Line(event.getX(), event.getY(), event.getX(), event.getY()));
                break;
            case MotionEvent.ACTION_MOVE:
                Line currentLine = lines.get(lines.size() - 1);
                currentLine.endX = event.getX();
                currentLine.endY = event.getY();
                invalidate(); // Redraw the canvas
                break;
        }
        return true;
    }

    private static class Line {
        float startX, startY, endX, endY;

        Line(float startX, float startY, float endX, float endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}
