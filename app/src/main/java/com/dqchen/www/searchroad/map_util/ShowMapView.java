package com.dqchen.www.searchroad.map_util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dqchen.www.searchroad.R;

/**
 * Created by Administrator on 2019/2/16.
 *
 * 地图绘制实例类
 *
 */

public class ShowMapView extends View {
    public ShowMapView(Context context) {
        super(context);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        //地图每格大小为80*80,屏幕x,y和数组x,y是相反的

        int row = (int) (y/80);
        int col = (int) (x/80);

        if (MapUtils.map[row][col]==0){
            MapUtils.touchFlag++;
            if (MapUtils.touchFlag==1){
                MapUtils.startRow = row;
                MapUtils.startCol = col;
                MapUtils.map[row][col] = 2;
            }else if (MapUtils.touchFlag==2){
                MapUtils.endRow = row;
                MapUtils.endCol = col;
                MapUtils.map[row][col] = 2;
            }
        }

        this.invalidate();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);

        for (int i = 0; i < MapUtils.map.length; i++) {
            for (int j = 0; j < MapUtils.map[i].length; j++) {
                if (MapUtils.map[i][j]==0){
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.route);
                    canvas.drawBitmap(bitmap,j*80,i*80,paint);
                }else if (MapUtils.map[i][j]==1){
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.wall);
                    canvas.drawBitmap(bitmap,j*80,i*80,paint);
                }else if (MapUtils.map[i][j]==2){
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.path);
                    canvas.drawBitmap(bitmap,j*80,i*80,paint);
                }
            }
        }

        if (MapUtils.path!=null){
            canvas.drawPath(MapUtils.path,paint);
        }
    }
}
