package com.test.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    Context context;
    List<Contacts> contacts;

    public ContactsAdapter(Context context, List<Contacts> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactsItem = LayoutInflater.from(context).inflate(R.layout.contacts_item, parent, false);
        return new ContactsViewHolder(contactsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.phoneNumber.setText(contacts.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static final class ContactsViewHolder extends RecyclerView.ViewHolder{

        TextView name, phoneNumber;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);


        }
    }
}
