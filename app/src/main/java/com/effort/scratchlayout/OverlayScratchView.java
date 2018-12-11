package com.effort.scratchlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class OverlayScratchView extends View {

    private static final String TAG = ScratchView.class.getSimpleName();
    private Paint bitmapPaint;
    private Paint revealPaint;
    private Bitmap sampleBitmap;

    private Path revealPath = new Path();

    public OverlayScratchView(Context context) {
        super(context);
        init();
    }

    public OverlayScratchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OverlayScratchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        sampleBitmap = createImage(500,500, Color.RED);
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        revealPaint = new Paint();
        revealPaint.setAntiAlias(true);
        revealPaint.setDither(true);
        revealPaint.setColor(0x00000000);
        revealPaint.setStyle(Paint.Style.STROKE);
        revealPaint.setStrokeJoin(Paint.Join.BEVEL);
        revealPaint.setStrokeCap(Paint.Cap.ROUND);
        revealPaint.setStrokeWidth(12f * 6);
        revealPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw()");
        canvas.drawBitmap(sampleBitmap, 0, 0, bitmapPaint);
        canvas.drawPath(revealPath, revealPaint);
    }

    int touchX = 0;
    int touchY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                revealPath.reset();
                revealPath.moveTo(x, y);
                touchX = x;
                touchY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                revealPath.quadTo(touchX, touchY, (x + touchX) / 2, (y + touchY) / 2);
                touchX = x;
                touchY = y;
                break;

        }
        invalidate();
        return true;
    }

    public static Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }
}
