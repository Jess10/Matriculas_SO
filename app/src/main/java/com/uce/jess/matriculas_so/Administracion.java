package com.uce.jess.matriculas_so;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jess on 10/03/2015.
 */
public class Administracion extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administracion);

    }
    public void FPersona(View v ){
        Intent admin_uno=new Intent(this,Personal.class);
        startActivity(admin_uno);

    }


}
