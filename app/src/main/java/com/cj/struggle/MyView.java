package com.cj.struggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by admin on 2017/11/9.
 */

public class MyView extends View {

    private Bitmap mBitmap;
    private Camera mCamera;
    private Paint mPaint;


    private int degressRotate;//图片旋转角度
    private int degressY;//3d角度
    private int endDegressY;//结束时的3D角度

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lol);
        mPaint = new Paint();

        mCamera = new Camera();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        mCamera.setLocation(0, 0, newZ);


    }

    {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerY = getHeight() / 2;
        int centerX = getWidth() / 2;
        int bitmapHeight = mBitmap.getHeight();
        int mBitmapWidth = mBitmap.getWidth();

        int centerBitmapY = bitmapHeight / 2;
        int centerBitmapX = mBitmapWidth / 2;

        //右半边部分
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);//防止图片几何变换后变形
        canvas.rotate(-degressRotate);
        mCamera.rotateY(degressY);//几何3d
        mCamera.applyToCanvas(canvas);
        //此时的位置已经变化
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.rotate(degressRotate);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();
        canvas.drawBitmap(mBitmap, centerX - centerBitmapX, centerY - centerBitmapY, mPaint);
        canvas.restore();

        //左半边部分
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degressRotate);
        mCamera.rotateY(endDegressY);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        canvas.rotate(degressRotate);
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(mBitmap, centerX - centerBitmapX, centerY - centerBitmapY, mPaint);
        mCamera.restore();
        canvas.restore();

    }


    public void setDegressY(int degressY) {
        this.degressY = degressY;
        invalidate();
    }

    public void setDegressRotate(int degressRotate) {
        this.degressRotate = degressRotate;
        invalidate();
    }

    public void setEndDegressY(int endDegressY) {
        this.endDegressY = endDegressY;
        invalidate();
    }

    public int getDegressY() {
        return degressY;
    }

    public int getDegressRotate() {
        return degressRotate;
    }

    public int getEndDegressY() {
        return endDegressY;
    }

    public void reset() {
        degressRotate = 0;
        degressY = 0;
        endDegressY = 0;
    }
}
