//CREAMOS EL FRAGMENT DE LA PANTALLA DE INICIO DEL JUEGO , EL CUAL PRESENTARA LAS REGLAS y habra el boton de jugar
// Autor: Luis Gonzalo Basurco Monroy
//Fecha Inicio 18 de Octubre del 2025
//Fecha Modificiacion 18 de Octubre del 2025

package com.example.juegocolores.ui.welcome
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.juegocolores.R

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            // por ahora solo mostramos un mensaje
            AlertDialog.Builder(requireContext())
                .setTitle("Juego no iniciado")
                .setMessage("Aquí irá el fragmento del juego en el siguiente paso.")
                .setPositiveButton("OK", null)
                .show()
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
