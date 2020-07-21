package id.co.smmf.customview.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/20 14:23
 */
public class MyLinerlayout extends LinearLayout {
    public MyLinerlayout(Context context) {
        super(context);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
      /*  switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("viewgroup---------onInterceptTouchEvent");
                break;
        }*/
        System.out.println("viewgroup---------onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /*switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("viewgroup---------dispatchTouchEvent");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(getContext(),"clickLayout", Toast.LENGTH_SHORT).show();
                return true;
        }*/
        System.out.println("viewgroup---------dispatchTouchEvent");
//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("viewgroup---------onTouchEvent");
     /*   switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("viewgroup---------onTouchEvent");
                break;
//                return  true;
        }*/
        return super.onTouchEvent(event);
    }
}
