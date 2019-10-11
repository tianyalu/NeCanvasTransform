package com.sty.ne.canvas.transform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画布变换操作
 * Created by tian on 2019/10/11.
 */

public class TransformView extends View {
    private Paint mPaint;

    public TransformView(Context context) {
        this(context, null);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1.平移操作
//        canvas.drawRect(0, 0, 400, 400, mPaint);
//        //平移后相当于坐标系不动，移动了整个画布 参考show/transform.png
//        canvas.translate(50, 50);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0, 0, 400, 400, mPaint);
//        canvas.drawLine(0, 0, 600, 600, mPaint);

        //2.缩放操作
//        canvas.drawRect(200, 200, 700, 700, mPaint);
//        //缩放后相当于坐标系不动，缩放了整个画布 参考show/scale.png  参考show/scale1.png
////        canvas.scale(0.5f, 0.5f);
//        //先translate(px, py),再scale(sx, sy),再translate(-px, -py) 参考show/scale2.png
//        canvas.scale(0.5f, 0.5f, 200, 200);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200, 200, 700, 700, mPaint);

        //3.旋转操作 若先平移后旋转，旋转中心点以平移后的坐标系为基准
//        canvas.translate(50, 50);
//        canvas.drawRect(0, 0, 700, 700, mPaint);
//        canvas.rotate(45); //默认方向顺时针旋转 参考show/rotate1.png
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0, 0, 700, 700, mPaint);

//        canvas.drawRect(400, 400, 900, 900, mPaint);
//        canvas.rotate(45, 650, 650); //px, py表示旋转中心坐标 参考show/rotate2.png
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(400, 400, 900, 900, mPaint);

        //4.倾斜操作
//        canvas.drawRect(0, 0, 400, 400, mPaint);
////        canvas.skew(1, 0); //在x方向上倾斜45° -> y轴逆时针旋转45° 参考show/skew1.png
//        canvas.skew(0, 1); //在y方向上倾斜45° -> x轴顺时针旋转45° 参考show/skew2.png
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0, 0, 400, 400, mPaint);

        //5.切割操作
//        canvas.drawRect(200, 200, 700, 700, mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200, 800, 700, 1300, mPaint);
//        //裁剪之后的绘制只会在裁剪区域内生效 参考show/clip1.png
//        canvas.clipRect(200, 200, 700, 700);  //画布被裁剪
//        canvas.drawCircle(100, 100, 100, mPaint); //坐标超出裁剪区域，无法绘制
//        canvas.drawCircle(300, 300, 100, mPaint); //坐标未超出裁剪区域，可以绘制
//
//        canvas.drawRect(200, 200, 700, 700, mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200, 800, 700, 1300, mPaint);
//        //反向裁剪 裁剪之后的绘制只会在裁剪区域外生效 参考show/clip2.png
//        canvas.clipOutRect(200, 200, 700, 700);  //画布被裁剪
//        canvas.drawCircle(100, 100, 100, mPaint); //坐标未超出反向裁剪区域，绘制成功
//        canvas.drawCircle(300, 300, 100, mPaint); //坐标超出反向裁剪区域，无法绘制

        //6.矩阵操作
        canvas.drawRect(0, 0, 700, 700, mPaint);
        Matrix matrix = new Matrix();
//        matrix.setTranslate(50, 50); //参考show/matrix_translate.png
//        matrix.setRotate(45);  //参考show/matrix_rotate.png
        matrix.setScale(0.5f, 0.5f); //参考show/matrix_scale.png
        canvas.setMatrix(matrix);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, 700, 700, mPaint);

    }
}
