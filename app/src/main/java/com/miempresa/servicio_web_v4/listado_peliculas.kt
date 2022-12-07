package com.miempresa.servicio_web_v4

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.miempresa.serviciowebv4.AdaptadorElementos
import com.miempresa.serviciowebv4.Elementos
import kotlinx.android.synthetic.main.activity_listado_peliculas.*
import org.json.JSONException

class listado_peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_peliculas)
        cargarLista()
        btnSalir.setOnClickListener(){
            finish()
        }
        btnAgregar.setOnClickListener(){
            val llamaractividad = Intent(applicationContext, ver_pelicula::class.java)
            startActivity(llamaractividad)
        }
        btnBuscar.setOnClickListener(){
            buscarPelicula()
        }
        btnBuscarCategoria.setOnClickListener(){
            buscarPeliculaCategoria()
        }
    }
    fun cargarLista() {
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<Elementos>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = getString(R.string.urlAPI) + "/peliculas"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("id")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            val duracion =
                                response.getJSONObject(i).getString("duracion")
                            val imagen =
                                response.getJSONObject(i).getString("imagen")
                            val categoria =
                                response.getJSONObject(i).getString("categoria")
                            llenarLista.add(Elementos(id.toInt(),imagen,nombre, duracion.toInt(),categoria))
                        }
                        val adapter = AdaptadorElementos(llenarLista)
                        lista.adapter = adapter
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Verifique que esta conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
    fun buscarPelicula() {
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<Elementos>()
        AsyncTask.execute {
            val p = txtBuscar.text.toString()
            val queue = Volley.newRequestQueue(applicationContext)
            val url = getString(R.string.urlAPI) + "/peliculas?nombre_like=" + p
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("id")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            val duracion =
                                response.getJSONObject(i).getString("duracion")
                            val imagen =
                                response.getJSONObject(i).getString("imagen")
                            val categoria =
                                response.getJSONObject(i).getString("categoria")
                            llenarLista.add(
                                Elementos(
                                    id.toInt(),
                                    imagen,
                                    nombre,
                                    duracion.toInt(),
                                    categoria
                                )
                            )
                        }
                        val adapter = AdaptadorElementos(llenarLista)
                        lista.adapter = adapter
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Verifique que esta conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
    fun buscarPeliculaCategoria() {
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<Elementos>()
        AsyncTask.execute {
            val p = txtBuscarCategoria.text.toString()
            val queue = Volley.newRequestQueue(applicationContext)
            val url = getString(R.string.urlAPI) + "/peliculas?categoria_like=" + p
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("id")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            val duracion =
                                response.getJSONObject(i).getString("duracion")
                            val imagen =
                                response.getJSONObject(i).getString("imagen")
                            val categoria =
                                response.getJSONObject(i).getString("categoria")
                            llenarLista.add(
                                Elementos(
                                    id.toInt(),
                                    imagen,
                                    nombre,
                                    duracion.toInt(),
                                    categoria
                                )
                            )
                        }
                        val adapter = AdaptadorElementos(llenarLista)
                        lista.adapter = adapter
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Verifique que esta conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}