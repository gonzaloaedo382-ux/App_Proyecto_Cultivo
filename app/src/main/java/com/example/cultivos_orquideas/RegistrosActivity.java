package com.example.cultivos_orquideas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrosActivity extends AppCompatActivity {
        EditText etUser, etPass, etRole;
        Button btnGuardar;
        DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro);

            etUser = findViewById(R.id.etUser);
            etPass = findViewById(R.id.etPass);
            etRole = findViewById(R.id.etRole);
            btnGuardar = findViewById(R.id.btnGuardar);
            dbHelper = new DatabaseHelper(this);

            btnGuardar.setOnClickListener(v -> {
                String user = etUser.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String role = etRole.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty() || role.isEmpty()) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", user);
                values.put("password", pass);
                values.put("role", role);

                long result = db.insert("usuarios", null, values);
                db.close();

                if (result != -1) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
