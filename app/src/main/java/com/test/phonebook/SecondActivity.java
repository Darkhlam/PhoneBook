package com.test.phonebook;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView info = findViewById(R.id.info);
        info.setText("Наименование версии ОС: " + Build.ID + "\n"
                + "Устройство: " + Build.DEVICE + "\n"
                + "Изготовитель: " + Build.MANUFACTURER + "\n"
                + "Модель: " + Build.MODEL + "\n"
        );
    }

    public void Click(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
