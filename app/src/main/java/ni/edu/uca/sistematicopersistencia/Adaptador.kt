package ni.edu.uca.sistematicopersistencia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto

class Adaptador (
    val listaProd: MutableList<EntityProducto>,
    //val listener: AdaptadorListener
): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.rcproducto, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = listaProd[position]

        holder.etNombre.text = prod.nombre
        holder.etPrecio.text = prod.precio.toString()
        holder.etExistencia.text = prod.existencia.toString()



    }

    override fun getItemCount(): Int {
        return listaProd.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {

        var etNombre = itemView.findViewById<TextView>(R.id.etNombre)
        var etPrecio = itemView.findViewById<TextView>(R.id.etPrecio)
        var etExistencia = itemView.findViewById<TextView>(R.id.etExistencia)

        var constrain = itemView.findViewById<ConstraintLayout>(R.id.rcproducto)
    }



}