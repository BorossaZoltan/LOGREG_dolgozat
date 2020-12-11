package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    Button btn_Vissza;
    TextView tv_userKiir;
    DBaseHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
        StringBuilder builder = new StringBuilder();
        Cursor adatok = adatbazis.adatLekerdezes();
        builder.append(adatok.getString(5));
        tv_userKiir.setText(builder.toString());

        btn_Vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void init() {
        btn_Vissza = findViewById(R.id.btnVissza);
        tv_userKiir = findViewById(R.id.tv_userName);
        adatbazis = new DBaseHelper(LoggedInActivity.this);
    }
}