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
 * Created by Gavilanes on 09/03/2015.
 */
public class Ubicacion_Admin  extends Activity {
    public EditText id_ubicacion,nombre;
    Spinner ubi_id_ubicacion;
    String select;
    int hasta=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
        id_ubicacion = (EditText) findViewById(R.id.txt_cod_ubi);
        nombre=(EditText)findViewById(R.id.txt_nombre_ubi);
        ubi_id_ubicacion=(Spinner)findViewById(R.id.spinner_ubicacion);
        cargar();
        /*ubi_id_ubicacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select=ubi_id_ubicacion.getSelectedItem().toString();
            }
        });*/
    }
    public void cargar(){

        BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();

        if (bd!=null) {
            Cursor fila = bd.rawQuery("select NOMBRE, ID_UBICACION from UBICACION", null);
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
            //ListView lista = (ListView) findViewById(R.id.listView1);
            ubi_id_ubicacion.setAdapter(adapter);

        }
    }
    public void ingresar (View v){
        BaseDatos dbobject = new BaseDatos(this, "Matriculas", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("ID_UBICACION", Integer.parseInt(id_ubicacion.getText().toString()));
        registro.put("NOMBRE", nombre.getText().toString());
        //registro.put("UBI_ID_UBICACION", );
        try {
            bd.insert("UBICACION", null, registro);
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
