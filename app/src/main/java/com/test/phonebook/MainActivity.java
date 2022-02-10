package com.test.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'phonebook' library on application startup.
    static {
        System.loadLibrary("phonebook");
    }
    RecyclerView contactsRecycler;
    ContactsAdapter contactsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(new Contacts("Вася","89601145497"));
        contactsList.add(new Contacts("Петя","89601145497"));
        contactsList.add(new Contacts("Лёша","89601145497"));

        setContactsRecycler(contactsList);


        /*TextView x = findViewById(R.id.sample_text);
        x.setText(stringFromJNI());*/
    }

    private void setContactsRecycler(List<Contacts> contactsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        contactsRecycler = findViewById(R.id.contactsRecycler);
        contactsRecycler.setLayoutManager(layoutManager);

        contactsAdapter = new ContactsAdapter(this, contactsList);
        contactsRecycler.setAdapter(contactsAdapter);
    }

    /**
     * A native method that is implemented by the 'phonebook' native library,
     * which is packaged with this application.
     */
    public native String contactsFromJNI();

    public void Click(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}