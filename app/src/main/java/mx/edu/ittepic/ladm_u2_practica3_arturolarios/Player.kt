package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class Player(name : String, v : View, avatar : Bitmap, var res : Resources)
{
    var name = ""
    var score = 0
    var turn = false
    var v : View ?= null
    var avatar : Bitmap ?= null

    init {
        this.v = v
        this.name = name
        this.avatar = avatar
    }

    fun drawPlayer(canvas : Canvas, p : Paint, x : Float, y : Float)
    {

        canvas.drawBitmap(avatar!!, x, y, p)
        p.textSize = 60f
        canvas.drawText(name,  x - v!!.width * 0.05f, y + 200f, p)
        canvas.drawText(getTurn(),  x - v!!.width * 0.05f, y + 250f, p)
        canvas.drawText("Puntos: $score",  x - v!!.width * 0.05f, y + 300f, p)
    }

    fun changeAvatar()
    {
        if(turn)
            avatar = BitmapFactory.decodeResource(res, R.drawable.user_turn)
        else
            avatar = BitmapFactory.decodeResource(res, R.drawable.user_waiting)
    }

    private fun getTurn() = if (turn) "En turno" else "En pausa"
}