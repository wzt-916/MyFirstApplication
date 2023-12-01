package com.jnu.student.View; // 声明包名

import android.content.Context; // 导入 Android 内容包
import android.graphics.Canvas; // 导入用于绘制的 Canvas 类
import android.graphics.Color; // 导入颜色类
import android.util.AttributeSet; // 导入属性集类
import android.view.MotionEvent;
import android.view.SurfaceHolder; // 导入 SurfaceHolder 类
import android.view.SurfaceView; // 导入 SurfaceView 类

import androidx.annotation.NonNull; // 导入注解

import com.jnu.student.data.Spriter; // 导入 Spriter 类（假设此类已存在）

import java.util.ArrayList; // 导入 ArrayList 类

public class GameView extends SurfaceView implements SurfaceHolder.Callback { // 创建 GameView 类并继承自 SurfaceView 类，并实现 SurfaceHolder.Callback 接口

    public GameView(Context context) { // GameView 类的构造函数，接收 Context 类型的参数
        super(context); // 调用父类构造函数
        initView(); // 初始化视图
    }

    public GameView(Context context, AttributeSet attrs) { // GameView 类的构造函数，接收 Context 和 AttributeSet 类型的参数
        super(context, attrs); // 调用父类构造函数
        initView(); // 初始化视图
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) { // GameView 类的构造函数，接收 Context、AttributeSet 和 defStyleAttr 类型的参数
        super(context, attrs, defStyleAttr); // 调用父类构造函数
        initView(); // 初始化视图
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { // GameView 类的构造函数，接收 Context、AttributeSet、defStyleAttr 和 defStyleRes 类型的参数
        super(context, attrs, defStyleAttr, defStyleRes); // 调用父类构造函数
        initView(); // 初始化视图
    }

    private void initView() { // 初始化视图方法
        surfaceHolder = getHolder(); // 获取 SurfaceHolder 对象
        surfaceHolder.addCallback(this); // 添加回调接口
    }

    private SurfaceHolder surfaceHolder; // 声明 SurfaceHolder 对象
    private DrawThread drawThread = null; // 声明 DrawThread 线程对象

    private ArrayList<Spriter> spriterArrayList = new ArrayList<>(); // 创建 Spriter 对象的 ArrayList 集合

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) { // Surface 创建时调用的方法
        // 创建 Spriter 对象并添加到绘制列表中
        for (int i = 0; i < 5; ++i) { // 循环创建 5 个 Spriter 对象
            Spriter spriter = new Spriter(this.getContext()); // 创建 Spriter 对象
            spriter.setX(i * 50); // 设置 X 坐标
            spriter.setY(i * 50); // 设置 Y 坐标
            spriter.setDirection((float) (Math.random() * 2 * Math.PI)); // 设置方向
            spriterArrayList.add(spriter); // 将 Spriter 对象添加到列表中
        }

        // 初始化并启动绘制线程
        drawThread = new DrawThread(); // 创建 DrawThread 线程对象
        drawThread.start(); // 启动线程
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // 处理 Surface 变化（在此示例中未实现）
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) { // Surface 销毁时调用的方法
        // 当 Surface 被销毁时停止绘制线程
        drawThread.stopThread(); // 停止线程
    }

    class DrawThread extends Thread { // DrawThread 内部类继承自 Thread 类

        private boolean isDrawing = true; // 声明标志位控制绘制

        public void stopThread() { // 停止线程方法
            isDrawing = false; // 设置标志位为 false

            try {
                join(); // 等待线程完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() { // 线程运行方法
            while (isDrawing) { // 循环绘制
                Canvas canvas = null; // 声明 Canvas 对象

                try {
                    canvas = surfaceHolder.lockCanvas(); // 获取用于绘制的 Canvas 对象
                    canvas.drawColor(Color.WHITE); // 用白色填充画布

                    // 移动 Spriter 对象在画布中
                    for (Spriter spriter : spriterArrayList) {
                        spriter.move(canvas.getHeight(), canvas.getWidth()); // 移动 Spriter 对象
                    }

                    // 在画布上绘制 Spriter 对象
                    for (Spriter spriter : spriterArrayList) {
                        spriter.draw(canvas); // 绘制 Spriter 对象
                    }
                } catch (Exception e) {
                    // 异常处理
                } finally {
                    if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas); // 解锁并提交 Canvas 对象
                }

                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 绘制
            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        // 处理触摸事件
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 检查是否点击了图像
                for (Spriter spriter : spriterArrayList) {
                    if (spriter.isTouched(touchX, touchY)) {
                        spriter.setClicked(true); // 将图像标记为已点击
                    }
                }
                break;
        }
        return true;
    }
}
