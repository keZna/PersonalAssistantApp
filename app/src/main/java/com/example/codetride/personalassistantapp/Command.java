package com.example.codetride.personalassistantapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Command extends AppCompatActivity {

    RecyclerView recycler_command;
    RecyclerAdapter recyclerAdapter;
    TextView chat, open, search, note, cancel;
    AutoCompleteTextView txtChat;
    RelativeLayout keys;
    FloatingActionButton fabCommand;
    SQLHelper sqlHelper = new SQLHelper(this);
    Contact contact;
    Intent intent;
    TextToSpeech speech;
    ListView key_word_view;
    ImageView close, open1;
    ArrayAdapter arrayAdapter;
    static final int ONE_SECOND_DELAY = 1000;

    String[] Open1 = {"Open Google Play Store", "Open Google Map", "Open music player", "Open File Manager", "Open Gallery", "Open Whatsapp", "Open SHAREit"
            , "Open Settings", "Open Location", "Open Ringtone", "Open Wifi", "Open contact", "Open youtube"};
    String[] Search = {"Search Cristiano Ronaldo", "Search Lionel Messi", "Search Personal Assistant", "Search Ambitious", "Search SQLite Database"};
    String[] Note = {"Low Priority", "Normal Priority", "High Priority", "Urgent Priority"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //casting
        Casting();
        //setOnClick
        setOnClick();
        //recycler view
        //for command database
        final ArrayList<Contact> contactCommand = sqlHelper.getCommand();
        recyclerAdapter = new RecyclerAdapter(this, contactCommand);
        recycler_command.setAdapter(recyclerAdapter);
    }

    public void Casting() {
        //floatingActionButton
        fabCommand = (FloatingActionButton) findViewById(R.id.fabCommand);
        //RecyclerViews
        recycler_command = (RecyclerView) findViewById(R.id.recycler_command);
        //TextViews
        chat = (TextView) findViewById(R.id.chat);
        //EditView
        txtChat = (AutoCompleteTextView) findViewById(R.id.txtChat);
        //key word view
        open = (TextView) findViewById(R.id.open);
        search = (TextView) findViewById(R.id.search);
        note = (TextView) findViewById(R.id.note);
        cancel = (TextView) findViewById(R.id.cancel);
        //List view
        key_word_view = (ListView) findViewById(R.id.key_word_view);
        //image view
        close = (ImageView) findViewById(R.id.close);
        open1 = (ImageView) findViewById(R.id.open1);
        //Relativelayout
        keys = (RelativeLayout) findViewById(R.id.keys);
    }

    public void setOnClick() {
        //chat recycler view
        recycler_command.setLayoutManager(new LinearLayoutManager(this));

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Command.this, Assistant.class);
                finish();
                startActivity(intent);
            }
        });

        fabCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoThis();
                //intent to Assistant activity
                Intent intent = new Intent(Command.this, Command.class);
                finish();
                startActivity(intent);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setVisibility(View.INVISIBLE);
                note.setVisibility(View.INVISIBLE);
                open.setVisibility(View.INVISIBLE);
                key_word_view.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);

                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_left);
                key_word_view.startAnimation(animation);
                //array adapter
                arrayAdapter = new ArrayAdapter(Command.this, android.R.layout.simple_list_item_1, Open1);
                key_word_view.setAdapter(arrayAdapter);
                key_word_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0) {
                            txtChat.setText(Open1[0]);
                            fabCommand.callOnClick();
                        }else if (i == 1){
                            txtChat.setText(Open1[1]);
                            fabCommand.callOnClick();
                        }else if (i == 2){
                            txtChat.setText(Open1[2]);
                            fabCommand.callOnClick();
                        }else if (i == 3){
                            txtChat.setText(Open1[3]);
                            fabCommand.callOnClick();
                        }else if (i == 4){
                            txtChat.setText(Open1[4]);
                            fabCommand.callOnClick();
                        }else if (i == 5){
                            txtChat.setText(Open1[5]);
                            fabCommand.callOnClick();
                        }else if (i == 6){
                            txtChat.setText(Open1[6]);
                            fabCommand.callOnClick();
                        }else if (i == 7){
                            txtChat.setText(Open1[7]);
                            fabCommand.callOnClick();
                        }else if (i == 8){
                            txtChat.setText(Open1[8]);
                            fabCommand.callOnClick();
                        }else if (i == 9){
                            txtChat.setText(Open1[9]);
                            fabCommand.callOnClick();
                        }else if (i == 10){
                            txtChat.setText(Open1[10]);
                            fabCommand.callOnClick();
                        }else if (i == 11){
                            txtChat.setText(Open1[11]);
                            fabCommand.callOnClick();
                        }else if (i == 12){
                            txtChat.setText(Open1[12]);
                            fabCommand.callOnClick();
                        }

                    }
                });
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setVisibility(View.INVISIBLE);
                note.setVisibility(View.INVISIBLE);
                open.setVisibility(View.INVISIBLE);
                key_word_view.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);
                cancel.setBackgroundColor(Color.parseColor("#2e7d32"));
                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_left);
                key_word_view.startAnimation(animation);
                //array adapter
                arrayAdapter = new ArrayAdapter(Command.this, android.R.layout.simple_list_item_1, Search);
                key_word_view.setAdapter(arrayAdapter);
                key_word_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        txtChat.setText(Search[i]);
                        String name = txtChat.getText().toString();
                        Replace(name);
                        fabCommand.callOnClick();
                    }
                });
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setVisibility(View.INVISIBLE);
                note.setVisibility(View.INVISIBLE);
                open.setVisibility(View.INVISIBLE);
                key_word_view.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);
                cancel.setBackgroundColor(Color.parseColor("#424242"));
                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_left);
                key_word_view.startAnimation(animation);
                //array adapter
                arrayAdapter = new ArrayAdapter(Command.this, android.R.layout.simple_list_item_1, Note);
                key_word_view.setAdapter(arrayAdapter);
                key_word_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
                open.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                key_word_view.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                cancel.setBackgroundColor(Color.parseColor("#5e35b1"));

                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_right);
                key_word_view.startAnimation(animation);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open1.setVisibility(View.VISIBLE);
                keys.setVisibility(View.GONE);

                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_right);
                keys.startAnimation(animation);
            }
        });

        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open1.setVisibility(View.GONE);
                keys.setVisibility(View.VISIBLE);

                //Animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_left);
                keys.startAnimation(animation);
            }
        });
    }

    public void SpeakLoud() {
        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    public void DoThis() {
        //get array resources in string xml
        Resources getResources = getResources();
        //questions that will be asked by user
        final String[] command = getResources.getStringArray(R.array.command);
        //answers that will be said by the assistant
        final String[] obey = getResources.getStringArray(R.array.obey);
        //input of the user to ask a question
        final String toSpeak = txtChat.getText().toString();

        //if statement on how to respond with the user's input.
        if (TextUtils.isEmpty(toSpeak)) {
            return;
        } else {
            //holds/stores the input of the user
            String chats = txtChat.getText().toString();
            //start at 0
            int id = 0;
            //constructor that consist of 3 parameters
            contact = new Contact(id++, chats, DateFormat.getTimeInstance().format(new Date().getTime()));
            //SQLHelper
            sqlHelper = new SQLHelper(this);
            //Adding method
            sqlHelper.AddChat(contact);
            //a 1 second delay on how the assistant should obey the command
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (toSpeak.equalsIgnoreCase(command[0])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("Please install whatsapp app", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[1])) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[2]) || toSpeak.equalsIgnoreCase(command[3])) {
                        intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[4])) {
                        intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[5])|| toSpeak.equalsIgnoreCase(command[6])) {
                        intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[7])) {
                        intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[8])) {
                        intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.contains(command[9]) || toSpeak.contains(command[10])) {
                        Replace(toSpeak);
                    } else if (toSpeak.equalsIgnoreCase(command[11])) {
                        composeEmail();
                    } else if (toSpeak.equalsIgnoreCase(command[12])) {
                        intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    } else if (toSpeak.equalsIgnoreCase(command[13]) || toSpeak.equalsIgnoreCase(command[14])) {

                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.lenovo.anyshare.gps");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("Please install SHAREit app", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[15]) || toSpeak.equalsIgnoreCase(command[16])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.android.contacts");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find contacts", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[17]) || toSpeak.equalsIgnoreCase(command[18])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find youtube", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[19]) || toSpeak.equalsIgnoreCase(command[20])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.android.browser");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find the browser", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[21]) || toSpeak.equalsIgnoreCase(command[22]) || toSpeak.equalsIgnoreCase(command[23])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.android.mediacenter");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find music player", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[24]) || toSpeak.equalsIgnoreCase(command[25])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.android.vending" );
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find google play store", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[26]) || toSpeak.equalsIgnoreCase(command[27])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find file manage", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[28]) || toSpeak.equalsIgnoreCase(command[29])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find google Maps", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[30]) || toSpeak.equalsIgnoreCase(command[31])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find youtube", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else if (toSpeak.equalsIgnoreCase(command[30]) || toSpeak.equalsIgnoreCase(command[31])) {
                        try {
                            // Raise exception if whatsapp doesn't exist
                            intent = getPackageManager().getLaunchIntentForPackage("com.uc.browser.en");
                            startActivity(intent);
                        } catch (Exception e) {
                            speech.speak("can't find that browser", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else {
                        speech.speak("I don't know how to answer that, since I'm programmed with basics only", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }, ONE_SECOND_DELAY);
        }
    }

    public String Replace(String toSpeak) {
        String myString = toSpeak;
        myString = myString.replace("Search", "").trim();
        myString = myString.replace("search", "").trim();
        intent = new Intent(Command.this, Website.class);
        intent.putExtra("Search", myString);
        startActivity(intent);
        return toSpeak;
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Enter Email To Send To"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "TYPE YOUR SUBJECT IN CAPITAL LETTERS");
        intent.putExtra(Intent.EXTRA_TEXT, "Write Your Message Here");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(Command.this, Home.class);
        startActivity(intent);
        finish();
    }


}
