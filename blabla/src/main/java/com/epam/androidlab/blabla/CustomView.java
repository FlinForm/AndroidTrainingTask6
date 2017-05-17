package com.epam.androidlab.blabla;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private final int[] smileColors = {Color.YELLOW, Color.BLACK, Color.RED, Color.BLUE};
    private final Paint painter;
    private final Path path;

    public CustomView(Context context, AttributeSet attributeSetrs) {
        super(context, attributeSetrs);
        painter = new Paint();
        path = new Path();
    }

    //Drawing sad smile
    @Override
    protected void onDraw(Canvas canvas) {
        //Drawing smile circle
        painter.setColor(smileColors[0]);
        canvas.drawCircle(360, 470, 200, painter);

        //Drawing eyes
        painter.setColor(smileColors[1]);
        painter.setStrokeWidth(10);
        canvas.drawLine(250, 380, 290, 420, painter);
        canvas.drawLine(250, 420, 290, 380, painter);
        canvas.drawLine(470, 380, 430, 420, painter);
        canvas.drawLine(470, 420, 430, 380, painter);

        //Drawing nose
        painter.setColor(smileColors[2]);
        canvas.drawCircle(360, 480, 30, painter);

        //Drawing smile mouth
        painter.setColor(smileColors[3]);
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 530, 450, 600);
        painter.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, painter);
    }
}
