package com.test.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CreateContactActivity extends AppCompatActivity {

    public native void addContactsFromJNI(String name, String number);
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact_activity);
    }


    public void clickAddContact(View view) {
        EditText name = findViewById(R.id.enter_name);
        EditText number = findViewById(R.id.enter_number);
        addContactsFromJNI(name.getText().toString(), number.getText().toString());
        Intent intent = new Intent(CreateContactActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
