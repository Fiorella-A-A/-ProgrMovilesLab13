package com.miempresa.servicio_web_v4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val policy =
             StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        btnLogear.setOnClickListener(){
            val usuario = txtUsuario.text.toString()
            val clave = txtClave.text.toString()
            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI) + "/usuarios?"
            url = url + "usuario=" + usuario + "&clave=" + clave

            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        val valor = response.getJSONObject(0)
                        val llamaractividad = Intent(applicationContext, listado_peliculas::class.java)
                        startActivity(llamaractividad)
                        finish()
                    } catch (e: JSONException){
                        Toast.makeText(
                            applicationContext,
                            "Error en las credenciales proporcionadas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Compruebe que tiene acceso a internet: ",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            queue.add(stringRequest)

        }
        btnRegistrar.setOnClickListener() {
            val llamaractividad = Intent(applicationContext, ver_usuarios::class.java)
            startActivity(llamaractividad)
            finish()
        }
        btnSalir.setOnClickListener(){
            finish()
        }
    }
}