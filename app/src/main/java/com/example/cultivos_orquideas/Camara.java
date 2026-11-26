package com.example.cultivos_orquideas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Camara extends AppCompatActivity {
    private ImageView imageView;
    private Button btnIrResultado;
    private ActivityResultLauncher<Intent> tomarFotoLauncher;
    private ActivityResultLauncher<String> pedirPermisoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camara_activity);

        imageView = findViewById(R.id.imageViewEscaner);
        btnIrResultado = findViewById(R.id.btnIrResultado);
        btnIrResultado.setEnabled(false);

        // 1. Contrato para recibir la foto de la cámara
        tomarFotoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            imageView.setImageBitmap(imageBitmap);
                            btnIrResultado.setEnabled(true);
                        }
                    } else {
                        Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // 2. Contrato para pedir el permiso de la cámara en tiempo de ejecución
        pedirPermisoLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        abrirCamaraReal();
                    } else {
                        Toast.makeText(this, "Se necesita el permiso de cámara para continuar", Toast.LENGTH_LONG).show();
                        finish(); // Si rechaza el permiso, regresa de forma segura
                    }
                }
        );

        // Al iniciar la actividad, verificamos el permiso antes de abrir la cámara
        verificarYAbrirCamara();

        btnIrResultado.setOnClickListener(v -> {
            Intent intent = new Intent(Camara.this, resultado_planta.class);
            intent.putExtra("NOMBRE_PLANTA", "Orquídea Phalaenopsis");
            startActivity(intent);
        });
    }

    private void verificarYAbrirCamara() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            abrirCamaraReal();
        } else {
            // Si no está concedido, lo pedimos en pantalla
            pedirPermisoLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void abrirCamaraReal() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        tomarFotoLauncher.launch(takePictureIntent);
    }
}