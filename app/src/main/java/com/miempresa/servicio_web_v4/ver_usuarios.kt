package com.miempresa.servicio_web_v4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ver_usuarios.*
import kotlinx.android.synthetic.main.activity_ver_usuarios.txtClave

class ver_usuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_usuarios)
        val estados = arrayOf("A","X")
        cmbEstado.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, estados
            )
        )
        btnRegistrar.setOnClickListener(){
            val queue = Volley.newRequestQueue(this)
            val url = getString(R.string.urlAPI) + "/usuarios"
            val usuario = txtNombre.text.toString()
            val clave = txtClave.text.toString()
            val estado = cmbEstado.selectedItem.toString()

            val stringReq : StringRequest =
                object : StringRequest(
                    Method.POST, url,
                    Response.Listener { response ->
                        var strResp = response.toString()
                        Log.d("API",strResp)
                        Toast.makeText(
                            applicationContext,
                            "Usuario registrado de forma exitosa ",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    Response.ErrorListener{
                        Toast.makeText(
                            applicationContext,
                            "Compruebe que tiene acceso a internet: ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }){
                    override fun getParams(): MutableMap<kotlin.String, kotlin.String>? {
                        val params: HashMap<kotlin.String, kotlin.String> = hashMapOf()
                        params.put("usuario",usuario)
                        params.put("clave",clave)
                        params.put("estado",estado)
                        return params
                    }
                }
            queue.add(stringReq)

        }
        }
    }