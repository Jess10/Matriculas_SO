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
public class Estudiante_Materia_Profesor extends Activity {
    public EditText estudiante_materia,id_persona, id_maestro_materia,matricula,costo;
    Spinner estado;
        String select;
        int hasta=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ubicacion);
            estudiante_materia = (EditText) findViewById(R.id.txtEstudianteMateria);
            id_persona=(EditText)findViewById(R.id.txtPersonas);
            id_maestro_materia=(EditText)findViewById(R.id.txtMaestroMat);
            estado=(Spinner)findViewById(R.id.spinner);
            matricula=(EditText)findViewById(R.id.txtMatricula);
            costo=(EditText)findViewById(R.id.txtCosto);
            cargar();

        }
        public void cargar(){

            BaseDatos dbobject = new BaseDatos(this, "Estudiante_Materia_Profesor", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();

            if (bd!=null) {
                Cursor fila = bd.rawQuery("select ESTUDIANTE_MATERIA, ID_PERSONA, ID_MAESTRO_MATERIA, MATRICULA,FECHA,COSTO from ESTUDIANTE_MATERIA_PROFESOR", null);
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

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);

                estado.setAdapter(adapter);
            }
        }
        public void ingresar (View v){
            BaseDatos dbobject = new BaseDatos(this, "Estdiante_Materia_Profesor", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();
            ContentValues registro = new ContentValues();

            registro.put("COD_ESTUDIANTE_MATERIA", Integer.parseInt(estudiante_materia.getText().toString()));
            registro.put("COD PERSONA", id_persona.getText().toString());
            registro.put("COD MAESTRO MATERIA", id_maestro_materia.getText().toString());
            registro.put("MATRICULA", matricula.getText().toString());
            registro.put("COSTO", costo.getText().toString());
            //registro.put("ID_ESTUDIANTE_MATERIA_PROFESOR", );
            try {
                bd.insert("ESTUDIANTE_MATERIA_PROFESOR", null, registro);
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