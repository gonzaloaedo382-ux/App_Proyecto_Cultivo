package com.example.cultivos_orquideas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*Aqui se declara el activityreportes para que se almacene los datos mostrados a los usuarios una ves haya resultado de la libreria */
public class activity_reportes extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listViewReportes;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reportes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        listViewReportes = findViewById(R.id.listViewReportes);

        // Se usa el ID exacto que tienes en tu XML: btnVolveralmenu
        btnVolver = findViewById(R.id.btnVolveralmenu);

        // Configurar el evento de clic para volver al MainActivity
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(activity_reportes.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Cierra la actividad de reportes
        });

        cargarReportes();
    }

    private void cargarReportes() {
        Cursor cursor = dbHelper.obtenerTodosLosRegistros();
        String[] fromColumns = new String[] {"nombre", "especie"};
        int[] toViews = new int[] {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                fromColumns,
                toViews,
                0
        );
        listViewReportes.setAdapter(adapter);
    }
}