package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Button
{
    var text = "Comenzar"
    var width = 0f
    var height = 0f
    var view : View ?= null


    constructor(v : View, width : Float, height : Float)
    {
        this.view = v
        this.width = width
        this.height = height
    }

    fun drawButton(canvas : Canvas, p : Paint)
    {
        p.color = Color.GREEN
        canvas.drawRect(view?.width?.div(2f)?.minus(width) ?: 0f, view?.height?.div(2f)?.minus(height) ?: 0f, view?.width?.div(2f)?.plus(width) ?: 0f, view?.height?.div(2f)?.plus(height) ?: 0f, p)

        p.textSize = 100f
        p.color = Color.BLACK
        canvas.drawText(text, view?.width?.div(2f)?.minus(width * 0.8f) ?: 0f, view?.height?.div(2f)?.plus(height * 0.2f) ?: 0f, p)
    }

    fun isInArea(event : MotionEvent) : Boolean
    {
        if (event.x >= view?.width?.div(2f)?.minus(width)!! && event.x <= view?.width?.div(2f)!! + width)
            if (event.y >= view?.height?.div(2f)?.minus(height)!! && event.y <= view?.height?.div(2f)!! + height)
                return true
        return false
    }

    fun start(control : Control, main : MainActivity)
    {
       control.start(main)
    }
}