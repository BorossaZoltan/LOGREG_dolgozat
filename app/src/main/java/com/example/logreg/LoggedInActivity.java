package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    Button btn_Vissza;
    TextView tv_userKiir;
    DBaseHelper adatbazis;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
        String nev = sharedPref.getString("nev","");
        String jelszo = sharedPref.getString("jelszo","");
        Cursor adatok = adatbazis.belepo(nev, jelszo);
        StringBuilder builder = new StringBuilder();
        while(adatok.moveToNext()){
            builder.append("Üdvözöllek: ").append(adatok.getString(0));
        }
        tv_userKiir.setText(builder.toString());

        btn_Vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visszaMainre = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(visszaMainre);
                finish();
            }
        });
    }

    private void init() {
        btn_Vissza = findViewById(R.id.btnVissza);
        tv_userKiir = findViewById(R.id.tv_userName);
        adatbazis = new DBaseHelper(LoggedInActivity.this);
        sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
    }
}