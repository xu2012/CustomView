package id.co.smmf.customview.event;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Description:
 *
 * @version 2.2
 * @author: xuyunlong
 * Date: 2020/7/20 14:24
 */
public class MyButton extends View {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
      /*  switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("view-----------dispatchTouchEvent");
                break;
        }*/
        System.out.println("view-----------dispatchTouchEvent");
//        return super.dispatchTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("view-----------onTouchEvent");

                break;
        }*/
        System.out.println("view-----------onTouchEvent");
        return super.onTouchEvent(event);
//        getParent().requestDisallowInterceptTouchEvent(false);
    }

}
