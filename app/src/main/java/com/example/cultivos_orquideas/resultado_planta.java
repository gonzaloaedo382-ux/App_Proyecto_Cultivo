package com.example.cultivos_orquideas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
    private Uri fotoUri;
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
        fotoUri = Uri.parse(intent.getStringExtra("foto_uri"));
        humedad = intent.getDoubleExtra("humedad", 0.0);
        temperatura = intent.getDoubleExtra("temperatura", 0.0);
        imageViewFoto.setImageURI(fotoUri);
        tvHumedad.setText("Humedad: " + humedad + " %");
        tvTemperatura.setText("Temperatura: " + temperatura + " °C");
        btnGuardarReporte.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("foto_uri", fotoUri.toString());
            values.put("humedad", humedad);
            values.put("temperatura", temperatura);
            values.put("fecha", System.currentTimeMillis());

            long result = db.insert("reportes", null, values);
            if (result != -1) {
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

