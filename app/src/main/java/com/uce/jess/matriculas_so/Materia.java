package com.uce.jess.matriculas_so;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Luis on 10/03/2015.
 */
public class Materia extends Activity {
    public EditText cod_materia, numero_creditos, semestre;
    Spinner id_materia;
    Spinner id_carrera;
    Spinner Nombre;
    String select;
    int hasta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
        cod_materia = (EditText) findViewById(R.id.txtCod_Materia);
        numero_creditos = (EditText) findViewById(R.id.txtCreditos);
        semestre = (EditText) findViewById(R.id.txtSemestre);
        id_materia = (Spinner) findViewById(R.id.spinner);
        id_carrera = (Spinner) findViewById(R.id.spinner2);
        Nombre = (Spinner) findViewById(R.id.spinner3);
        cargar();
        cargar1();
        cargar2();

    }

    public void cargar() {

        BaseDatos dbobject = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        if (bd != null) {
            Cursor fila = bd.rawQuery("select ID_MATERIA, ID_CARRERA, NOMBRE, NUM_CREDITO,CODIGO_MATERIA,SEMESTRE from MATERIA", null);
            int cantidad = fila.getCount();
            int i = 0;
            String[] arreglo = new String[cantidad];
            if (fila.moveToFirst()) {
                do {
                    String linea = fila.getString(0) + " ";
                    arreglo[i] = linea;
                    i++;
                    if (fila.getInt(0) > hasta)
                        hasta = fila.getInt(0);
                } while (fila.moveToNext());
            } else
                Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                        .show();
            bd.close();

            //Ordenar Arreglo1

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
            id_materia.setAdapter(adapter);
        }
    }
    public void cargar1 () {
        BaseDatos dbobject1 = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd1 = dbobject1.getWritableDatabase();
        if (bd1 != null) {
            Cursor fila1 = bd1.rawQuery("select ID_MATERIA, ID_CARRERA, NOMBRE, NUM_CREDITO,CODIGO_MATERIA,SEMESTRE from MATERIA", null);
            int cantidad1 = fila1.getCount();
            int j = 0;
            String[] arreglo1 = new String[cantidad1];
            if (fila1.moveToFirst()) {
                do {
                    String linea = fila1.getString(0) + " ";
                    arreglo1[j] = linea;
                    j++;
                    if (fila1.getInt(0) > hasta)
                        hasta = fila1.getInt(0);
                } while (fila1.moveToNext());
            } else
                Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                        .show();
            bd1.close();


            //Ordenar Arreglo2

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_2, arreglo1);
            id_carrera.setAdapter(adapter1);

        }
    }

    public void cargar2 () {
        BaseDatos dbobject2 = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd2 = dbobject2.getWritableDatabase();
            if (bd2 != null) {
                Cursor fila2 = bd2.rawQuery("select ID_MATERIA, ID_CARRERA, NOMBRE, NUM_CREDITO,CODIGO_MATERIA,SEMESTRE from MATERIA", null);
                int cantidad2 = fila2.getCount();
                int l = 0;
                String[] arreglo2 = new String[cantidad2];
                if (fila2.moveToFirst()) {
                    do {
                        String linea = fila2.getString(0) + " ";
                        arreglo2[l] = linea;
                        l++;
                        if (fila2.getInt(0) > hasta)
                            hasta = fila2.getInt(0);
                    } while (fila2.moveToNext());
                } else
                    Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                            .show();
                bd2.close();

                //Ordenar Arreglo3

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_2, arreglo2);
                Nombre.setAdapter(adapter2);
            }
    }


    public void ingresar (View v){
        BaseDatos dbobject = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("COD_MATERIA", Integer.parseInt(cod_materia.getText().toString()));
        registro.put("NUMERO DE CREDITOS", numero_creditos.getText().toString());
        registro.put("SEMESTRE", semestre.getText().toString());
        //registro.put("ID_MATERIA", );
        try {
            bd.insert("MATERIA", null, registro);
            bd.close();
            Toast.makeText(this,
                    "Ingreso correcto",    Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void ingresar1 (View v){
        BaseDatos dbobject1 = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd1 = dbobject1.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("COD_MATERIA", Integer.parseInt(cod_materia.getText().toString()));
        registro.put("NUMERO DE CREDITOS", numero_creditos.getText().toString());
        registro.put("SEMESTRE", semestre.getText().toString());
        //registro.put("ID_MATERIA", );
        try {
            bd1.insert("MATERIA", null, registro);
            bd1.close();
            Toast.makeText(this,
                    "Ingreso correcto",    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void ingresar2 (View v){
        BaseDatos dbobject2 = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd2 = dbobject2.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("COD_MATERIA", Integer.parseInt(cod_materia.getText().toString()));
        registro.put("NUMERO DE CREDITOS", numero_creditos.getText().toString());
        registro.put("SEMESTRE", semestre.getText().toString());
        //registro.put("ID_MATERIA", );
        try {
            bd2.insert("MATERIA", null, registro);
            bd2.close();
            Toast.makeText(this,
                    "Ingreso correcto",    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
