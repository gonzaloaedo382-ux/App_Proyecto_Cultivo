package com.example.cultivos_orquideas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvWelcome;
    Button btnRegistro, btnReportes, btnConfig;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnReportes = findViewById(R.id.btnReportes);
        dbHelper = new DatabaseHelper(this);

        String username = getIntent().getStringExtra("username");
        String role = getIntent().getStringExtra("role");

        tvWelcome.setText("Bienvenido " + username + " (" + role + ")");

        // Control de permisos
        if (!role.equals("admin") && !role.equals("tecnico")) {
            btnReportes.setEnabled(false);
            btnConfig.setEnabled(false);
        }
        btnRegistro.setOnClickListener(v -> {
            startActivity(new Intent(this,LoginActivity.class));
        });

        btnReportes.setOnClickListener(v -> {
            startActivity(new Intent(this,activity_reportes.class));
        });
    }
}
