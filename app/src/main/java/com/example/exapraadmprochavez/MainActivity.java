package com.example.exapraadmprochavez;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText contrasenia;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = new DatabaseHelper(this);
        contrasenia = findViewById(R.id.etContrasenia);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contrasenia = contrasenia.getText().toString();
                Intent intent = new Intent(MainActivity.this, Formulario.class);
                intent.putExtra("contrasenia", contrasenia);
                startActivity(intent);
            }
        });

    }
}