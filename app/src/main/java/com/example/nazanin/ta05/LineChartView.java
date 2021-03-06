package com.example.nazanin.ta05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.text.MessageFormat;

/**
 * Created by jon on 3/20/2016.
 */
public class LineChartView extends View {

    private Paint _paint = new Paint();
    private Paint _paintBorder = new Paint();
    private Paint _paintBackground = new Paint();

    private int[] _colors = {Color.CYAN, Color.YELLOW, Color.MAGENTA,
                            Color.GREEN, Color.RED, Color.BLUE, Color.GRAY};

    private double[] _values;
    private String[] _labels;

    public LineChartView(Context context) {
        super(context);
        init(null, 0);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * Because we have more than one constructor (i.e., overloaded constructors), we use
     * a separate initialization method
     * @param attrs
     * @param defStyle
     */
    private void init(AttributeSet attrs, int defStyle){

        // Set setDrawingCacheEnabled to true to support generating a bitmap copy of the view (for saving)
        // See: http://developer.android.com/reference/android/view/View.html#setDrawingCacheEnabled(boolean)
        //      http://developer.android.com/reference/android/view/View.html#getDrawingCache()
        this.setDrawingCacheEnabled(true);

        _paint.setColor(Color.CYAN);
        _paint.setAlpha(255);
        _paint.setAntiAlias(true);
        _paint.setStyle(Paint.Style.FILL);
        _paint.setStrokeWidth(4);
        _paint.setStrokeJoin(Paint.Join.ROUND);
        _paint.setStrokeCap(Paint.Cap.ROUND);

        _paintBorder.setColor(Color.BLACK);
        _paintBorder.setStrokeWidth(3);
        _paintBorder.setStyle(Paint.Style.STROKE);
        _paintBorder.setAlpha(150);

        _paintBackground.setColor(Color.WHITE);
        _paintBackground.setStyle(Paint.Style.FILL);
    }

    public void setData(double[] values, String[] labels) {
        _values = values;
        _labels = labels;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;
        int radius = (int)Math.min(canvas.getWidth() / 4, canvas.getHeight() / 3);

        int minX = centerX - radius;
        int maxX = centerX + radius;
        int minY = centerY - radius;
        int maxY = centerY + radius;

        if (_values != null) {
            for (int i = 0; i < _values.length; i++) {

            }
        }

        canvas.drawRect(centerX - radius, centerY - radius,
                        centerX + radius, centerY + radius, _paintBorder);
    }
}