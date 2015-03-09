package com.uce.jess.matriculas_so;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gavilanes on 09/03/2015.
 */
public class BaseDatos extends SQLiteOpenHelper {


    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;\n" +
                "\n" +
                "CREATE TABLE CARRERA (\n" +
                "   ID_CARRERA           INT4                 not null, \n" +
                "   NOMBRE               VARCHAR(100)         null,\n" +
                "   primary key (ID_CARRERA)\n" +
                ");\n" +
                "CREATE TABLE ESTUDIANTE_MATERIA_PROFESOR (\n" +
                "   ESTUDIANTE_MATERIA   INT4     \t           not null,\n" +
                "   ID_PERSONA           INT4                 null,\n" +
                "   ID_MAESTRO_MATERIA   INT4                 null,\n" +
                "   ESTADO               varchar(20)          not null default 'MAT'\n" +
                "   check (ESTADO in ('MAT','APR','PER')),\n" +
                "   MATRICULA            INT2                 null,\n" +
                "   FECHA                date                 null,\n" +
                "   COSTO                INT4                 null,\n" +
                "   primary key (ESTUDIANTE_MATERIA)\n" +
                "   foreign key (ID_MAESTRO_MATERIA) references MAESTRO_MATERIA (ID_MAESTRO_MATERIA)\n" +
                "   foreign key (ID_PERSONA) references PERSONA (ID_PERSONA)\n" +
                ");\n" +
                "CREATE TABLE MAESTRO_MATERIA (\n" +
                "   ID_MAESTRO_MATERIA   INT4                 not null,\n" +
                "   ID_PERSONA           INT4                 null,\n" +
                "   ID_MATERIA           INT4                 null,\n" +
                "   CUPOS                INT2                 not null,\n" +
                "   PARALELO             INT2                 null,\n" +
                "   primary key (ID_MAESTRO_MATERIA)\n" +
                "   foreign key (ID_PERSONA) references PERSONA (ID_PERSONA)  \n" +
                "   foreign key (ID_MATERIA) references MATERIA (ID_MATERIA)\n" +
                ");\n" +
                "CREATE TABLE MATERIA (\n" +
                "   ID_MATERIA           INT4                 not null,\n" +
                "   ID_CARRERA           INT4                 null,\n" +
                "   NOMBRE               VARCHAR(50)          null,\n" +
                "   NUM_CREDITO          INT4                 null,\n" +
                "   CODIGO_MATERIA       VARCHAR(100)         null,\n" +
                "   SEMESTRE             INT4                 null,\n" +
                "   primary key (ID_MATERIA)\n" +
                "   foreign key (ID_CARRERA) references CARRERA (ID_CARRERA)\n" +
                ");\n" +
                "CREATE TABLE PERSONA (\n" +
                "   ID_PERSONA           INT4                 not null,\n" +
                "   ID_UBICACION         INT4                 null,\n" +
                "   NOMBRE               VARCHAR(50)          not null,\n" +
                "   APELLIDO             VARCHAR(50)          not null,\n" +
                "   DIRECCION            VARCHAR(100)         null,\n" +
                "   CEDULA               VARCHAR(10)          not null,\n" +
                "   USUARIO              VARCHAR(50)          not null,\n" +
                "   PASSWORD             VARCHAR(50)          not null,\t\n" +
                "   SEXO                 VARCHAR(50)          not null,\n" +
                "   FECHA_NACIMIENTO     DATE                 not null,\n" +
                "   MAIL                 varchar(100)         null,\n" +
                "   TELEFONO             varchar(11)          null,\n" +
                "   OCUPACION            varchar(15)          not null default 'EST'\n" +
                "   check (OCUPACION in ('PRO','EST')),\n" +
                "   primary key (ID_PERSONA)\n" +
                "   foreign key (ID_UBICACION) references UBICACION (ID_UBICACION)\n" +
                ");\n" +
                "CREATE TABLE UBICACION (\n" +
                "   ID_UBICACION         INT4                 not null,\n" +
                "   UBI_ID_UBICACION     INT4                 null,\n" +
                "   NOMBRE               VARCHAR(100)         null,\n" +
                "   primary key (ID_UBICACION)\n" +
                "   foreign key (UBI_ID_UBICACION) references UBICACION (ID_UBICACION)\n" +
                ");\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists UBICACION");
        db.execSQL("drop table if exists PERSONA");
        db.execSQL("drop table if exists MATERIA");
        db.execSQL("drop table if exists CARRERA");
        db.execSQL("drop table if exists MAESTRO_MATERIA");
        db.execSQL("drop table if exists ESTUDIANTE_MATERIA_PROFESOR");
    }
}
