package com.example.panaderiaaak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.panaderiaaak.database.PanDatbaseContract;
import com.example.panaderiaaak.database.PanDatbaseHelper;

public class Agregarpan extends AppCompatActivity {

    PanDatbaseHelper dbHelper;
    String nombre, decripcion, precio;
    SQLiteDatabase db;
    private EditText etNombre, etDescripcion, etPrecio;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarpan);
        dbHelper = new PanDatbaseHelper(this);
        db = dbHelper.getWritableDatabase();

        etNombre = (EditText) findViewById(R.id.et_nombre);
        etDescripcion = (EditText) findViewById(R.id.et_descripcion);
        etPrecio = (EditText) findViewById(R.id.et_precio);
        btRegister = (Button) findViewById(R.id.bt_registration);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = etNombre.getText().toString();
                decripcion = etDescripcion.getText().toString();
                precio = etPrecio.getText().toString();

                ContentValues values = new ContentValues();
                values.put(PanDatbaseContract.PanDatbase.NOMBRE_PAN,nombre);
                values.put(PanDatbaseContract.PanDatbase.DESCRIPCION_PAN,decripcion);
                values.put(PanDatbaseContract.PanDatbase.PRECIO_PAN, precio);
                long rowId = db.insert(PanDatbaseContract.PanDatbase.TABLE_PAN, null, values);
                if (rowId != -1) {
                    Toast.makeText(Agregarpan.this, "Producto registrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Agregarpan.this, panes.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Agregarpan.this, "Producto no registrado! ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}