package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegisztracio, btnVissza;
    EditText etEmail, etFelhasznaloNev, etJelszo, etTeljesNev;
    DBaseHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        String email = etEmail.getText().toString().trim();
        String nev = etFelhasznaloNev.getText().toString().trim();
        String jelszo = etJelszo.getText().toString().trim();
        String teljesNev = etTeljesNev.getText().toString().trim();
        if (!(email.isEmpty() || nev.isEmpty() || jelszo.isEmpty() || teljesNev.isEmpty())){
            btnRegisztracio.setEnabled(true);
        }

        adatLekerdez();

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        btnRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatLekerdez();
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
        else if (emailValidacio(etEmail) == false){
            return;
        }
        else{
            adatbazis.adatRogzites(email,nev, jelszo, teljesNev);
            Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
            Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(vissza);
            finish();
            return;

        }
    }

    private boolean emailValidacio(EditText email){
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return true;
        }else{
            Toast.makeText(this, "HIBA, hibás email cím!", Toast.LENGTH_SHORT).show();
            return false;
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