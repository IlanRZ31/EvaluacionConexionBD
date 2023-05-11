package ni.edu.uca.sistematicopersistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var listaProd: MutableList<EntityProducto> = mutableListOf()
    lateinit var room : BaseDatos
    lateinit var  producto: EntityProducto
    lateinit var adatador: Adaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcproducto.layoutManager = LinearLayoutManager(this)
        room = Room.databaseBuilder(this, BaseDatos::class.java, "dbPruebas").build()
        obtenerProducto(room)
        with(binding){
            binding.floatingActionButton.setOnClickListener {
                if (etNombre.text.toString() == "" || etPrecio.text.toString() == "" || etExistencia.text.toString() == "" ){
                    Toast.makeText(this@MainActivity, "Todos los campos son requeridos", Toast.LENGTH_LONG).show()
                }else{

                    producto = EntityProducto(
                        etNombre.text.toString().trim(),
                        etPrecio.text.toString().toDouble(),
                        etExistencia.text.toString().toInt(),
                    )

                    agregarProducto(room, producto)
                    Toast.makeText(this@MainActivity, "Guardado Exitoso", Toast.LENGTH_LONG).show()

                }
            }

        }
    }

    private fun agregarProducto(room: BaseDatos, producto: EntityProducto) {
        lifecycleScope.launch {
            room.productoDao().agregarProducto(producto)
            obtenerProducto(room)
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {

    }

    private fun obtenerProducto(room: BaseDatos) {
        lifecycleScope.launch {
            listaProd = room.productoDao().obtenerProducto()
            adatador = Adaptador(listaProd)
            binding.rcproducto.adapter = adatador
        }
    }
}