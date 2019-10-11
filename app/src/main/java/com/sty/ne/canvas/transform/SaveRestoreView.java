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
import android.util.Log;
import android.view.View;

/**
 * 画布状态保存与恢复 栈原理
 * Created by tian on 2019/10/11.
 */

public class SaveRestoreView extends View {
    private Paint mPaint;

    public SaveRestoreView(Context context) {
        this(context, null);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(200, 200, 700, 700, mPaint);
        Log.i("sty", "onDraw0: " + canvas.getSaveCount()); //1
        int state = canvas.save();
        Log.i("sty", "onDraw1: " + canvas.getSaveCount()); //2
        canvas.translate(50, 50);

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, 500, 500, mPaint);

        canvas.save();
        Log.i("sty", "onDraw2: " + canvas.getSaveCount()); //3
        canvas.translate(50, 50);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 500, 500, mPaint);

//        canvas.restore();
        Log.i("sty", "onDraw3: " + canvas.getSaveCount()); //2
//        canvas.restore();
        canvas.restoreToCount(state); //回退到具体的某一次状态
        Log.i("sty", "onDraw4: " + canvas.getSaveCount()); //1
        canvas.drawLine(0, 0, 400, 500, mPaint);



        //saveLayer新建一个图层，下面的操作在该图层上操作，该图层可指定大小
        canvas.drawRect(200, 200, 700, 700, mPaint);
        int layerId = canvas.saveLayer(0, 0, 700, 700, mPaint);
        mPaint.setColor(Color.GRAY);
        Matrix matrix = new Matrix();
        matrix.setTranslate(100, 100);
        canvas.setMatrix(matrix);
        canvas.drawRect(0, 0, 700, 700, mPaint); //由于平移操作，导致绘制的矩形超出了图层的大小，所以绘制不完全
        canvas.restoreToCount(layerId);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 100, 100, mPaint);
    }
}
