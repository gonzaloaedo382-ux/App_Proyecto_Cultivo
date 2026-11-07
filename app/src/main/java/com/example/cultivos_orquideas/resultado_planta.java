package com.example.cultivos_orquideas;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class resultado_planta extends AppCompatActivity {
    private TextView txtNombre, txtEspecie, txtLuz, txtRiego, txtEstado;
    private ImageView imgPlanta;

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
            /* se muestra estos datos predeterminados si no encuentra coincidencia en la libreria*/
        } else {
            mostrarDatosPlanta(
                    "Orquídea Phalaenopsis",
                    "Phalaenopsis amabilis",
                    "Luz indirecta brillante",
                    "Cada 7-10 días",
                    "Saludable",
                    R.drawable.phalaenopsis
            );
        }
    }
    public void mostrarDatosPlanta(String nombre, String especie, String luz, String riego, String estado, int imagenResId) {
        txtNombre.setText(nombre);
        txtEspecie.setText("Especie: " + especie);
        txtLuz.setText("Luz: " + luz);
        txtRiego.setText("Riego: " + riego);
        txtEstado.setText("Estado: " + estado);
        imgPlanta.setImageResource(imagenResId);
    }
}