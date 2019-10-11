### NeCanvasTransform Canvas变换操作示例
### 一、画布的变换操作
#### 1、平移操作
平移后相当于坐标系不动，移动了整个画布
```android
    canvas.drawRect(0, 0, 400, 400, mPaint);
    //平移后相当于坐标系不动，移动了整个画布 参考show/transform.png
    canvas.translate(50, 50);
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(0, 0, 400, 400, mPaint);
    canvas.drawLine(0, 0, 600, 600, mPaint);
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/transform.png)
#### 2、缩放操作
```android
    canvas.drawRect(200, 200, 700, 700, mPaint);
    //缩放后相当于坐标系不动，缩放了整个画布 参考show/scale.png  参考show/scale1.png
    canvas.scale(0.5f, 0.5f);
    //先translate(px, py),再scale(sx, sy),再translate(-px, -py) 参考show/scale2.png
    //canvas.scale(0.5f, 0.5f, );
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(200, 200, 700, 700, mPaint);
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/scale1.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/scale2.png)
#### 3、缩放操作
若先平移后旋转，旋转中心点以平移后的坐标系为基准
```android
    canvas.translate(50, 50);
    canvas.drawRect(0, 0, 700, 700, mPaint);
    canvas.rotate(45); //默认方向顺时针旋转 参考show/rotate1.png
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(0, 0, 700, 700, mPaint);
    
    canvas.drawRect(400, 400, 900, 900, mPaint);
    canvas.rotate(45, 650, 650); //px, py表示旋转中心坐标 参考show/rotate2.png
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(400, 400, 900, 900, mPaint);
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/rotate1.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/rotate2.png)
#### 4、倾斜操作
```android
   canvas.drawRect(0, 0, 400, 400, mPaint);
// canvas.skew(1, 0); //在x方向上倾斜45° -> y轴逆时针旋转45° 参考show/skew1.png
   canvas.skew(0, 1); //在y方向上倾斜45° -> x轴顺时针旋转45° 参考show/skew2.png
   mPaint.setColor(Color.GRAY);
   canvas.drawRect(0, 0, 400, 400, mPaint);
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/skew1.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/skew2.png)
#### 5、切割操作
```android
    canvas.drawRect(200, 200, 700, 700, mPaint);
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(200, 800, 700, 1300, mPaint);
    //裁剪之后的绘制只会在裁剪区域内生效 参考show/clip1.png
    canvas.clipRect(200, 200, 700, 700);  //画布被裁剪
    canvas.drawCircle(100, 100, 100, mPaint); //坐标超出裁剪区域，无法绘制
    canvas.drawCircle(300, 300, 100, mPaint); //坐标未超出裁剪区域，可以绘制
    
    canvas.drawRect(200, 200, 700, 700, mPaint);
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(200, 800, 700, 1300, mPaint);
    //反向裁剪 裁剪之后的绘制只会在裁剪区域外生效 参考show/clip2.png
    canvas.clipOutRect(200, 200, 700, 700);  //画布被裁剪
    canvas.drawCircle(100, 100, 100, mPaint); //坐标未超出反向裁剪区域，绘制成功
    canvas.drawCircle(300, 300, 100, mPaint); //坐标超出反向裁剪区域，无法绘制
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/clip1.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/clip2.png)
#### 6、矩阵操作
```android
   canvas.drawRect(0, 0, 700, 700, mPaint);
   Matrix matrix = new Matrix();
// matrix.setTranslate(50, 50); //参考show/matrix_translate.png
// matrix.setRotate(45);  //参考show/matrix_rotate.png
   matrix.setScale(0.5f, 0.5f); //参考show/matrix_scale.png
   canvas.setMatrix(matrix);
   mPaint.setColor(Color.GRAY);
   canvas.drawRect(0, 0, 700, 700, mPaint);
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_translate.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_rotate.png)
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_scale.png)

### 二、画布状态保存与恢复 栈原理
#### 1. canvas.save()
```aidl
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
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/save_restore1.png)

#### 2. canvas.saveLayer()
```aidl
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
```
示例如下：  
![image](https://github.com/tianyalu/NeCanvasTransform/blob/master/show/save_restore2.png)
