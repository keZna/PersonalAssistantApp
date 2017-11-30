package com.example.codetride.personalassistantapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TODO extends AppCompatActivity {

    GridView todo_list;
    EditText task, note;
    CheckBox no, yes;
    Button done, cancel, close, delete;
    Contact contact;
    Intent intent;
    String priority = "";
    ImageView no_data_image;
    CardView container, details;
    AppBarLayout appbar;
    ScheduleAdapter scheduleAdapter;
    FloatingActionButton addFloat, clearFloat, high, medium, low;
    CoordinatorLayout parent_layout;
    Animation animation;
    LinearLayout views;
    TextView lblHigh, lblMedium, lblLow, task_name, task_desc, task_notify, task_priority, task_date;

    ScheduleSQLHelper scheduleSQLHelper;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //Casting
        Casting();
        //setOnClick
        setOnClick();

        appbar = (AppBarLayout) findViewById(R.id.appbar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scheduleSQLHelper = new ScheduleSQLHelper(this);
        contacts = scheduleSQLHelper.getAll();

        if (contacts.size() == 0) {
            no_data_image.setVisibility(View.VISIBLE);
            parent_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            appbar.setExpanded(false);
            todo_list.setVisibility(View.GONE);
        } else {
            scheduleAdapter = new ScheduleAdapter(this, contacts);
            todo_list.setAdapter(scheduleAdapter);
            parent_layout.setBackgroundResource(R.drawable.side_nav_bar);
            no_data_image.setVisibility(View.GONE);
            todo_list.setVisibility(View.VISIBLE);
        }

        todo_list.setEnabled(true);

        todo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                appbar.setExpanded(false);
                contact = contacts.get(i);

                details.setVisibility(View.VISIBLE);
                todo_list.setEnabled(false);

                task_name.setText(contact.getName());
                task_desc.setText(contact.getDesc());
                task_notify.setText(contact.getNotify());
                task_date.setText(contact.getDate());
                task_notify.setText(contact.getNotify());
                task_priority.setText(priority);

            }
        });
    }

    public void Casting() {
        todo_list = (GridView) findViewById(R.id.todo_list);
        done = (Button) findViewById(R.id.done);
        cancel = (Button) findViewById(R.id.cancel);
        close = (Button) findViewById(R.id.close);
        delete = (Button) findViewById(R.id.delete);
        task = (EditText) findViewById(R.id.task);
        note = (EditText) findViewById(R.id.note);
        no = (CheckBox) findViewById(R.id.no);
        yes = (CheckBox) findViewById(R.id.yes);
        container = (CardView) findViewById(R.id.container);
        details = (CardView) findViewById(R.id.details);
        addFloat = (FloatingActionButton) findViewById(R.id.addFloat);
        clearFloat = (FloatingActionButton) findViewById(R.id.clearFloat);
        high = (FloatingActionButton) findViewById(R.id.high);
        medium = (FloatingActionButton) findViewById(R.id.medium);
        low = (FloatingActionButton) findViewById(R.id.low);
        lblHigh = (TextView) findViewById(R.id.lblHigh);
        lblMedium = (TextView) findViewById(R.id.lblMedium);
        lblLow = (TextView) findViewById(R.id.lblLow);
        task_name = (TextView) findViewById(R.id.task_name);
        task_desc = (TextView) findViewById(R.id.task_desc);
        task_notify = (TextView) findViewById(R.id.task_notify);
        task_priority = (TextView) findViewById(R.id.task_priority);
        task_date = (TextView) findViewById(R.id.task_date);
        no_data_image = (ImageView) findViewById(R.id.no_data_image);

        parent_layout = (CoordinatorLayout) findViewById(R.id.parent_layout);

        views = (LinearLayout) findViewById(R.id.views);
    }

    public void setOnClick() {

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task1, note1, notify = "";
                task1 = task.getText().toString();
                note1 = note.getText().toString();
                int id = 0;
                //checkboxes
                if (no.isChecked()) {
                    notify = "No";
                    yes.setEnabled(false);
                    no.setEnabled(true);
                } else if (yes.isChecked()) {
                    notify = "Yes";
                    no.setEnabled(false);
                    yes.setEnabled(true);
                }

                contact = new Contact(id++, task1, note1, notify, DateFormat.getDateInstance().format(new Date()));

                appbar.setExpanded(true);
                scheduleSQLHelper = new ScheduleSQLHelper(TODO.this);
                scheduleSQLHelper.ADD(contact);
                container.setVisibility(View.GONE);
                addFloat.setVisibility(View.VISIBLE);
                todo_list.setEnabled(true);

                Intent intent = new Intent(TODO.this, TODO.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setVisibility(View.GONE);
                addFloat.setVisibility(View.VISIBLE);
                todo_list.setEnabled(true);
                appbar.setExpanded(true);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.setVisibility(View.GONE);
                todo_list.setEnabled(true);

                appbar.setExpanded(false);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleSQLHelper.Delete(contact.getId());
                details.setVisibility(View.GONE);
                intent = new Intent(TODO.this, TODO.class);
                startActivity(intent);
                appbar.setExpanded(false);
                finish();
                details.setVisibility(View.GONE);
            }
        });

        addFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                container.setVisibility(View.VISIBLE);
                clearFloat.setVisibility(View.VISIBLE);
                addFloat.setVisibility(View.INVISIBLE);
                high.setVisibility(View.VISIBLE);
                medium.setVisibility(View.VISIBLE);
                views.setVisibility(View.VISIBLE);
                low.setVisibility(View.VISIBLE);
                lblMedium.setVisibility(View.VISIBLE);
                lblHigh.setVisibility(View.VISIBLE);
                lblLow.setVisibility(View.VISIBLE);
                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_top);
                views.startAnimation(animation);
            }
        });

        clearFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setVisibility(View.GONE);
                clearFloat.setVisibility(View.GONE);
                lblMedium.setVisibility(View.GONE);
                lblHigh.setVisibility(View.GONE);
                lblLow.setVisibility(View.GONE);
                addFloat.setVisibility(View.VISIBLE);
                views.setVisibility(View.GONE);
                todo_list.setEnabled(true);
                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_bottom);
                views.startAnimation(animation);

                high.setVisibility(View.GONE);
                medium.setVisibility(View.GONE);
                low.setVisibility(View.GONE);
                views.setVisibility(View.GONE);

            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views.setVisibility(View.GONE);
                addFloat.setVisibility(View.GONE);
                clearFloat.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                priority = "Low";
                task_priority.setText(priority);
                appbar.setExpanded(false);
//                todo_list.isInTouchMode();
                todo_list.setEnabled(false);

            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views.setVisibility(View.GONE);
                addFloat.setVisibility(View.GONE);
                clearFloat.setVisibility(View.GONE);
                appbar.setExpanded(false);
                priority = "Medium";
                container.setVisibility(View.VISIBLE);
                todo_list.setEnabled(false);
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views.setVisibility(View.GONE);
                addFloat.setVisibility(View.GONE);
                clearFloat.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                priority = "High";
                appbar.setExpanded(false);
                todo_list.setEnabled(false);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes.setEnabled(false);
                no.setEnabled(true);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes.setEnabled(true);
                no.setEnabled(false);
            }
        });
    }
}
