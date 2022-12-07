package com.miempresa.serviciowebv4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.miempresa.servicio_web_v4.R
import com.miempresa.servicio_web_v4.ver_pelicula

class AdaptadorElementos(val ListaElementos:ArrayList<Elementos>): RecyclerView.Adapter<AdaptadorElementos.ViewHolder>() {

    override fun getItemCount(): Int {
        return ListaElementos.size;
    }
    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView) {
        val fImagen = itemView.findViewById<ImageView>(R.id.elemento_imagen);
        val fNombre = itemView.findViewById<TextView>(R.id.elemento_nombre)
        val fDuracion = itemView.findViewById<TextView>(R.id.elemento_duracion)

        //set the onclick listener for the singlt list item
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.fImagen?.load(ListaElementos[position].imagen)
        holder?.fNombre?.text=ListaElementos[position].nombre
        holder?.fDuracion?.text=ListaElementos[position].duracion.toString() + "min"
        var id = ListaElementos[position].id
        var nombre = ListaElementos[position].nombre
        var categoria = ListaElementos[position].categoria
        var duracion = ListaElementos[position].duracion
        var imagen = ListaElementos[position].imagen

        holder.itemView.setOnClickListener(){
            val llamaractividad = Intent(holder.itemView.context, ver_pelicula::class.java)
            llamaractividad.putExtra("id",id.toString())
            llamaractividad.putExtra("nombre",nombre.toString())
            llamaractividad.putExtra("categoria",categoria.toString())
            llamaractividad.putExtra("duracion",duracion.toString())
            llamaractividad.putExtra("imagen",imagen.toString())
            holder.itemView.context.startActivity(llamaractividad)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementoslista, parent, false);
        return ViewHolder(v);
    }
}
