package com.jnu.student.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jnu.student.R;

import java.util.Calendar;
import android.graphics.Matrix;
public class ClockView extends View {
    Bitmap clockDialBitmap;
    Bitmap secondDialBitmap;
    Bitmap hourDialBitmap;
    Bitmap minuteDialBitmap;
    private int viewWidth;
    private int viewHeight;
    private int centerX;
    private int centerY;
    private int radius;
    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化表盘
        clockDialBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clock);
        //秒针
        secondDialBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.second);
        //时针
        hourDialBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hour);
        //分针
        minuteDialBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.minute);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh); // 调用父类的onSizeChanged方法

        viewWidth = w; // 将传入的新宽度赋给viewWidth变量
        viewHeight = h; // 将传入的新高度赋给viewHeight变量

        centerX = viewWidth / 2; // 计算中心点的x坐标为viewWidth的一半
        centerY = viewHeight / 2; // 计算中心点的y坐标为viewHeight的一半

        radius = Math.min(viewWidth, viewHeight) / 2 - 20; // 计算半径为宽度和高度中较小值的一半，并减去20作为圆的半径
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制表盘图片
        //canvas.drawBitmap(clockDialBitmap, centerX - clockDialBitmap.getWidth() / 2, centerY - clockDialBitmap.getHeight() / 2, null);
        // 计算表盘应该绘制的大小
        float scaledWidth = viewWidth ; // 调整表盘宽度
        float scaledHeight = clockDialBitmap.getHeight() * (scaledWidth / clockDialBitmap.getWidth());


        // 缩放表盘图片
        Bitmap scaledClockDialBitmap = Bitmap.createScaledBitmap(clockDialBitmap, (int) scaledWidth, (int) scaledHeight, true);

        // 绘制表盘图片
        canvas.drawBitmap(scaledClockDialBitmap, centerX - scaledWidth / 2, centerY - scaledHeight / 2, null);

        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        // 计算指针的角度
        float hourAngle = (hour % 12 + minute / 60.0f) * 360 / 12;
        float minuteAngle = (minute + second / 60.0f) * 360 / 60;
        float secondAngle = second * 360 / 60;

        // 旋转并绘制时针
        Matrix hourMatrix = new Matrix();
        hourMatrix.setRotate(hourAngle, hourDialBitmap.getWidth() / 2, hourDialBitmap.getHeight() / 2);
        hourMatrix.postTranslate(centerX - hourDialBitmap.getWidth() / 2, centerY - hourDialBitmap.getHeight() / 2);
        canvas.drawBitmap(hourDialBitmap, hourMatrix, null);

        // 旋转并绘制分针
        Matrix minuteMatrix = new Matrix();
        minuteMatrix.setRotate(minuteAngle, minuteDialBitmap.getWidth() / 2, minuteDialBitmap.getHeight() / 2);
        minuteMatrix.postTranslate(centerX - minuteDialBitmap.getWidth() / 2, centerY - minuteDialBitmap.getHeight() / 2);
        canvas.drawBitmap(minuteDialBitmap, minuteMatrix, null);

        // 旋转并绘制秒针
        Matrix secondMatrix = new Matrix();
        secondMatrix.setRotate(secondAngle, secondDialBitmap.getWidth() / 2, secondDialBitmap.getHeight() / 2);
        secondMatrix.postTranslate(centerX - secondDialBitmap.getWidth() / 2, centerY - secondDialBitmap.getHeight() / 2);
        canvas.drawBitmap(secondDialBitmap, secondMatrix, null);

        // 每秒钟刷新界面
        postInvalidateDelayed(1000);
    }
}