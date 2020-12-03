package com.example.panaderiaaak.database;

import android.provider.BaseColumns;

public class PanDatbaseContract {
    private PanDatbaseContract(){

    }

    public static class PanDatbase implements BaseColumns{
        public static final String TABLE_PAN = "tabla_panes";
        public static final String NOMBRE_PAN = "nombre";
        public static final String DESCRIPCION_PAN = "descripcion";
        public static final String PRECIO_PAN = "precio";
    }
}
