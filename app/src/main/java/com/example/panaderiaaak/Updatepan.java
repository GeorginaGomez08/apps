package com.example.panaderiaaak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.panaderiaaak.database.PanDatbaseContract;
import com.example.panaderiaaak.database.PanDatbaseHelper;
import com.example.panaderiaaak.mode.PanDetails;

import java.util.ArrayList;
import java.util.List;

public class Updatepan extends AppCompatActivity {
    PanDatbaseHelper dbHelper;
    EditText upNombre, upDescripcion, upPrecio;
    Button btUpdate;
    List<PanDetails> panDetails;
    String nombre, descripcion, precio;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepan);

        dbHelper = new PanDatbaseHelper(this);
        db = dbHelper.getWritableDatabase();
        upNombre = (EditText) findViewById(R.id.et_up_nombre);
        upDescripcion = (EditText) findViewById(R.id.et_up_descripcion);
        upPrecio = (EditText) findViewById(R.id.et_up_precio);
        btUpdate = (Button) findViewById(R.id.bt_update);

        final int rowId = getIntent().getIntExtra("USERID", -1);
        Cursor c1 = db.query(PanDatbaseContract.PanDatbase.TABLE_PAN, null, PanDatbaseContract.PanDatbase._ID + " = " + rowId, null, null, null, null);

        panDetails = new ArrayList<PanDetails>();
        panDetails.clear();

        if (c1 != null && c1.getCount() != 0) {
            while (c1.moveToNext()) {

                upNombre.setText(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.NOMBRE_PAN)));
                upDescripcion.setText(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.DESCRIPCION_PAN)));
                upPrecio.setText(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.PRECIO_PAN)));
            }

        }
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = upNombre.getText().toString();
                descripcion = upDescripcion.getText().toString();
                precio = upPrecio.getText().toString();

                ContentValues values = new ContentValues();
                values.put(PanDatbaseContract.PanDatbase.NOMBRE_PAN, nombre);
                values.put(PanDatbaseContract.PanDatbase.DESCRIPCION_PAN,descripcion);
                values.put(PanDatbaseContract.PanDatbase.PRECIO_PAN, precio);
                int updateId = db.update(PanDatbaseContract.PanDatbase.TABLE_PAN, values, PanDatbaseContract.PanDatbase._ID + " = " + rowId, null);
                if (updateId != -1) {

                    Toast.makeText(Updatepan.this, "¡Datos actualizados!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Updatepan.this, panes.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(Updatepan.this, "¡Error al guardar los datos!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}