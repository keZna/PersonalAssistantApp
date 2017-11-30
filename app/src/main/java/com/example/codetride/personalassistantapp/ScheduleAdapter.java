package com.example.codetride.personalassistantapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTride on 2017/10/12.
 */

public class ScheduleAdapter extends ArrayAdapter<Contact> {

    public ScheduleAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.schedule,parent,false);
        }

        Contact contact = getItem(position);

        TextView name,date,desc,notify;

        name = view.findViewById(R.id.name);
        name.setText(contact.getName());
        desc = view.findViewById(R.id.desc);
        desc.setText(contact.getDesc());
        date = view.findViewById(R.id.date);
        date.setText(contact.getDate());
        notify = view.findViewById(R.id.notify);
        notify.setText("Notify Me?: "+contact.getNotify());

        return view;
    }
}
