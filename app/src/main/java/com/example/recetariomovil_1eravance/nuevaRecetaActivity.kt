package com.example.recetariomovil_1eravance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.common.internal.Objects
import com.google.firebase.firestore.FirebaseFirestore

class nuevaRecetaActivity : AppCompatActivity() {



    lateinit var btnVolver: Button
    lateinit var btnGuardarReceta: Button
    lateinit var btnCancelarReceta: Button
    lateinit var nombreReceta: EditText
    lateinit var ingredientesReceta: EditText
    lateinit var tiempoReceta: EditText
    lateinit var dificultadReceta: EditText
    lateinit var tipoDieta: EditText
    lateinit var procedimiento: EditText

    lateinit var nombre: String
    lateinit var ingredientes: String
    lateinit var tiempo: String
    lateinit var dificultad: String
    lateinit var dieta: String
    lateinit var proced: String
    lateinit var map: HashMap<String,String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_receta)

        db = FirebaseFirestore.getInstance()

        btnVolver = findViewById(R.id.btn_volver)
        btnGuardarReceta = findViewById(R.id.btn_guardar_receta)
        btnCancelarReceta = findViewById(R.id.btn_cancelar_receta)
        nombreReceta = findViewById(R.id.nombreEditText)
        ingredientesReceta = findViewById(R.id.ingredientesEditText)
        tiempoReceta = findViewById(R.id.tiempoEditText)
        dificultadReceta = findViewById(R.id.dificultadEditText)
        tipoDieta = findViewById(R.id.tipoDietaEditText)
        procedimiento = findViewById(R.id.procedimientoEditText)


        btnGuardarReceta.setOnClickListener {
            nombre = nombreReceta.text.toString()
            ingredientes = ingredientesReceta.text.toString()
            tiempo = tiempoReceta.text.toString()
            dificultad = dificultadReceta.text.toString()
            dieta = tipoDieta.text.toString()
            proced = procedimiento.text.toString()

            if (nombre.isEmpty() && ingredientes.isEmpty() && tiempo.isEmpty() && 
                dificultad.isEmpty() && dieta.isEmpty() && proced.isEmpty()){
                Toast.makeText(applicationContext, "Llene todos los campos por favor.", Toast.LENGTH_SHORT).show()
            }else{
                guardarReceta(nombre,ingredientes,tiempo,dificultad,dieta,proced)
            }
        }


        btnVolver.setOnClickListener {
            finish()
        }

        btnCancelarReceta.setOnClickListener {
            nombreReceta.setText("")
            ingredientesReceta.setText("")
            tiempoReceta.setText("")
            dificultadReceta.setText("")
            tipoDieta.setText("")
            procedimiento.setText("")
        }

    }

    private fun guardarReceta(nombre: String, ingredientes: String, tiempo: String, dificultad: String, dieta: String, proced: String) {

        map.put("Nombre",nombre)
        map.put("Ingredientes",ingredientes)
        map.put("Tiempo",tiempo)
        map.put("Dificultad",dificultad)
        map.put("TipoDieta",dieta)
        map.put("Procedimiento",proced)


        db.collection("recetas").add(map).addOnSuccessListener {
            Toast.makeText(applicationContext, "Se guardo con exito la receta", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "No se logro guardar la receta", Toast.LENGTH_SHORT).show()
        }
    }
}