package com.pxq.myapplication.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 文本闪烁效果
 */
public class SplashTextView extends View {

    private static final String TAG = "SplashTextView";

    private static final int DURATION = 1500;

    private Paint mPaint;

    //闪烁白光的宽度
    private int mSplashWidth;

    //渐变色，用来绘制闪烁效果
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;

    //获取文本宽高
    private Rect mTextRect;

    //要画的文本
    private String mText = "";
    private int mTextSize = 20;

    //动画
    private ValueAnimator mValueAnimator;

    public SplashTextView(Context context) {
        super(context);
        init();
    }

    public SplashTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getContext().getResources().getDisplayMetrics()));
        mPaint.setAntiAlias(true);

        mTextRect = new Rect();


        mMatrix = new Matrix();

    }

    /**
     * 获取文本宽高
     * @param text
     * @param rect
     */
    private void getTextWidth(String text, Rect rect) {
        mPaint.getTextBounds(text, 0, text.length(), rect);
        rect.width();
        rect.height();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawText(mText, 0, mTextRect.height(), mPaint);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        startAnim();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnim();
    }

    public void setText(String text){
        mText = text;
        initGradient();
        startAnim();
    }

    /**
     * 初始化线性渐变
     */
    private void initGradient(){
        getTextWidth(mText, mTextRect);

        mSplashWidth = mTextRect.width() / 3;
        //初始化线性渐变
        mLinearGradient = new LinearGradient(-mSplashWidth, 0, 0, mTextRect.height(),
                //渐变颜色
                new int[]{Color.parseColor("#CACACA"), Color.RED, Color.parseColor("#CACACA")},
                new float[]{0.2f, 0.5f, 0.8f},
                Shader.TileMode.CLAMP);

        mLinearGradient.setLocalMatrix(mMatrix);

        mPaint.setShader(mLinearGradient);
    }

    private void startAnim() {
        stopAnim();
        mValueAnimator = ValueAnimator.ofInt(0, mSplashWidth + mTextRect.width());
        mValueAnimator.setDuration(DURATION);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                //改变渐变的位置
                mMatrix.setTranslate(value, 0);
                //这里要重新设置Matrix
                mLinearGradient.setLocalMatrix(mMatrix);
//                Log.d(TAG, "onAnimationUpdate: " + value);
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    private void stopAnim() {
        if (mValueAnimator == null){
            return;
        }
        mValueAnimator.cancel();
    }

}
