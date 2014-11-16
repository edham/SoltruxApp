package com.soltrux.app.demo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class bdSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SoltruxBD"; 
    private static final int DATABASE_VERSION = 1;        


       private static final String CREATE_USUARIO = "CREATE TABLE USUARIO ("
                        + "int_id_usuario_movil integer NOT NULL PRIMARY KEY,"
                        + "str_nombres text NOT NULL,"
                        + "str_apellidos text NOT NULL,"
                        + "str_email text NOT NULL,"
                        + "str_telefono text NOT NULL);";
      

    private static final String CREATE_PERSONA ="CREATE TABLE PERSONA ("
                        +"int_id_persona INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +"str_nombres text NOT NULL,"
                        +"str_apellidos text NOT NULL,"
                        +"bool_sexo INTEGER NOT NULL,"
                        +"dat_fecha_nacimiento numeric NOT NULL);";

	public bdSQLite(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_USUARIO);
                db.execSQL(CREATE_PERSONA);

        }   
         
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {

                db.execSQL("drop table if exists USUARIO");
                db.execSQL(CREATE_USUARIO);
                db.execSQL("drop table if exists PERSONA");
                db.execSQL(CREATE_PERSONA);
        }
}