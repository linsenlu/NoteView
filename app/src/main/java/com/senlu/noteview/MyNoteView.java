package com.senlu.noteview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 实现类似记事本的自定义控件，继承自EditText：
 *
 * 简单的自定义控件步骤：
 * 1、新建类继承自某控件，重写两个构造方法，一个构造方法是在代码中new该自定义控件时回掉的
 *    另一个构造方法是在布局文件中实例化该控件调用的
 * 2、重写onDraw()方法
 * Created by Administrator on 2016/10/21.
 */
public class MyNoteView extends EditText {

    private Paint paint;
    private int lineColor;
    private int lineWidth;

    public MyNoteView(Context context) {
        this(context,null);
    }

    public MyNoteView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * 自定义属性步骤：
         * 1、在res目录下新建resource文件，声明declare—styleable属性
         * 2、在布局文件中使用该自定义的属性，声明命名空间
         * 3、通过context.obtainStyledAttributes()在自定义控件代码中获取布局文件中声明的属性值
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.note_attrs);
        lineColor = typedArray.getColor(R.styleable.note_attrs_line_color, Color.GRAY);
        lineWidth = (int) typedArray.getDimension(R.styleable.note_attrs_line_width, 1.0f);

        initPaint();
    }

    //由于哦你Draw（）会频繁调用，因此对象的申明创建应在onDraw()方法外
    private void initPaint() {
        paint = new Paint();
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        paint.setAntiAlias(true);   //消锯齿
        paint.setDither(true);      //防抖动
    }

    public void setColor(int color){
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算出需要画的行数
        int count = getHeight()/getLineHeight();
        //如果行数大于空间高度时，获取行数重新赋值给count
        count = count>getLineCount()?count:getLineCount();
        for (int i = 1; i <= count; i++) {
            canvas.drawLine(0,getLineHeight()*i,getWidth(),getLineHeight()*i,paint);
        }

    }
}
