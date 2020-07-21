package id.co.smmf.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/16 18:30
 */
public class CharView extends View {
    private Paint mPaint;
    private int height,width;
    public CharView(Context context) {
        super(context);
    }

    public CharView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CharView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(12);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,height,width,height,mPaint);
        canvas.drawLine(0,height,0,0,mPaint);
    }
}
