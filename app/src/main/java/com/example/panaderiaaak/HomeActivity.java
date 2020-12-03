package com.example.panaderiaaak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import Data.DatabaseHelper;
import Model.User;

public class HomeActivity extends AppCompatActivity {

    TextView textViewUserName, textViewName, textViewPassword, textViewEmail, textViewLogout;

    Button btnEditDetails, btnChangePassword, btproduct,btmenu;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    String textViewUsernameString;
    String textViewNameString;
    String textViewEmailString;
    String textViewPasswordString;
    int textViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");

        textViewUserName = findViewById(R.id.textViewUserName);
        textViewName = findViewById(R.id.textViewName);
        textViewPassword = findViewById(R.id.textViewPassword);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewLogout = findViewById(R.id.textViewLogout);

        final Bundle b = getIntent().getExtras();


        textViewID = Integer.parseInt(b.getString("textViewId"));
        textViewUsernameString = b.getString("textViewUsername");
        textViewNameString = b.getString("textViewUsername");
        textViewEmailString = b.getString("textViewEmail");
        textViewPasswordString = b.getString("textViewPassword");


        Log.d("Data", String.valueOf(textViewID));
        Log.d("Data", "User name: " + textViewUsernameString);
        Log.d("Data", "name: " + textViewNameString);
        Log.d("Data", "Email: " + textViewEmailString);
        Log.d("Data", "password: " + textViewPasswordString);

        textViewUserName.setText("Hola " + textViewUsernameString);
        textViewName.setText(textViewNameString);
        textViewPassword.setText(textViewPasswordString);
        textViewEmail.setText(textViewEmailString);

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });

        btnEditDetails = findViewById(R.id.btnEditDetails);
        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserDidals();
            }
        });

        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        btproduct = findViewById(R.id.btproduct);
        btproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prod = new Intent(HomeActivity.this,panes.class);
                startActivity(prod);
            }
        });

        btmenu=findViewById(R.id.btmenu);
        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent men = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(men);
            }
        });

    }

    public void changePassword() {
        alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        inflater = LayoutInflater.from(HomeActivity.this);
        final View view = inflater.inflate(R.layout.popup_password, null);
        final EditText editTextPasswordPopup = view.findViewById(R.id.editTextPasswordPopup);
        final EditText editTextConfPasswordPopup = view.findViewById(R.id.editTextConfPasswordPopup);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();

        final User user = new User();
        user.setId(textViewID);
        user.setUserName(textViewNameString);
        user.setEmail(textViewEmailString);
        user.setPassword(textViewPasswordString);


        final DatabaseHelper db = new DatabaseHelper(HomeActivity.this);

        Button saveButtonPassword = view.findViewById(R.id.saveButtonPassword);

        saveButtonPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("check", editTextConfPasswordPopup.getText().toString());
                Log.d("check", editTextPasswordPopup.getText().toString());

                if (Integer.parseInt(editTextPasswordPopup.getText().toString()) == Integer.parseInt(editTextPasswordPopup.getText().toString()) ) {
                    user.setPassword(editTextPasswordPopup.getText().toString());
                    db.updateUser(user);
                    Toast.makeText(HomeActivity.this, "¡Nueva contraseña!", Toast.LENGTH_SHORT).show();
                    Snackbar.make(v, "¡Contraseña guardada!", Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                } else{
                    Snackbar.make(view, "¡Contraseña no guardada! ", Snackbar.LENGTH_LONG).show();
                    Toast.makeText(HomeActivity.this, "what else ", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

            }
        });


    }


    public void editUserDidals() {
        alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        inflater = LayoutInflater.from(HomeActivity.this);
        final View view = inflater.inflate(R.layout.popup, null);
        final EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();

        final User user = new User();
        user.setId(textViewID);
        user.setUserName(textViewNameString);
        user.setEmail(textViewEmailString);
        user.setPassword(textViewPasswordString);

        editTextUsername.setText(user.getUserName());
        editTextEmail.setText(user.getEmail());

        final DatabaseHelper db = new DatabaseHelper(HomeActivity.this);

        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());

                if (!editTextUsername.getText().toString().isEmpty()
                        && !editTextEmail.getText().toString().isEmpty()) {
                    db.updateUser(user);
                    Snackbar.make(v, "¡Datos guardados!", Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                } else {
                    Snackbar.make(view, "¡Datos no guardados!", Snackbar.LENGTH_LONG).show();
                }
                dialog.dismiss();

            }
        });
    }
}