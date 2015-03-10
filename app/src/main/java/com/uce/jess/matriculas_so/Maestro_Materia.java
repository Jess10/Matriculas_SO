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

public class Maestro_Materia extends Activity {
    public EditText id_maestro_materia,id_persona, id_materias,cupos,paralelo;
    String select;
    int hasta=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
        id_maestro_materia = (EditText) findViewById(R.id.txtCod_Materia);
        id_persona=(EditText)findViewById(R.id.txtCreditos);
        id_materias=(EditText)findViewById(R.id.txtSemestre);
        cupos=(EditText)findViewById(R.id.txtCupos);
        paralelo=(EditText)findViewById(R.id.txtParalelos);

        cargar();

    }
    public void cargar(){

        BaseDatos dbobject = new BaseDatos(this, "Maestro_Materia", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();

        if (bd!=null) {
            Cursor fila = bd.rawQuery("select ID_MAESTRO_MATERIA, ID_PERSONA, ID_MATERIA, CUPOS, PARALELO from MAESTRO_MATERIA", null);
            int cantidad = fila.getCount();
            int i=0;
            String [] arreglo=new String[cantidad];

            if (fila.moveToFirst()) {
                do{
                    String linea = fila.getString(0)+" ";
                    arreglo[i]=linea;
                    i++;
                    if(fila.getInt(0)>hasta)
                        hasta=fila.getInt(0);
                }while(fila.moveToNext());

            } else
                Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                        .show();
            bd.close();

            //Ordenar Arreglo


        }
    }
    public void ingresar (View v){
        BaseDatos dbobject = new BaseDatos(this, "Materia", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("COD_MAESTRO_MATERIA", Integer.parseInt(id_maestro_materia.getText().toString()));
        registro.put("COD_PERSONA", Integer.parseInt(id_persona.getText().toString()));
        registro.put("COD DE MATERIA", id_materias.getText().toString());
        registro.put("CUPOS", cupos.getText().toString());
        registro.put("PARALELO", Integer.parseInt(paralelo.getText().toString()));
        //registro.put("COD_MAESTRO_MATERIA", );
        try {
            bd.insert("MAESTRO_MATERIA", null, registro);
            bd.close();
            Toast.makeText(this,
                    "Ingreso correcto",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,
                    "error",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}