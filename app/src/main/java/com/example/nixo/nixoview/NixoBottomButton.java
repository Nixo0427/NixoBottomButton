package com.example.nixo.nixoview;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class NixoBottomButton extends android.support.v7.widget.AppCompatButton {

    private boolean mIsClick;
    private int mCircleColor;
    private int mPaddingBottom;
    private Paint mPaint;
    private int mRaids;
    private int mCircleFrameColor;
    private int mCircleFrame;
    private int mBackground;
    private int mIcon;
    private Bitmap plus;
    private Animator mPlusAnimator;
    private static final String TAG = "JSY";
    private String mText = "工具栏";
    private int mTextSize;

    public NixoBottomButton(Context context) {
        this(context,null);
    }

    public NixoBottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.NixoBottomButton);
        mTextSize = (int)typedArray.getFloat(R.styleable.NixoBottomButton_textSize,30);
//        mText = typedArray.getString(R.styleable.NixoBottomButton_android_text);
        mIsClick = typedArray.getBoolean(R.styleable.NixoBottomButton_isClick,false);
        mCircleColor = typedArray.getColor(R.styleable.NixoBottomButton_circleColor, Color.parseColor("#a3cf62"));
        mBackground = typedArray.getColor(R.styleable.NixoBottomButton_android_background, Color.parseColor("#00000000"));
        mPaddingBottom = typedArray.getInt(R.styleable.NixoBottomButton_circlePaddingBottom,20);
        mRaids = typedArray.getInt(R.styleable.NixoProgessBar_CircleRaids,0);
        mCircleFrameColor = typedArray.getColor(R.styleable.NixoBottomButton_circleFrameColor,Color.WHITE);
        mCircleFrame = typedArray.getInt(R.styleable.NixoBottomButton_circleFrame,5);
        mPlusAnimator = AnimatorInflater.loadAnimator(context,R.animator.plus_animator);

        mIcon = R.mipmap.plus;
        plus  = ((BitmapDrawable) getContext().getResources().getDrawable(mIcon)).getBitmap();

        Log.i(TAG, String.valueOf(mPlusAnimator == null));

        typedArray.recycle();
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int SpecWidth = MeasureSpec.getSize(widthMeasureSpec);
        int SpecWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int SpecHeight = MeasureSpec.getSize(heightMeasureSpec);
        int SpecHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if(SpecWidthMode == MeasureSpec.EXACTLY){
            width = SpecWidth;
        }else{
            int needWidth = MeasureWidth() + getPaddingLeft() + getPaddingRight();
            if(SpecWidthMode == MeasureSpec.AT_MOST){
                width = Math.min(needWidth,width);
            }else{
                width = needWidth;
            }
        }

        if(SpecHeightMode == MeasureSpec.EXACTLY){
            height = (int) (SpecHeight + width/2);
        }else{
            int needHeight = MeasureHeight() + getPaddingBottom() + getPaddingEnd();
            if(SpecHeightMode == MeasureSpec.AT_MOST){
                height = Math.min(needHeight,height);
            }else{
                height = needHeight;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int MeasureHeight() {
        return getHeight() + mPaddingBottom;
    }

    private int MeasureWidth() {
        return getWidth() + mCircleFrame;
    }

    public void setIcon(int icon){
        mIcon = icon;
    }
    public int getIcon(){
        return mIcon;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRaids = getWidth()/2 - mCircleFrame;
        Log.i("Nixo", ""+mRaids);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCircleColor);
        canvas.drawCircle(getWidth()/2 , getHeight()/2 - mPaddingBottom  , mRaids  , mPaint);

         //获取背景图片
        Log.i(TAG, "onDraw的plus是否为空"+String.valueOf(plus == null));
        canvas.drawBitmap(plus, (getWidth()-plus.getWidth())/2, mRaids, mPaint); //画背景图片
        mPaint.setColor(mBackground);
        canvas.drawRect(0,0 ,getWidth(),getHeight()/2,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mCircleFrameColor);
        mPaint.setStrokeWidth(mCircleFrame);
        canvas.drawCircle(getWidth()/2,getHeight()/2 - mPaddingBottom,mRaids + mCircleFrame - mPaint.getStrokeWidth(), mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(mText,getWidth()/2,getHeight()-(mPaddingBottom),mPaint);
    }

    private static final String BACKGROUND_COLOR = "background_color";
    private static final String SUPER = "super";


    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR,mBackground);
        bundle.putParcelable(SUPER,super.onSaveInstanceState());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mBackground = bundle.getInt(BACKGROUND_COLOR);
            Parcelable parcelable = bundle.getParcelable(SUPER);
            super.onRestoreInstanceState(parcelable);
            return;
        }
        super.onRestoreInstanceState(state);

    }


    public void setmPlusAnimator(ObjectAnimator mPlusAnimator) {
        this.mPlusAnimator = mPlusAnimator;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        Log.i(TAG, "onCilick的plus是否为空: "+String.valueOf(plus == null));
        Log.i(TAG, String.valueOf(mPlusAnimator == null));
        mPlusAnimator.setTarget(plus);
        mPlusAnimator.start();


        Log.i("JSY", "setOnClickListener: " );
    }
}
