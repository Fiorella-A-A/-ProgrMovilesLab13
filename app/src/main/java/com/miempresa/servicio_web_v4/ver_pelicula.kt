package com.miempresa.servicio_web_v4

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ver_pelicula.*

class ver_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pelicula)
        val bundle  :Bundle ?=intent.extras
        if (bundle!=null){
            txtId.setText(bundle.getString("id").toString())
            txtNombre.setText(bundle.getString("nombre").toString())
            when(bundle.getString("categoria").toString()){
                "Drama" -> cmbCategoria.setSelection(0,true)
                "Comedia" -> cmbCategoria.setSelection(1,true)
                "Anime" -> cmbCategoria.setSelection(2,true)
            }
            txtDuracion.setText(bundle.getString("duracion").toString())
            txtImagen.setText(bundle.getString("imagen").toString())

            btnGuardar.setEnabled(false)
            btnActualizar.setEnabled(true)
            btnEliminar.setEnabled(true)
        }else{
            btnGuardar.setEnabled(true)
            btnActualizar.setEnabled(false)
            btnEliminar.setEnabled(false)
        }

        val categorias = arrayOf("Drama","Comedia","Anime")
        cmbCategoria.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,categorias
            )
        )

        btnGuardar.setOnClickListener(){
            agregarPelicula()
        }
        btnEliminar.setOnClickListener(){
            eliminarPelicula()
        }
        btnActualizar.setOnClickListener(){
            editarPelicula()
        }
    }
    fun agregarPelicula() {
        AsyncTask.execute {

            val id = txtId.text.toString()
            val nombre = txtNombre.text.toString()
            val categoria = cmbCategoria.selectedItem.toString()
            val duracion = txtDuracion.text.toString()
            val imagen = txtImagen.text.toString()

            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI) + "/peliculas"
            val postRequest: StringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response -> // response
                    Toast.makeText(
                        applicationContext,
                        "Registro agregado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                },
                Response.ErrorListener { response ->// error
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error al guardar los datos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> =
                        HashMap()
                    params["id"] = id
                    params["nombre"] = nombre
                    params["categoria"] = categoria
                    params["duracion"] = duracion
                    params["imagen"] = imagen
                    return params
                }
            }
            queue.add(postRequest)
        }
    }
    fun eliminarPelicula() {
        AsyncTask.execute {
            val id = txtId.text.toString()

            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI) + "/peliculas/" + id
            val postRequest: StringRequest = object : StringRequest(
                Request.Method.DELETE, url,
                Response.Listener { response -> // response
                    Toast.makeText(
                        applicationContext,
                        "Registro eliminado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                },
                Response.ErrorListener { response ->// error
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error al eliminar el registro",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ){}
            queue.add(postRequest)
        }

    }
    fun editarPelicula() {
        AsyncTask.execute {

            val id = txtId.text.toString()
            val nombre = txtNombre.text.toString()
            val categoria = cmbCategoria.selectedItem.toString()
            val duracion = txtDuracion.text.toString()
            val imagen = txtImagen.text.toString()

            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI) + "/peliculas/"+id
            val postRequest: StringRequest = object : StringRequest(
                Request.Method.PUT, url,
                Response.Listener { response -> // response
                    Toast.makeText(
                        applicationContext,
                        "Registro actualizado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                },
                Response.ErrorListener { response ->// error
                    Toast.makeText(
                        applicationContext,
                        "Se produjo un error al atualizar los datos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> =
                        HashMap()
                    params["nombre"] = nombre
                    params["categoria"] = categoria
                    params["duracion"] = duracion
                    params["imagen"] = imagen
                    return params
                }
            }
            queue.add(postRequest)
        }
    }
}