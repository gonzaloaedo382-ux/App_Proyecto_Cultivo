package com.example.cultivos_orquideas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class resultado_planta extends AppCompatActivity {
    private TextView txtNombre, txtEspecie, txtLuz, txtRiego, txtEstado;
    private ImageView imgPlanta;
    private Button btnVolver;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_planta);
        txtNombre = findViewById(R.id.txtResultadoNombre);
        txtEspecie = findViewById(R.id.txtResultadoEspecie);
        txtLuz = findViewById(R.id.txtResultadoLuz);
        txtRiego = findViewById(R.id.txtResultadoRiego);
        txtEstado = findViewById(R.id.txtResultadoEstado);
        imgPlanta = findViewById(R.id.imgResultadoPlanta);
        // 2 Vincular el botón con el ID que pusiste en el XML
        btnVolver = findViewById(R.id.btnVolveralmenu);

        // 3 Configurar la acción al hacer clic para volver al MainActivity
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(resultado_planta.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
        databaseHelper = new DatabaseHelper(this);
        String nombreBuscado = getIntent().getStringExtra("NOMBRE_PLANTA");
        Planta plantaEncontrada = LibreriaPlantas.obtenerPlantaPorNombre(nombreBuscado);

        if (plantaEncontrada != null) {
            mostrarDatosPlanta(
                    plantaEncontrada.getNombre(),
                    plantaEncontrada.getEspecie(),
                    plantaEncontrada.getLuz(),
                    plantaEncontrada.getRiego(),
                    plantaEncontrada.getEstado(),
                    plantaEncontrada.getImagenResId()
            );
            guardarEnBD(plantaEncontrada.getNombre(), plantaEncontrada.getEspecie(),
                    plantaEncontrada.getLuz(), plantaEncontrada.getRiego(), plantaEncontrada.getEstado());
        } else {
            String nomDef = "Orquídea Phalaenopsis";
            String espDef = "Phalaenopsis amabilis";
            String luzDef = "Luz indirecta brillante";
            String rigDef = "Cada 7-10 días";
            String estDef = "Saludable";
            mostrarDatosPlanta(nomDef, espDef, luzDef, rigDef, estDef, R.drawable.phalaenopsis);
            guardarEnBD(nomDef, espDef, luzDef, rigDef, estDef);
        }
    }
    /*aqui se muestra los datos de la planta encontrada en la libreriade plantas si hubo coincidencias */
    public void mostrarDatosPlanta(String nombre, String especie, String luz, String riego, String estado, int imagenResId) {
        txtNombre.setText(nombre);
        txtEspecie.setText("Especie: " + especie);
        txtLuz.setText("Luz: " + luz);
        txtRiego.setText("Riego: " + riego);
        txtEstado.setText("Estado: " + estado);
        imgPlanta.setImageResource(imagenResId);
    }
    /* una ves mostrado los resultados del resultado encontrado en la libreria se guarda en los reportes*/
    private void guardarEnBD(String nombre, String especie, String luz, String riego, String estado) {
        boolean exito = databaseHelper.insertarRegistroPlanta(nombre, especie, luz, riego, estado);
        if (exito) {
            Toast.makeText(this, "Guardado en reportes exitosamente", Toast.LENGTH_SHORT).show();
        }
    }
}