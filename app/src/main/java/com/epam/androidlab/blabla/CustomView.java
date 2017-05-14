package com.epam.androidlab.blabla;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private Paint painter;
    private Path path;

    public CustomView(Context context, AttributeSet attributeSetrs) {
        super(context, attributeSetrs);
    }

    //Drawing sad smile
    @Override
    protected void onDraw(Canvas canvas) {
        painter = new Paint();
        path = new Path();
        canvas.drawARGB(80, 102, 204, 255);

        //Drawing circle
        painter.setColor(Color.YELLOW);
        canvas.drawCircle(360, 470, 200, painter);

        //Drawing eyes
        painter.setColor(Color.BLACK);
        painter.setStrokeWidth(10);
        canvas.drawLine(250, 380, 290, 420, painter);
        canvas.drawLine(250, 420, 290, 380, painter);
        canvas.drawLine(470, 380, 430, 420, painter);
        canvas.drawLine(470, 420, 430, 380, painter);

        //Drawing nose
        painter.setColor(Color.RED);
        canvas.drawCircle(360, 480, 30, painter);
        painter.setColor(Color.YELLOW);
        canvas.drawCircle(360, 480, 20, painter);

        //Drawing mouth
        painter.setColor(Color.BLUE);
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 530, 450, 600);
        painter.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, painter);
    }
}
