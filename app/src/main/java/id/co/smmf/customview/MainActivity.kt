package id.co.smmf.customview

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    var  value = 0.0f
//    lateinit var animator:ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun hello(view: View) {
       /* animator = ValueAnimator.ofFloat(0f, value)
        if (value<=1){
            value+=0.1f
        }else{
            value=0.1f
        }
        animator.duration = 1500
        animator.addUpdateListener {
            myview.setCurrentPercent(it.animatedValue as Float);
        }
        animator.start()*/
        myview.setCurrentPercent(0.8f)
        success.startAnimotor()

    }

    fun hello2(view: View) {
        startActivity(Intent(this,SecondActivity::class.java))
    }
}
