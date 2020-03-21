package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.widget.Toast

class Control(var v : View, res : Resources)
{
    var round = 1
    var state = "En espera"
    var turn : Player ?= null

    var player1 : Player ?= null
    var player2 : Player ?= null
    var player3 : Player ?= null
    var player4 : Player ?= null

    var p1 : Bitmap ?= null
    var p2 : Bitmap ?= null
    var p3 : Bitmap ?= null
    var p4 : Bitmap ?= null

    var thread1 : Thread ?= null
    var thread2 : Thread ?= null
    var thread3 : Thread ?= null
    var thread4 : Thread ?= null

    var dice1 : Dice ?= null
    var dice2 : Dice ?= null

    var d1 : Bitmap ?= null
    var d2 : Bitmap ?= null

    init {
        p1 = BitmapFactory.decodeResource(res, R.drawable.user_turn)
        p2 = BitmapFactory.decodeResource(res, R.drawable.user_waiting)
        p3 = BitmapFactory.decodeResource(res, R.drawable.user_waiting)
        p4 = BitmapFactory.decodeResource(res, R.drawable.user_waiting)

        d1 = BitmapFactory.decodeResource(res, R.drawable.d2)
        d2 = BitmapFactory.decodeResource(res, R.drawable.d1)

        player1 = Player("Player1", v, p1!!, res)
        player2 = Player("Player2", v, p2!!, res)
        player3 = Player("Player3", v, p3!!, res)
        player4 = Player("Player4", v, p4!!, res)

        dice1 = Dice(d1!!, res)
        dice2 = Dice(d2!!, res)

        turn = player1
    }

    fun drawPlayers(canvas : Canvas, p : Paint)
    {
        player1?.drawPlayer(canvas, p, v.width * 0.2f, 0f)
        player2?.drawPlayer(canvas, p, v.width.toFloat() - v.width * 0.3f, 0f)
        player3?.drawPlayer(canvas, p, v.width * 0.2f, v.height.toFloat() - v.height * 0.15f)
        player4?.drawPlayer(canvas, p, v.width.toFloat() - v.width * 0.3f, v.height.toFloat() - v.height * 0.15f)
    }

    fun drawData(canvas : Canvas, p : Paint)
    {
        p.textSize = 100f
        canvas.drawText("Ronda $round/4", v.width / 2f - v.width * 0.16f, v.height / 2f - v.height * 0.05f, p)
        p.textSize = 60f
        canvas.drawText("Turno: ${turn?.name} ($state)", v.width / 2f - v.width * 0.45f, v.height / 2f - v.height * 0.1f, p)
    }

    fun compare() : Player?
    {
        var array = ArrayList<Player>()
        array.add(player1!!)
        array.add(player2!!)
        array.add(player3!!)
        array.add(player4!!)

        var bigger : Player ?= null

        (0 until array.size - 1).forEach {
            if(array[it].score > bigger?.score ?: 0)
            {
                bigger = array[it]
            }
            else if(array[it].score == bigger?.score ?: 0)
            {
                if(it == array.size - 1)
                {
                    return null
                }
            }
        }

        return bigger
    }

    fun start(main : MainActivity)
    {
        if(thread1 == null)
        {
            thread1 = Thread(main, player1!!, this)
            thread2 = Thread(main, player2!!, this)
            thread3 = Thread(main, player3!!, this)
            thread4 = Thread(main, player4!!, this)

            thread1?.start()
            thread1?.nextThread = thread2
            thread2?.nextThread = thread3
            thread3?.nextThread = thread4
            thread4?.nextThread = thread1

            thread1?.pause()

            thread2?.start()
            thread3?.start()
            thread4?.start()
        }
        else
        {
            Toast.makeText(main, "Ya ha sido iniciado el hilo.", Toast.LENGTH_LONG).show()
        }
    }
}