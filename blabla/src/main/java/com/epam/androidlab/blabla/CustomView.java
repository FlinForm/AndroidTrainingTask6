package com.epam.androidlab.blabla;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View implements View.OnClickListener {
    private final Paint smilePainter;
    private final Paint mouthPainter;
    private final Paint eyesPainter;
    private final Paint nosePainter;
    private final Path path;
    private boolean isSmileSad;

    public CustomView(Context context, AttributeSet attributeSetrs) {
        super(context, attributeSetrs);
        setOnClickListener(this);
        smilePainter = new Paint();
        mouthPainter = new Paint();
        eyesPainter = new Paint();
        nosePainter = new Paint();
        path = new Path();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributeSetrs,
                R.styleable.CustomView,
                0, 0);
        try {
            isSmileSad = typedArray.getBoolean(R.styleable.CustomView_isSad, false);
            mouthPainter.setColor(typedArray.getColor(R.styleable.CustomView_mouthColor, Color.BLUE));
            eyesPainter.setColor(typedArray.getInteger(R.styleable.CustomView_eyesColor, Color.BLACK));
        } finally {
            typedArray.recycle();
        }
    }

    //Drawing sad smile
    @Override
    protected void onDraw(Canvas canvas) {

        //Drawing smile circle
        smilePainter.setColor(Color.YELLOW);
        smilePainter.setStrokeWidth(10);
        canvas.drawCircle(360, 470, 200, smilePainter);

        //Drawing eyes
        eyesPainter.setStrokeWidth(10);
        canvas.drawLine(250, 380, 290, 420, eyesPainter);
        canvas.drawLine(250, 420, 290, 380, eyesPainter);
        canvas.drawLine(470, 380, 430, 420, eyesPainter);
        canvas.drawLine(470, 420, 430, 380, eyesPainter);

        //Drawing nose
        nosePainter.setColor(Color.RED);
        canvas.drawCircle(360, 480, 30, nosePainter);

        //Drawing smile mouth
        if (isSmileSad) {
            drawSadMouth(canvas);
        } else {
            drawCheerfulMouth(canvas);
        }
    }

    //Drawing cheerful mouth
    private void drawCheerfulMouth(Canvas canvas) {
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 670, 450, 600);
        mouthPainter.setStyle(Paint.Style.STROKE);
        mouthPainter.setStrokeWidth(10);
        canvas.drawPath(path, mouthPainter);
    }

    //Drawing sad mouth
    private void drawSadMouth(Canvas canvas) {
        path.reset();
        path.moveTo(270, 600);
        path.quadTo(360, 530, 450, 600);
        mouthPainter.setStyle(Paint.Style.STROKE);
        mouthPainter.setStrokeWidth(10);
        canvas.drawPath(path, mouthPainter);
    }

    @Override
    public void onClick(View v) {
        isSmileSad = !isSmileSad;
        invalidate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putBoolean("isSmileSad", isSmileSad);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            isSmileSad = bundle.getBoolean("isSmileSad");
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }
}