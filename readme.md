### NeCanvasTransform Canvas变换操作示例
#### 一、平移操作
平移后相当于画布不动，移动了整个参考坐标系
```android
    canvas.drawRect(0, 0, 400, 400, mPaint);
    //平移后相当于画布不动，移动了整个参考坐标系 参考show/transform.png
    canvas.translate(50, 50);
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(0, 0, 400, 400, mPaint);
    canvas.drawLine(0, 0, 600, 600, mPaint);
```
示例如下：  
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/transform.png)
#### 二、缩放操作
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
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/scale1.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/scale2.png)
#### 三、缩放操作
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
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/rotate1.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/rotate2.png)
#### 四、倾斜操作
```android
   canvas.drawRect(0, 0, 400, 400, mPaint);
// canvas.skew(1, 0); //在x方向上倾斜45° -> y轴逆时针旋转45° 参考show/skew1.png
   canvas.skew(0, 1); //在y方向上倾斜45° -> x轴顺时针旋转45° 参考show/skew2.png
   mPaint.setColor(Color.GRAY);
   canvas.drawRect(0, 0, 400, 400, mPaint);
```
示例如下：  
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/skew1.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/skew2.png)
#### 五、切割操作
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
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/clip1.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/clip2.png)
#### 六、矩阵操作
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
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_translate.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_rotate.png)
![image](http://github.com/tianyalu/NeCanvasTransform/blob/master/show/matrix_scale.png)