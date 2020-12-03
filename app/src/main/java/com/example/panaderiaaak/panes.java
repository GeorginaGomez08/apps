package com.example.panaderiaaak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.panaderiaaak.adapter.PanesDetailsAdapter;
import com.example.panaderiaaak.database.PanDatbaseContract;
import com.example.panaderiaaak.database.PanDatbaseHelper;
import com.example.panaderiaaak.mode.PanDetails;

import java.util.ArrayList;
import java.util.List;

public class panes extends AppCompatActivity {
    PanDatbaseHelper dbHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter panAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnRegister, btreg;

    List<PanDetails> panDetailsList;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panes);

        dbHelper = new PanDatbaseHelper(this);
        db = dbHelper.getReadableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.rv_panes);
        btnRegister = (Button) findViewById(R.id.bt_register);
        btreg=(Button)findViewById(R.id.btreg);
        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent(panes.this,HomeActivity.class);
                startActivity(re);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(panes.this, Agregarpan.class);
                startActivity(intent);
                finish();
            }
        });

        panDetailsList = new ArrayList<PanDetails>();
        panDetailsList.clear();
        Cursor c1 = db.query(PanDatbaseContract.PanDatbase.TABLE_PAN, null, null, null, null, null, null);

        if (c1 != null && c1.getCount() != 0) {
            panDetailsList.clear();
            while (c1.moveToNext()) {
                PanDetails panDetailsItem = new PanDetails();

                panDetailsItem.setPanId(c1.getInt(c1.getColumnIndex(PanDatbaseContract.PanDatbase._ID)));
                panDetailsItem.setNombre(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.NOMBRE_PAN)));
                panDetailsItem.setDescripcion(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.DESCRIPCION_PAN)));
                panDetailsItem.setPrecio(c1.getString(c1.getColumnIndex(PanDatbaseContract.PanDatbase.PRECIO_PAN)));
                panDetailsList.add(panDetailsItem);
            }

        }

        c1.close();
        layoutManager = new LinearLayoutManager(this);
        panAdapter = new PanesDetailsAdapter(panDetailsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(panAdapter);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}