package com.example.exapraadmprochavez;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText contraseniA;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = new DatabaseHelper(this);
        contraseniA = findViewById(R.id.etContrasenia);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contrasenia = contraseniA.getText().toString();
                if(contrasenia.equals("Admin123")){
                    Intent intent = new Intent(MainActivity.this, Formulario.class);
                    intent.putExtra("contrasenia", contrasenia);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"La contaseña es incorrecta", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}