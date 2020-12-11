package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegisztracio, btnVissza;
    EditText etEmail, etFelhasznaloNev, etJelszo, etTeljesNev;
    DBaseHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        adatLekerdez();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void adatLekerdez() {

        String email = etEmail.getText().toString().trim();
        String nev = etFelhasznaloNev.getText().toString().trim();
        String jelszo = etJelszo.getText().toString().trim();
        String teljesNev = etTeljesNev.getText().toString().trim();



        if (nev.isEmpty() || jelszo.isEmpty() || email.isEmpty() || teljesNev.isEmpty()){
            Toast.makeText(this, "HIBA, üresen hagyott mező!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (adatbazis.emailEllenorzes(email)){
            Toast.makeText(this, "HIBA, Már van ilyen emailre regisztráció", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            //TODO: regisztrálás
        }
    }


    private void init() {
        btnVissza = findViewById(R.id.btn_vissza_bejelentkezeshez);
        btnRegisztracio = findViewById(R.id.btn_regisztracio_elkuldese);
        etEmail = findViewById(R.id.et_email);
        etFelhasznaloNev = findViewById(R.id.et_user);
        etJelszo = findViewById(R.id.et_password);
        etTeljesNev = findViewById(R.id.et_fullName);
        adatbazis = new DBaseHelper(RegisterActivity.this);
    }

}