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

    private ImageView _imageView;

    private Canvas _offScreenCanvas = null;
    private Bitmap _offScreenBitmap = null;

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

        //_paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
    }

    public void setData(double[] values, String[] labels) {
        _values = values;
        _labels = labels;
        invalidate();
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh){

        Bitmap bitmap = getDrawingCache();
        Log.v("onSizeChanged", MessageFormat.format("bitmap={0}, w={1}, h={2}, oldw={3}, oldh={4}", bitmap, w, h, oldw, oldh));
        if(bitmap != null) {
            _offScreenBitmap = getDrawingCache().copy(Bitmap.Config.ARGB_8888, true);
            _offScreenCanvas = new Canvas(_offScreenBitmap);
        }
    }

    /**
     * Sets the ImageView, which hosts the image that we will paint in this view
     * @param imageView
     */
    public void setImageView(ImageView imageView){
        _imageView = imageView;
    }

    /**
     * Clears the painting
     */
    public void clearPainting(){
        _offScreenCanvas.drawRect(0, 0, _offScreenCanvas.getWidth(), _offScreenCanvas.getHeight(), _paintBackground);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(_offScreenBitmap != null) {
            _paint.setAlpha(255);
            canvas.drawBitmap(_offScreenBitmap, 0, 0, _paint);
        }

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

        canvas.drawCircle(centerX, centerY, radius, _paintBorder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        //Basically, the way this works is to listen for Touch Down and Touch Move events and determine where those
        //touch locations correspond to the bitmap in the ImageView. You can then grab info about the bitmap--like the pixel color--
        //at that location

        invalidate();

        return true;
    }

    public Bitmap getBitmap() {
        return _offScreenBitmap;
    }
}