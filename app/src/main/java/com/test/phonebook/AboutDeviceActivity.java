package com.test.phonebook;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutDeviceActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_device_activity);

        TextView info = findViewById(R.id.info);
        info.setText("Наименование версии ОС: " + Build.ID + "\n"
                + "Устройство: " + Build.DEVICE + "\n"
                + "Изготовитель: " + Build.MANUFACTURER + "\n"
                + "Модель: " + Build.MODEL + "\n"
        );
    }

}
