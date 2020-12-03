package com.example.panaderiaaak.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.panaderiaaak.HomeActivity;
import com.example.panaderiaaak.R;

public class Usuario  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuario,container,false);
        Button btnusuario = (Button) view.findViewById(R.id.btnusuario);
        btnusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(), HomeActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}
