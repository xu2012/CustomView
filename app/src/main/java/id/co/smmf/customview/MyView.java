package id.co.smmf.customview;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/16 11:51
 */
public class MyView extends View {
    private Paint backCirclePaint,//画背景圆
            outerCirclePaint,//画进度圆弧
            endCirclePaint,//画终点实心大圆
            endCirclePaint2,//画终点实心小圆
            titlePaint,//画第一行文字
            numPaint,//画第二行文字
            unitPaint;//画第三行文字
    private int width, height;
    private float edgeDistance=10;//背景圆与view边界的距离
    private float currentPercent = 0.0f;
    private float endCircleWidth=30;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private ValueAnimator animator;
    private void init() {
        backCirclePaint = new Paint();
        backCirclePaint.setAntiAlias(true);
        backCirclePaint.setStrokeWidth(12);
        backCirclePaint.setColor(Color.parseColor("#e6e9ed"));
        backCirclePaint.setStyle(Paint.Style.STROKE);

        outerCirclePaint = new Paint();
        outerCirclePaint.setAntiAlias(true);
        outerCirclePaint.setStrokeWidth(20);
        outerCirclePaint.setColor(Color.parseColor("#4fc1e9"));
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        endCirclePaint = new Paint();
        endCirclePaint.setAntiAlias(true);
        endCirclePaint.setColor(Color.parseColor("#4fc1e9"));
        endCirclePaint.setStyle(Paint.Style.FILL);


        endCirclePaint2 = new Paint();
        endCirclePaint2.setAntiAlias(true);
        endCirclePaint2.setColor(Color.GREEN);
        endCirclePaint2.setStyle(Paint.Style.FILL);

        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        titlePaint.setColor(Color.YELLOW);
        titlePaint.setTextSize(30);

        numPaint = new Paint();
        numPaint.setAntiAlias(true);
        numPaint.setColor(Color.BLACK);
        numPaint.setTextSize(30);

        unitPaint = new Paint();
        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.GRAY);
        unitPaint.setTextSize(16);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width  =MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        int a = Math.min(width,height);
        setMeasuredDimension(a,a);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = width/2;
        int centerY = height/2;
        float radius = (width/2)-edgeDistance;
        canvas.drawCircle(centerX,centerY,radius,backCirclePaint);
        //根据进度话扫过一定角度的圆弧
        RectF rectF = new RectF(edgeDistance, edgeDistance, width - edgeDistance, height - edgeDistance);
        canvas.drawArc(rectF, -90, 360 * currentPercent, false, outerCirclePaint);


        Rect textRect = new Rect();
        String num = String.valueOf(currentPercent*100)+"%";
        numPaint.getTextBounds(num, 0, num.length(), textRect);
        canvas.drawText(num, width / 2 - textRect.width() / 2, height / 2 + textRect.height() / 2, numPaint);

        //我这里规定进度在0~100%的时候才会画终点小圆，可以自由改动
        if (currentPercent < 1 && currentPercent > 0) {
            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), endCircleWidth / 2, endCirclePaint);


            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), endCircleWidth / 4, endCirclePaint2);

        }
    }

    public void setCurrentPercent(float currentPercent) {

        animator = ValueAnimator.ofFloat(0.0f,currentPercent);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                setCurrentPercent(valueAnimator.getAnimatedFraction());
                MyView.this.currentPercent = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

}
