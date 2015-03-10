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
import android.widget.AdapterView;

/**
 * Created by Gavilanes on 09/03/2015.
 */
public class Ubicacion_Admin  extends Activity {
    public EditText id_ubicacion,nombre;
    Spinner ubi_id_ubicacion;
    String referencia;
    String select;
    int hasta=0;
    int part1=0;
    int linea3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
        id_ubicacion = (EditText) findViewById(R.id.txt_cod_ubi);
        nombre=(EditText)findViewById(R.id.txt_nombre_ubi);
        ubi_id_ubicacion=(Spinner)findViewById(R.id.spinner_ubicacion);
        cargar();
        ubi_id_ubicacion.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                referencia=ubi_id_ubicacion.getSelectedItem().toString();
                if(referencia.length()!=0) {
                    String[] parts = referencia.split(" ");
                    part1 = Integer.parseInt(parts[0]); // cod
                    String part2 = parts[1]; // nombre
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

        public void cargar() {

            BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();

                if (bd != null) {
                    Cursor fila = bd.rawQuery("select NOMBRE, ID_UBICACION from UBICACION where UBI_ID_UBICACION is NULL", null);
                    int cantidad = fila.getCount();
                    int i = 1;
                    String[] arreglo = new String[cantidad + 1];
                    arreglo[0] = "";
                    if (fila.moveToFirst()) {
                            do {

                                String linea = fila.getInt(1) + " " + fila.getString(0);
                                arreglo[i] = linea;
                                i++;

                            if (fila.getInt(0) > hasta)
                                hasta = fila.getInt(0);
                        } while (fila.moveToNext());

                    } else
                        Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                                .show();
                    bd.close();

                    //Ordenar Arreglo

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
                    //ListView lista = (ListView) findViewById(R.id.listView1);
                    ubi_id_ubicacion.setAdapter(adapter);

                }
            }
        public void ingresar (View v){
            //codigo
            BaseDatos dbobject2 = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd2 = dbobject2.getWritableDatabase();
            int linea4 = 0;
            Cursor fila2 = bd2.rawQuery("select NOMBRE, ID_UBICACION from UBICACION", null);
            int cantidad = fila2.getCount();
            if (fila2.moveToFirst()) {
                do {
                    if (linea4 < fila2.getInt(1))
                        linea4 = fila2.getInt(1);
                } while (fila2.moveToNext());

            } else
                Toast.makeText(this, "No existen registros", Toast.LENGTH_SHORT)
                        .show();
            bd2.close();
            linea3 = linea4;
            String ch = String.valueOf(linea3);
            id_ubicacion.setText(ch);

            BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();

            int linea2=0;
            linea2=part1;
            ContentValues registro = new ContentValues();

            registro.put("ID_UBICACION", (linea3+1));
            //registro.put("ID_UBICACION", Integer.parseInt(id_ubicacion.getText().toString()));
            registro.put("NOMBRE", nombre.getText().toString());

            if(linea2!=0)
                registro.put("UBI_ID_UBICACION",linea2 );
            else registro.put("UBI_ID_UBICACION", (byte[]) null);
            try {
                bd.insert("UBICACION", null, registro);
                bd.close();
                }catch(){

            }
            }
        public void borrar(View v) {
            BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();
            String cod = id_ubicacion.getText().toString();

            if (cod.length() != 0) {
                int cant = bd.delete("UBICACION", "ID_UBICACION=" + cod, null);
                bd.close();
                if (cant == 1)
                    Toast.makeText(this, "Alumno BORRADO", Toast.LENGTH_SHORT)
                            .show();
                else
                    Toast.makeText(this, "Alumno NO EXISTE", Toast.LENGTH_SHORT)
                            .show();
            } else
                Toast.makeText(this, "Ingrese una cod para BORRAR",
                        Toast.LENGTH_SHORT).show();
        }
        public void modificar(View v) {
            BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();

            if (id_ubicacion.length()!=0) {
                int linea2=0;
                linea2=part1;
                ContentValues registro = new ContentValues();
                registro.put("ID_UBICACION", Integer.parseInt(id_ubicacion.getText().toString()));
                registro.put("NOMBRE", nombre.getText().toString());
                if(linea2!=0)
                    registro.put("UBI_ID_UBICACION",linea2 );
                else registro.put("UBI_ID_UBICACION", (byte[]) null);
                int cant = bd
                        .update("UBICACION", registro, "ID_UBICACION=" + id_ubicacion.getText(), null);
                bd.close();
                if (cant == 1)
                    Toast.makeText(this, "Datos ACTUALIZADOS",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "UBICACION NO EXISTE",
                            Toast.LENGTH_SHORT).show();
            }else

                Toast.makeText(this, "Ingrese una cedula para MODIFICAR",
                        Toast.LENGTH_SHORT).show();
        }
        public void listar(View v) {
            BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();

            if (id_ubicacion.length() != 0) {
                Cursor fila = bd.rawQuery(
                        "select ID_UBICACION,NOMBRE,UBI_ID_UBICACION from UBICACION where ID_UBICACION="
                                + Integer.parseInt(String.valueOf(id_ubicacion.getText())), null);
                if (fila.moveToFirst()) {
                    id_ubicacion.setText(fila.getString(0));
                    nombre.setText(fila.getString(1));

                } else
                    Toast.makeText(this, "No existe la UBICACION", Toast.LENGTH_SHORT)
                            .show();

                bd.close();
            } else
                Toast.makeText(this, "Ingrese un codigo para CONSULTAR",
                        Toast.LENGTH_SHORT).show();
        }
    }