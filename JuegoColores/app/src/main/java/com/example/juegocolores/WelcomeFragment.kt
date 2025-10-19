package com.example.juegocolores.ui.welcome

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.juegocolores.R
import com.example.juegocolores.ui.game.GameFragment

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //aqui realizamos la navegacion entre fragments
        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment())
                .addToBackStack(null)
                .commit()
        }

        if (savedInstanceState == null) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.rules_title))
                .setMessage(getString(R.string.rules_body))
                .setPositiveButton(getString(R.string.ok), null)
                .show()
        }
    }
}
