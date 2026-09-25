package com.example.cultivos_orquideas;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cultivo.db";
    private static final int DB_VERSION = 2;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT," +
                "role TEXT)");

        // Tabla de registros
        db.execSQL("CREATE TABLE registros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "temperatura REAL," +
                "humedad REAL," +
                "turno TEXT," +
                "timestamp INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS registros");
        onCreate(db);
    }
}
