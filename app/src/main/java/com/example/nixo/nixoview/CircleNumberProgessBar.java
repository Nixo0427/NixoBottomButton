package com.example.nixo.nixoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CircleNumberProgessBar extends View{


    private int mCircleColor;
    private int mTextSize;
    private int mCircleRaids;
    private int mProgress;
    private Paint mPaint;


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mCircleColor);
    }
    public CircleNumberProgessBar(Context context) {
        this(context,null);
    }

    public CircleNumberProgessBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.NixoProgessBar);
        mCircleColor = typedArray.getInt(R.styleable.NixoProgessBar_CircleColor,Color.parseColor("#FF1493"));
        mTextSize = (int) typedArray.getDimension(R.styleable.NixoProgessBar_android_textSize ,px2dp(30) );
        mCircleRaids = (int) typedArray.getDimension(R.styleable.NixoProgessBar_CircleRaids,px2dp(40));
        mProgress = typedArray.getInt(R.styleable.NixoProgessBar_android_progress,0);

        initPaint();


    }

    private float px2dp(int dp){

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int SpecWidth = MeasureSpec.getSize(widthMeasureSpec);
        int SpecWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int SpecHeight = MeasureSpec.getMode(widthMeasureSpec);
        int SpecHeightMode = MeasureSpec.getMode(widthMeasureSpec);

        int Width = 0;
        int Height = 0;

        // 高度
        if(SpecHeightMode == MeasureSpec.EXACTLY) {
            Height = SpecHeight;
        }else{
            int needHeight = MeasureHeight() + getPaddingBottom() + getPaddingTop();
            if(SpecHeightMode == MeasureSpec.AT_MOST){
                Height = Math.min(SpecHeight,needHeight);
            }else{
                Height = needHeight;
            }
        }

        //宽度
        if(SpecWidthMode == MeasureSpec.EXACTLY){
            Width = SpecWidth;
        }else{
            int needWidth = MeasureWidth() + getPaddingLeft() + getPaddingRight();
            if(SpecWidthMode == MeasureSpec.AT_MOST){
                Width = Math.min(SpecWidth,needWidth);
            }else{
                Width = needWidth;
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int MeasureHeight() {
        return mCircleRaids * 2;
    }

    private int MeasureWidth() {
        return mCircleRaids * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(width/2,height/2,width/2- getPaddingLeft() - mPaint.getStrokeWidth()/2,mPaint);
        float progress = mProgress *1.0f / 100  * 360;
        canvas.save();
        canvas.translate(getPaddingLeft(),getPaddingTop());
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.parseColor("#8A2BE2"));
        canvas.drawArc(new RectF(0,0,width - getPaddingLeft()*2,height-getPaddingTop()*2),0,progress,false,mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setStrokeWidth(0);
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        Rect rect = new Rect();
        String text = mProgress+"%";
        mPaint.getTextBounds(text,0,text.length(),rect);
        int textHeight = rect.height();
        canvas.drawText(text,0,text.length(),getWidth()/2,getHeight()/2+textHeight/2 ,mPaint);
        canvas.restore();
    }

    private int getProgress(){
        return mProgress;
    }

    public void setProgress(int progress){
        mProgress = progress;
        invalidate();
    }

}
