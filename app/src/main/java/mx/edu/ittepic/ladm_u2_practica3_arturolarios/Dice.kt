package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Dice
{
    var icon : Bitmap?= null
    var res : Resources ?= null

    constructor(icon : Bitmap, res : Resources)
    {
        this.icon = icon
        this.res = res
    }

    fun drawDice(canvas : Canvas, p : Paint, x : Float, y : Float)
    {
        canvas.drawBitmap(icon!!, x, y, p)
    }

    fun roll() : Int
    {
        return (1..6).random()
    }

    fun changeDice(number : Int)
    {
        when(number)
        {
            1 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d1)
            }
            2 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d2)
            }
            3 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d3)
            }
            4 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d4)
            }
            5 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d5)
            }
            6 -> {
                icon = BitmapFactory.decodeResource(res, R.drawable.d6)
            }
        }
    }
}