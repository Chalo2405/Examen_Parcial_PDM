package com.example.juegocolores.ui.result

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.juegocolores.R
import com.example.juegocolores.ui.game.GameFragment

class ResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        private const val ARG_SCORE = "score"

        fun newInstance(score: Int) = ResultFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SCORE, score) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = arguments?.getInt(ARG_SCORE) ?: 0

        val tvFinal = view.findViewById<TextView>(R.id.tvFinalScore)
        val tvHigh = view.findViewById<TextView>(R.id.tvHighScore)
        val btnReplay = view.findViewById<Button>(R.id.btnReplay)

        tvFinal.text = getString(R.string.final_score_fmt, score)

        // 🔹 Usamos el mismo SharedPreferences que GameFragment
        val prefs = requireContext().getSharedPreferences("game_prefs", 0)
        val best = prefs.getInt("best_score", 0)
        tvHigh.text = getString(R.string.high_score_fmt, best)

        btnReplay.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment())
                .commit()
        }
    }
}
