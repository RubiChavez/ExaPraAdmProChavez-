package com.example.exapraadmprochavez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {//inicia clase
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //contexto, se prepara para qeu se aplique en cualcuwer otro acticity

        super(context, name, factory, version);
    }

    //Generador de objetos
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea tabla en base de datos administracion
        db.execSQL("create table empleado (numEmp integer primary key, nombre text, puesto text, diasT integer, sueldo integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists empleado");
        db.execSQL("create table empleado (numEmp integer primary key, nombre text, puesto text, diasT integer, sueldo integer)");
    }//tgermina onUpgrade
}//termina clase

