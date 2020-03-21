package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast


class Canvas(var main : MainActivity) : View(main)
{
    var button : Button ?= null
    var control : Control ?= null

    init {
        button = Button(this, 300f, 100f)
        control = Control(this, resources)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        //canvas.drawRGB(128, 64, 0)
        button?.drawButton(canvas, paint)
        control?.drawData(canvas, paint)
        control?.drawPlayers(canvas, paint)
        control!!.dice1!!.drawDice(canvas, paint, width / 2f - width * 0.3f, height.toFloat() - height * 0.4f)
        control!!.dice2!!.drawDice(canvas, paint, width / 2f, height.toFloat() - height * 0.4f)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN -> {
                if (button?.isInArea(event)!!){
                    button?.start(control!!, main)
                    button?.text = "Jugando..."
                }
            }
        }

        return true
    }
}