package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnBejelentkezes, btnRegisztracio;
    EditText etFelhasznaloNev, etJelszo;
    DBaseHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regActivity);
                finish();
            }
        });
        btnBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatlekerdezes();
            }
        });
    }

    private void adatlekerdezes() {

        String nev = etFelhasznaloNev.getText().toString().trim();
        String jelszo = etJelszo.getText().toString().trim();


        Cursor adatok = adatbazis.adatLekerdezes();

        if (nev.isEmpty() || jelszo.isEmpty()){
            Toast.makeText(this, "HIBA, üresen hagyott mezők!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (adatok.getCount() == 0 || !adatbazis.felhasznaloEllenorzes(nev, jelszo)){
            Toast.makeText(this, "HIBA, nincs ilyen felhasználó!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            Intent logActivity = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(logActivity);
            finish();
        }
    }

    private void init() {
        btnBejelentkezes = findViewById(R.id.btn_bejelentkezes);
        btnRegisztracio = findViewById(R.id.btn_regisztracio);
        etFelhasznaloNev = findViewById(R.id.et_user_input);
        etJelszo = findViewById(R.id.et_password_input);
        adatbazis = new DBaseHelper(MainActivity.this);

    }
}