package com.effort.scratchlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ScratchView extends FrameLayout {

    private static final String TAG = ScratchView.class.getSimpleName();
    private OverlayScratchView overlayScratchView;

    public ScratchView(@NonNull Context context) {
        super(context);
        init();
    }

    public ScratchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d(TAG, "init()");
        overlayScratchView = new OverlayScratchView(getContext());
    }

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "onFinishInflate()");
        super.onFinishInflate();
        addView(overlayScratchView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout()");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int totalHeight = MeasureSpec.getSize(heightMeasureSpec);

        overlayScratchView.measure(
                MeasureSpec.makeMeasureSpec(totalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY));
        setMeasuredDimension(totalWidth, totalHeight);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent()");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");
        return super.onTouchEvent(event);
    }


}
