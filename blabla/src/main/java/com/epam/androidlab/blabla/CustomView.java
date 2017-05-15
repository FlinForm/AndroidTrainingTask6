package com.epam.androidlab.blabla;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View implements View.OnClickListener {
    private int[] smileColors = {Color.YELLOW, Color.BLACK, Color.RED, Color.BLUE};
    private CustomView instance;
    private Paint painter;
    private Path path;
    private boolean isSmileSad;

    public CustomView(Context context, AttributeSet attributeSetrs) {
        super(context, attributeSetrs);
        instance = this;
        instance.setOnClickListener(this);
        painter = new Paint();
        path = new Path();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributeSetrs,
                R.styleable.CustomView,
                0, 0);
        try {
            isSmileSad = typedArray.getBoolean(R.styleable.CustomView_isSad, false);
            smileColors[3] = typedArray.getInteger(R.styleable.CustomView_mouthColor, 0);
            smileColors[1] = typedArray.getInteger(R.styleable.CustomView_eyesColor, 0);
        } finally {
            typedArray.recycle();
        }
    }

    //Drawing sad smile
    @Override
    protected void onDraw(Canvas canvas) {
        checkAttributes();

        canvas.drawARGB(80, 102, 204, 255);

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
        if (isSmileSad) {
            drawSadMouth(canvas);
        } else {
            drawCheerfulMouth(canvas);
        }
    }

    // Checks, if attributes in *.xml file are given. If not, restores default values.
    private void checkAttributes() {
        if (smileColors[1] == 0) {
            smileColors[1] = Color.BLACK;
        }
        if (smileColors[3] == 0) {
            smileColors[3] = Color.BLUE;
        }
    }

    //Drawing cheerful mouth
    private void drawCheerfulMouth(Canvas canvas) {
        painter.setColor(smileColors[3]);
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 670, 450, 600);
        painter.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, painter);
    }

    //Drawing sad mouth
    private void drawSadMouth(Canvas canvas) {
        painter.setColor(smileColors[3]);
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 530, 450, 600);
        painter.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, painter);
    }

    @Override
    public void onClick(View v) {
        if (isSmileSad) {
            isSmileSad = false;
        } else if (!isSmileSad) {
            isSmileSad = true;
        }
        invalidate();
    }
}
