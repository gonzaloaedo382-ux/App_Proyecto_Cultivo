package com.example.cultivos_orquideas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Clase  para la gestión de la base de datos SQLite (registro.db).
 * Administra la creación, actualización y operaciones CRUD de las tablas de la aplicación.
 *
 * @author Tu Nombre / Proyecto Cultivos_Orquideas
 * @version 3.0
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cultivo.db";
    private static final int DB_VERSION = 3;
    /**
     * Constructor estándar
     * @param context Contexto de la aplicación o actividad actual.
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla de usuarios
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT," +
                "role TEXT)");

        db.execSQL("CREATE TABLE registros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "especie TEXT," +
                "luz TEXT," +
                "riego TEXT," +
                "estado TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS registros");
        onCreate(db);
    }
    /**
     * Inserta un nuevo registro de planta de orquídea en la base de datos utilizando ContentValues.
     *
     * @param nombre Nombre asignado a la planta.
     * @param especie Especie botánica de la orquídea.
     * @param luz Nivel de luz requerido.
     * @param riego Frecuencia de riego recomendada.
     * @param estado Estado de salud actual de la planta.
     * @return true si el registro fue insertado con éxito, false en caso contrario.
     * @throws android.database.SQLException si ocurre un fallo en la ejecución de la sentencia.
     */
    public boolean insertarRegistroPlanta(String nombre, String especie, String luz, String riego, String estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("especie", especie);
        values.put("luz", luz);
        values.put("riego", riego);
        values.put("estado", estado);

        long resultado = db.insert("registros", null, values);
        return resultado != -1;
    }
    public Cursor obtenerTodosLosRegistros() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id AS _id, nombre, especie FROM registros", null);
    }
    /**
     * Obtiene todos los registros almacenados en la base de datos para mostrarlos en reportes.
     *
     * @return Un Cursor con los resultados de la consulta estructurados para adaptadores.
     */
    public Cursor buscarRegistroPorEspecie(String especieBuscada) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id AS _id, nombre, especie FROM registros WHERE especie = ?", new String[]{especieBuscada});
    }
    /**
     * Busca registros específicos filtrando por la especie de la orquídea mediante consulta parametrizada.
     *
     * @param especieBuscada El nombre de la especie a consultar.
     * @return Un Cursor con los registros coincidentes.
     */
}