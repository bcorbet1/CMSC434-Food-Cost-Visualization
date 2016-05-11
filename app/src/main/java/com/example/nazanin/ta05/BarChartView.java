package com.example.nazanin.ta05;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jon on 3/20/2016.
 */
public class BarChartView extends View {

    private Paint _paint = new Paint();
    private Paint _paintText = new Paint();
    private Paint _paintBorder = new Paint();
    private Paint _paintShadow = new Paint();
    private Paint _paintBackground = new Paint();

    private int[] _colors = {0xFF57B196, 0xFFFFD25A};

//            {Color.CYAN, Color.YELLOW, Color.MAGENTA,
//            Color.GREEN, Color.RED, Color.BLUE, Color.GRAY};

    private double _maxValue;
    private double[] _values;
    private String[] _labels;

    private boolean _vertical = true;

    public BarChartView(Context context) {
        super(context);
        init(null, 0);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyle) {
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

        _paintText.setColor(Color.BLACK);
        _paintText.setTextSize(80);
        _paint.setAntiAlias(true);

        _paintShadow.setColor(Color.BLACK);
        _paintShadow.setStrokeWidth(3);
        _paintShadow.setStyle(Paint.Style.FILL);
        _paintShadow.setAlpha(50);

        _paintBorder.setColor(Color.BLACK);
        _paintBorder.setStrokeWidth(3);
        _paintBorder.setStyle(Paint.Style.STROKE);
        _paintBorder.setAlpha(50);

        _paintBackground.setColor(Color.WHITE);
        _paintBackground.setStyle(Paint.Style.FILL);
    }

    public void setData(double[] values, double maxValue, String[] labels) {
        _values = values;
        _maxValue = maxValue;
        _labels = labels;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        int minX = 0;
        int maxX = canvas.getWidth();
        int minY = 0;
        int maxY = canvas.getHeight();

        canvas.drawRect(minX, minY, maxX, maxY, _paintBackground);

        if (_values != null) {

            double[] normalizedValues = new double[_values.length];

            for (int i = 0; i < _values.length; i++) {
                normalizedValues[i] = _values[i] / _maxValue;
            }

            double maxHeight = maxY - minY - 50;
            int width = (int)((maxX - minX) / _values.length);

            for (int i = 0; i < _values.length; i++) {
                _paint.setColor(_colors[i]);

                if (_vertical) {
                    int span = (int)(maxHeight * normalizedValues[i]);
                    int spacing = width / 8;
                    int start = spacing / 2 + minY + width * i;

                    canvas.drawRect(start, maxY - span, start + width - spacing, maxY, _paint);
                    canvas.drawRect(start, maxY - span, start + width - spacing, maxY, _paintBorder);
                    canvas.drawText(String.format("$%.2f", _values[i]), start, maxY - span - 35, _paintText);
                }
            }
        }
    }
}