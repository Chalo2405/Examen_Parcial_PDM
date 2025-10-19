package com.example.juegocolores.ui.game

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.juegocolores.R
import com.example.juegocolores.ui.result.ResultFragment
import kotlin.random.Random

class GameFragment : Fragment(R.layout.fragment_game) {

    private var score = 0
    private var timer: CountDownTimer? = null
    //aqui esta la definicion de los colores a usar
    private val colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
    private var currentColor: Int = Color.RED

    private lateinit var viewColor: View
    private lateinit var txtScore: TextView
    private lateinit var txtTimer: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewColor = view.findViewById(R.id.viewColor)
        txtScore  = view.findViewById(R.id.txtScore)
        txtTimer  = view.findViewById(R.id.txtTimer)

        val listener = View.OnClickListener { v ->
            val chosen = when (v.id) {
                R.id.btnRed -> Color.RED
                R.id.btnGreen -> Color.GREEN
                R.id.btnBlue -> Color.BLUE
                R.id.btnYellow -> Color.YELLOW
                else -> Color.BLACK
            }
            handleAnswer(chosen, v)
        }


        //aqui tenemos lo que es interaccion con el usuario a traves de botones/feedback
        view.findViewById<Button>(R.id.btnRed).setOnClickListener(listener)
        view.findViewById<Button>(R.id.btnGreen).setOnClickListener(listener)
        view.findViewById<Button>(R.id.btnBlue).setOnClickListener(listener)
        view.findViewById<Button>(R.id.btnYellow).setOnClickListener(listener)

        score = 0
        txtScore.text = getString(R.string.score_fmt, score)
        nextColor()
        startTimer()
    }

    //aca tenemos el timer usando CountDownTimer
    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(30_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                txtTimer.text = getString(R.string.time_fmt, seconds)
            }

            override fun onFinish() {
                endGame()
            }
        }.start()
    }

    //aqui tenemos la logica de el juego , es decir si el usuario eligio el color correcto o no
    private fun handleAnswer(chosen: Int, clickedView: View) {
        if (chosen == currentColor) {
            score++
            txtScore.text = getString(R.string.score_fmt, score)

            //animacion cuando tenemos un acierto
            clickedView.animate()
                .scaleX(1.1f).scaleY(1.1f).setDuration(100)
                .withEndAction {
                    clickedView.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }.start()

            nextColor()
        } else {
            // animacion cuando tenemos un error
            viewColor.animate().alpha(0.3f).setDuration(80)
                .withEndAction { viewColor.animate().alpha(1f).setDuration(80).start() }
                .start()
        }
    }

    private fun nextColor() {
        currentColor = colors[Random.nextInt(colors.size)]
        viewColor.setBackgroundColor(currentColor)

        // Animación de aparición
        viewColor.alpha = 0f
        viewColor.animate().alpha(1f).setDuration(150).start()
    }

    //aqui usamos SharedPreferences y hacemos el uso de el resultfragment para enseñar el score con su highscore
    private fun endGame() {
        timer?.cancel()

        val prefs = requireContext().getSharedPreferences("game_prefs", 0)
        val best = prefs.getInt("best_score", 0)
        if (score > best) {
            prefs.edit().putInt("best_score", score).apply()
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, ResultFragment.newInstance(score))
            .commit()
    }


    override fun onDestroyView() {
        timer?.cancel()
        super.onDestroyView()
    }
}
