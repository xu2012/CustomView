package id.co.smmf.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/16 16:29
 */
public class SuccessView extends View {
    private int mRealSize;
    private Paint mPaint;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;

    public SuccessView(Context context) {
        super(context);
        init(context, null);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initPaint();
        initCircleAnimator();
        post(new Runnable() {
            @Override
            public void run() {
                initPath();
            }
        });
    }

    private ValueAnimator mCircleAnimator;
    private boolean hasCircle;

    private void initCircleAnimator() {
        mCircleAnimator = ValueAnimator.ofFloat(0, 1);
        mCircleAnimator.setDuration(1500);
        mCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mValue = (float) valueAnimator.getAnimatedValue();
                invalidate();//重绘界面
            }
        });
        // 插值器
        mCircleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hasCircle = true;
                initMarkAnimator();
                initRightMarkPath();
                mMarkAnimator.start();
            }
        });
    }
    public void startAnimotor(){
        hasCircle = false;
        mPathMeasure.setPath(mCirclePath,false);
        mPathLength = mPathMeasure.getLength();
        mCircleAnimator.start();
    }
    private void initRightMarkPath() {
        Path path = new Path();
        // 对号起点
        float startX = (float) (0.3 * mRealSize);
        float startY = (float) (0.5 * mRealSize);
        path.moveTo(startX, startY);

        // 对号拐角点
        float cornerX = (float) (0.43 * mRealSize);
        float cornerY = (float) (0.66 * mRealSize);
        path.lineTo(cornerX, cornerY);

        // 对号终点
        float endX = (float) (0.75 * mRealSize);
        float endY = (float) (0.4 * mRealSize);
        path.lineTo(endX, endY);

        mPathMeasure.setPath(path,false);
        mPathLength = mPathMeasure.getLength();
    }

    private void initMarkAnimator() {
        mMarkAnimator = ValueAnimator.ofFloat(0,1);
        mMarkAnimator.setDuration(1500);
        // 插值器
        mMarkAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mMarkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }
    private ValueAnimator mMarkAnimator;
    private float mPathLength;
    private Path mDstPath;

    private void initPath() {
        mCirclePath = new Path();
        //CCW逆时针 cw顺时针
        mCirclePath.addCircle(mRealSize / 2, mRealSize / 2, mRealSize / 2 - 10, Path.Direction.CCW);
        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mCirclePath, false);
        mPathLength = mPathMeasure.getLength();
        mDstPath = new Path();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(12);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    private float mValue = 0.5f;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDstPath == null) {
            return;
        }
        if (hasCircle) {
            canvas.drawPath(mCirclePath, mPaint);
        }
        mDstPath.reset();
        mDstPath.lineTo(0, 0);
        //截取片段
        float stop = mPathLength * mValue;
        mPathMeasure.getSegment(0, stop, mDstPath, true);

        canvas.drawPath(mDstPath, mPaint);
    }


    public SuccessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRealSize = Math.min(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getSize(widthMeasureSpec));
        setMeasuredDimension(mRealSize, mRealSize);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCircleAnimator!=null&&mCircleAnimator.isRunning()){
            mCircleAnimator.cancel();
        }
        if (mMarkAnimator!=null&&mMarkAnimator.isRunning()){
            mMarkAnimator.cancel();
        }
    }
}
