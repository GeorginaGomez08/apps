package com.example.panaderiaaak.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PanDatbaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Panes.db";

    public PanDatbaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_PAN_TABLE = "CREATE TABLE " + PanDatbaseContract.PanDatbase.TABLE_PAN +
            "( " + PanDatbaseContract.PanDatbase._ID + " INTEGER PRIMARY KEY," +
            PanDatbaseContract.PanDatbase.NOMBRE_PAN + " text," +
            PanDatbaseContract.PanDatbase.DESCRIPCION_PAN + " text," +
            PanDatbaseContract.PanDatbase.PRECIO_PAN + " text)";
    private static final String DELETE_PAN_TABLE = "DROP TABLE IF EXISTS " + PanDatbaseContract.PanDatbase.TABLE_PAN;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_PAN_TABLE);
        onCreate(db);
    }
}
