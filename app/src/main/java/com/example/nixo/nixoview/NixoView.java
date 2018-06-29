package com.example.nixo.nixoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Measure;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class NixoView extends View {

    private String defText = "Nixo";
    private Paint paint ;



    public NixoView(Context context) {
        this(context,null);
    }

    public NixoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray array = context.obtainStyledAttributes(R.styleable.NixoView);
        int number = array.getInteger(R.styleable.NixoView_number,1);


        int index = array.getIndexCount();
        for (int i = 0; i < index; i++) {
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.NixoView_text:
                    defText = array.getString(R.styleable.NixoView_text);
                    break;
            }
        }
        Log.i("Nixo", "NixoView: "+","+""+number+","+defText);


    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#FFB6C1"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int Specwidth = MeasureSpec.getSize(widthMeasureSpec);
        int SpecwidthMode = MeasureSpec.getMode(widthMeasureSpec);

        int width = 0;

        if(SpecwidthMode == MeasureSpec.EXACTLY){
            width = Specwidth;
        }else{

            int needWidth = MeasureWidth() + getPaddingLeft() + getPaddingRight();

            if(SpecwidthMode == MeasureSpec.AT_MOST){
                //AT_MOST 就是不能大于父控件(Warp_content)，所以这里把设置的大小和父控件的大小去比较，取最小值
                width = Math.min(needWidth , Specwidth);
            }else{
                //这里的Mode是 UNSPECIFIED（不限定宽高)
                // 所以是测量的是多大，他就显示多大.
                width = needWidth;
            }

        }

        int Specheight = MeasureSpec.getSize(heightMeasureSpec);
        int SpecheightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;
        if(SpecheightMode == MeasureSpec.EXACTLY){
            height = Specheight;
        }else{
            int needHeight = MeasureHeight() + getPaddingTop() + getPaddingBottom();
            if(SpecheightMode == MeasureSpec.AT_MOST){
                height = Math.min(Specheight,height);
            }else{
                height = needHeight;
            }
        }



        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }

    private int MeasureHeight() {
        return 0;
    }

    private int MeasureWidth() {
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2 - paint.getStrokeWidth()/2, paint);
//        paint.setStrokeWidth(2);
////      drawLine四个参数分别是 X起始位置y起始位置，x长度，y长度
//        canvas.drawLine(getWidth()/2,getHeight()/2 ,getWidth(),getHeight()/2,paint);
////        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight() ,paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(0);
          canvas.drawText(defText,0,getHeight()/2,paint);


    }

    private static final String INSTANCE = "instance";
    private static final String SUPER = "super";


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        defText = "baka";
        invalidate();
        return true;
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putString(INSTANCE,defText);
        bundle.putParcelable(SUPER,super.onSaveInstanceState());


        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            defText = bundle.getString(INSTANCE);
            Parcelable parcelable = bundle.getParcelable(SUPER);
            super.onRestoreInstanceState(parcelable);
            return;
        }
        super.onRestoreInstanceState(state);

    }
}
