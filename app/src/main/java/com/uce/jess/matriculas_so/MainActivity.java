package com.uce.jess.matriculas_so;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ubi(View v) {
        Intent siguiente = new Intent(getApplicationContext(), Ubicacion_Admin.class);
        startActivity(siguiente);
    }

    public void prueba(View v) {
        Intent sigAdmi = new Intent(this, Administracion.class);
        startActivity(sigAdmi);
    }

    public void Ingresar(View v) {
        EditText editName = (EditText) findViewById(R.id.etextUser);
        EditText editPas = (EditText) findViewById(R.id.etextpass);
        if (editName.getText().toString().equals("root") && editPas.getText().toString().equals("root")) {
            Intent e = new Intent(this, Administracion.class);
            startActivity(e);
        } else {
            Toast.makeText(getApplicationContext(), "El USER o Pass no son correctos ", Toast.LENGTH_LONG).show();
        }
    }
}