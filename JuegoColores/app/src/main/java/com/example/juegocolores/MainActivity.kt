//en este proyecto realice el juego de colores basico en donde hay algunas pequeñas animaciones extras
//Autor: Luis Gonzalo Basurco Monroy
//Fecha Inicio: 18 de Octubre
//Fecha Modificacion: 19 de Octubre

package com.example.juegocolores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegocolores.ui.welcome.WelcomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment())
                .commit()
        }
    }
}
