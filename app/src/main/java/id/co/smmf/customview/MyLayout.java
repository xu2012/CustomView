package id.co.smmf.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/16 10:01
 */
public class MyLayout extends ViewGroup {
    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("-------"+left+"----"+top+"---"+right+"---"+bottom);
        int childSize = getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = getChildAt(i);
            child.layout(right/2-child.getMeasuredWidth()/2,bottom/2-child.getMeasuredHeight()/2,right/2+child.getMeasuredWidth()/2,bottom/2+child.getMeasuredHeight()/2);
        }
    }
    /*
    * 我的理解是onMeasure就是根据模式和子view来计算自己的大小
    * 如果是match/xxdp那么就直接取size，否则需要计算子view来确定自身的大小
    *
    * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        System.out.println("widthSize==="+widthSize);
        System.out.println("heightSize==="+heightSize);
        int childCount = getChildCount();
        int layoutHieght = 0;
        int layoutWidth = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            layoutWidth = widthSize;
            System.out.println("EXACTLY");
        } else if (widthMode==MeasureSpec.AT_MOST){
            System.out.println("AT_MOST");
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                layoutWidth = Math.max(layoutWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
            }
        }else {
            System.out.println("else");
        }
        if (heightMode == MeasureSpec.EXACTLY) {//match_parent
            layoutHieght = heightSize;
        } else {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                layoutHieght += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            }
        }
        int a = Math.min(layoutWidth + getPaddingLeft() + getPaddingRight(), layoutHieght + getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(a,a);
//        setMeasuredDimension(layoutWidth + getPaddingLeft() + getPaddingRight(), layoutHieght + getPaddingTop() + getPaddingBottom());
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
