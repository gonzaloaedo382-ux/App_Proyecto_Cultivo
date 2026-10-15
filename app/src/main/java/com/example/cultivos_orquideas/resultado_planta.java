package com.example.cultivos_orquideas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class resultado_planta extends AppCompatActivity {

    private ImageView imageViewFoto;
    private TextView tvHumedad, tvTemperatura;
    private Button btnGuardarReporte, btnVolverMain;
    private DatabaseHelper dbHelper;
    private Bitmap fotoBitmap;
    private double humedad;
    private double temperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_planta);
        imageViewFoto = findViewById(R.id.imageViewFoto);
        tvHumedad = findViewById(R.id.tvHumedad);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        btnGuardarReporte = findViewById(R.id.btnGuardarReporte);
        btnVolverMain = findViewById(R.id.btnVolverMain);
        dbHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        fotoBitmap = intent.getParcelableExtra("fotoPlanta");
        humedad = intent.getDoubleExtra("humedad", 1.5);
        temperatura = intent.getDoubleExtra("temperatura", 2.9);
        if (fotoBitmap != null) {
            imageViewFoto.setImageBitmap(fotoBitmap);
        }
        tvHumedad.setText("Humedad: " + humedad + " %");
        tvTemperatura.setText("Temperatura: " + temperatura + " °C");
        btnGuardarReporte.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("humedad", humedad);
            values.put("temperatura", temperatura);
            values.put("fecha", System.currentTimeMillis());

            long result = db.insert("reportes", null, values);
            if (result != 1) {
                Toast.makeText(this, "Reporte guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar reporte", Toast.LENGTH_SHORT).show();
            }
        });
        btnVolverMain.setOnClickListener(v -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });
    }
}
