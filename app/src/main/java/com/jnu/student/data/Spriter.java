package com.jnu.student.data; // 声明包名

import android.content.Context; // 导入 Android 内容包
import android.graphics.Bitmap; // 导入 Bitmap 类
import android.graphics.Canvas; // 导入 Canvas 类
import android.graphics.Paint; // 导入 Paint 类
import android.graphics.Rect; // 导入 Rect 类
import android.graphics.drawable.BitmapDrawable; // 导入 BitmapDrawable 类

import com.jnu.student.R; // 导入 R 类（假设这是资源类）

import java.util.Random;

public class Spriter { // 创建 Spriter 类
    public static int  score = 0;
    float x, y; // 声明坐标变量
    int drawableResourceId; // 声明绘制资源 ID 变量
    int maxHeight;
    int maxWidth;
    float direction; // 声明方向变量
    Context context; // 声明上下文对象
    boolean isTouched;
    Bitmap bitmap ;

    public Spriter(Context context,int i) { // Spriter 类的构造函数，接收 Context 类型的参数
        this.context = context; // 初始化上下文对象
        bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.book_no_name)).getBitmap();
        /*if(i == 1)
        {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha1)).getBitmap();
        }
        if(i == 2)
        {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha2)).getBitmap();
        }
        if(i == 3)
        {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha3)).getBitmap();
        }
        if(i == 4)
        {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha4)).getBitmap();
        }
        if(i == 5)
        {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha5)).getBitmap();
        }*/
        // // 获取位图资源
        score = 0;
    }

    public void move(float maxHeight, float maxWidth) { // 移动方法，接收最大高度和宽度作为参数
        this.maxHeight = (int)maxHeight;
        this.maxWidth = (int)maxWidth;
        if (Math.random() < 0.05) { // 如果随机数小于 0.05
            setDirection((float) (Math.random() * 2 * Math.PI)); // 设置新的方向
        }
        x = (float) (x + 30 * Math.cos(direction)); // 更新 x 坐标
        y = (float) (y + 30 * Math.sin(direction)); // 更新 y 坐标

        // 边界处理
        if (x < 0 || x > maxWidth - bitmap.getWidth()) {
            x = Math.min(Math.max(x, 0), maxWidth - bitmap.getWidth());
            direction = (float) (Math.PI - direction); // 反向
        }
        if (y < 0 || y > maxHeight - bitmap.getHeight()) {
            y = Math.min(Math.max(y, 0), maxHeight - bitmap.getHeight());
            direction = -direction; // 垂直方向反向
        }
    }
    public boolean isTouched(float touchX, float touchY) {
        // 计算触摸点是否在图像的范围内
        return touchX >= x && touchX <= x + bitmap.getWidth() && touchY >= y && touchY <= y + bitmap.getHeight();
    }

    public boolean isClicked() {
        return isTouched;
    }

    public void setClicked(boolean clicked) {
        isTouched = clicked;
    }
    public void draw(Canvas canvas) { // 绘制方法，接收 Canvas 对象作为参数
        // 初始化源矩形和目标矩形
        Rect mSrcRect, mDestRect;

        Paint mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 创建画笔对象
        Random random = new Random();
        int h = random.nextInt(1);
        Bitmap bit = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.haha1)).getBitmap();
        mBitPaint.setFilterBitmap(true); // 设置位图过滤
        mBitPaint.setDither(true); // 设置抖动
        //canvas.drawBitmap(bitmap, getX(), getY(), mBitPaint); // 在画布上绘制位图
        if (!isTouched) { // 如果没有被触摸
            canvas.drawBitmap(bitmap, getX(), getY(), mBitPaint); // 在画布上绘制位图
        }
        else
        {
            score += 1;
            x = random.nextInt(maxWidth); // 生成0到10之间的随机整数
            y = random.nextInt(maxHeight);
            isTouched = false;
        }
    }

    // Getter 和 Setter 方法

    public float getX() {
        return x; // 返回 x 坐标
    }

    public void setX(float x) {
        this.x = x; // 设置 x 坐标
    }

    public float getY() {
        return y; // 返回 y 坐标
    }

    public void setY(float y) {
        this.y = y; // 设置 y 坐标
    }

    public int getDrawableResourceId() {
        return drawableResourceId; // 返回绘制资源 ID
    }

    public void setDrawableResourceId(int drawableResourceId) {
        this.drawableResourceId = drawableResourceId; // 设置绘制资源 ID
    }

    public float getDirection() {
        return direction; // 返回方向
    }

    public void setDirection(float direction) {
        this.direction = direction; // 设置方向
    }
}
