package com.uce.jess.matriculas_so;

import android.os.Bundle;
import android.app.Activity;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by Jess on 10/03/2015.
 */
public class Personal extends Administracion {
    public EditText id_persona,id_ubicacion,nombrepersona,apellidoP,direccionP,cedula,fechanacimiento,mail,telefono;
    Spinner sexo,ocupacion;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_persona);
        /*llamada de los campos de persona*/
        id_persona=(EditText) findViewById(R.id.edit_idPersona);
        id_ubicacion=(EditText) findViewById(R.id.edit_idubicacion);
        nombrepersona=(EditText) findViewById(R.id.edit_nombrePersona);
        apellidoP=(EditText) findViewById(R.id.edit_apellidoPersona);
        direccionP=(EditText) findViewById(R.id.edit_direccion);
        cedula=(EditText) findViewById(R.id.edit_cedula);
       //sexo=(Spinner)findViewById(R.id.spinner_sexo);
        fechanacimiento=(EditText)findViewById(R.id.edit_fechanacimiento);
        mail=(EditText)findViewById(R.id.edit_mail);
        telefono=(EditText)findViewById(R.id.edit_telefono);
        //ocupacion=(Spinner)findViewById(R.id.spinner_ocupacion);
       // Spinner spinner = (Spinner) findViewById(R.id.GameSpinner);













    }
}
