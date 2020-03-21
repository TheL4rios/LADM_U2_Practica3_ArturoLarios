package mx.edu.ittepic.ladm_u2_practica3_arturolarios

import android.app.AlertDialog
import java.lang.Thread

class Thread(var main : MainActivity, var player : Player, var control : Control) : Thread()
{
    var nextThread : mx.edu.ittepic.ladm_u2_practica3_arturolarios.Thread ?= null
    var finish = false
    var pause = true
    var dado1 = 0
    var dado2 = 0

    override fun run() {
        super.run()

        while(!finish)
        {
            //control.v.invalidate()
            if(!pause)
            {


                main.runOnUiThread {
                    control.turn = player
                    control.turn!!.turn = true
                    control.turn!!.changeAvatar()
                    control.state = "Tirando..."
                }

                for(i in 1..12)
                {
                    sleep(100)
                    main.runOnUiThread {
                        control.dice1?.changeDice(control.dice1?.roll()!!)
                        control.dice2?.changeDice(control.dice1?.roll()!!)
                    }
                }

                dado1 = control.dice1?.roll() ?: 0
                dado2 = control.dice2?.roll() ?: 0

                main.runOnUiThread {
                    control.dice1?.changeDice(dado1)
                    control.dice2?.changeDice(dado2)
                }

                main.runOnUiThread {
                    control.turn!!.score = player.score + dado1 + dado2
                    control.state = "Dado1: $dado1, Dado2: $dado2"
                    pause()
                }

                sleep(1200)

                main.runOnUiThread {
                    if(nextThread?.player!!.name == "Player1")
                        control.round++

                    control.turn!!.turn = false
                    control.turn!!.changeAvatar()
                }

                nextThread!!.pause()
            }

            if(control.round == 4)
                finish()
        }

        if(!nextThread!!.isAlive)
        {
            showWinner(control.compare())
        }
    }

    fun pause()
    {
        pause = !pause
    }

    fun finish()
    {
        finish = true
    }

    fun showWinner(player : Player?)
    {
        main.runOnUiThread {
            var alert = AlertDialog.Builder(main)

            if(player != null)
            {
                alert.setTitle("Ganador!!!!")
                    .setMessage("${player?.name} con ${player?.score} puntos.")
                    .setPositiveButton("OK"){i,d->}.show()
            }
            else
            {
                alert.setTitle("Empate!!!!")
                    .setMessage("Hubo un empate entre los jugadores")
                    .setPositiveButton("OK"){i,d->}.show()
            }
        }
    }
}