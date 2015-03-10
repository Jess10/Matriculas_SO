package com.uce.jess.matriculas_so;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Matricula_Registro extends Activity {

    Spinner carrera;
    ListView materia;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula__registro);
        carrera=(Spinner)findViewById(R.id.spinner_carrera);
        materia=(ListView)findViewById(R.id.list_materias);
        nombre=(TextView)findViewById(R.id.txt_nom_ape);
        Bundle extras=getIntent().getExtras();
        if(extras == null){
            return;
        }
        String snombre=extras.getString("nombre1");
        String sapellido=extras.getString("nombre2");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matricula__registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
