package id.co.smmf.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import id.co.smmf.customview.RemoteControlMenu.MenuListener
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        button.setOnClickListener(this)
//        layout.setOnClickListener(this)
        meanu.setListener(object:MenuListener{
            override fun onDownCliched() {
            }

            override fun onLeftCliched() {
            }

            override fun onRightCliched() {
            }

            override fun onUpCliched() {
            }

            override fun onCenterCliched() {
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("activity-----------onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("activity-----------dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.layout -> {
                Toast.makeText(this,"clickLayout",Toast.LENGTH_SHORT).show()

            }
            R.id.button->{
                Toast.makeText(this,"clickView",Toast.LENGTH_SHORT).show()

            }
            else -> {
            }
        }
    }
}
