package com.test.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'phonebook' library on application startup.
    static {
        System.loadLibrary("phonebook");
    }

    RecyclerView contactsRecycler;
    ContactsAdapter contactsAdapter;
    TextView find;

    public native String contactListFromJNI();
    public native String contactSortFromJNI(String findName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find = findViewById(R.id.find);

        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Contacts> contactsLis = new ArrayList<>();
                try {
                    String x = find.getText().toString();
                    JSONArray json = new JSONObject(contactSortFromJNI(x)).getJSONArray("contacts");
                    for (int j = 0; j < json.length(); j++) {
                        String name = json.getJSONObject(j).getString("name");
                        String number = json.getJSONObject(j).getString("number");
                        contactsLis.add(new Contacts(name, number));
                    }
                    setContactsRecycler(contactsLis);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void onResume(){
        super.onResume();
        find.setText("");

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


    public void clickAbout(View view) {
        Intent intent = new Intent(MainActivity.this, AboutDeviceActivity.class);
        startActivity(intent);
    }


    public void clickAddContact(View view) {
        Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
        startActivity(intent);
    }
}