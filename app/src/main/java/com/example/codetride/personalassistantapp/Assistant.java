package com.example.codetride.personalassistantapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Assistant extends AppCompatActivity {

    RecyclerView recycler_chat;
    RecyclerAdapter recyclerAdapter;
    TextView command;
    AutoCompleteTextView txtChat;
    FloatingActionButton fab;
    SQLHelper sqlHelper = new SQLHelper(this);
    Contact contact;
    Intent intent;
    TextToSpeech speech;
    static final int ONE_SECOND_DELAY = 1000;
    //possible questions to be asked by the user.
    String questions[] = {"what is your name?",
            "how old are you?",
            "where do you see yourself in 5 years?",
            "do you have work experience?",
            "Tell me a bit about yourself?",
            "What are you?",
            "How are you?",
            "What are you wearing?",
            "Will you marry me?",
            "What is your Mom’s name?",
            "Do you have any pets?",
            "Are you human?",
            "Are you intelligent?",
            "What is your problem?",
            "When is your birthday?",
            "Who made you?",
            "Do you date?",
            "How’s it going?",
            "Are you male or female?",
            "Who is your daddy?",
            "How much do you cost?",
            "How much do you weight?",
            "Are you a virgin?",
            "Do you sleep?",
            "What's today's date?",
            "What time is it?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //casting
        Casting();
        //setOnClick
        setOnClick();
        //arrayList
        //speak
        SpeakLoud();
        //auto complete for questions
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
        txtChat.setAdapter(adapter);
        //for chat database
        final ArrayList<Contact> contacts = sqlHelper.getChats();
        recyclerAdapter = new RecyclerAdapter(this, contacts);
        recycler_chat.setAdapter(recyclerAdapter);
    }

    public void Casting() {
        //floatingActionButton
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //RecyclerViews
        recycler_chat = (RecyclerView) findViewById(R.id.recycler_chats);
        //TextViews
        command = (TextView) findViewById(R.id.command);
        //EditView
        txtChat = (AutoCompleteTextView) findViewById(R.id.txtChat);
    }

    public void setOnClick() {
        //chat recycler view
        recycler_chat.setLayoutManager(new LinearLayoutManager(this));

        command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Assistant.this, Command.class);
                finish();
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Add();
                //intent to Assistant activity
                Intent intent = new Intent(Assistant.this, Assistant.class);
                finish();
                startActivity(intent);
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

    public void Add() {
        //get array resources in string xml
        Resources getResources = getResources();
        //questions that will be asked by user
        final String[] questions = getResources.getStringArray(R.array.questionnaire);
        final String[] obey = getResources.getStringArray(R.array.obey);
        //answers that will be said by the assistant
        final String[] answer = getResources.getStringArray(R.array.answers);
        //input of the user to ask a question
        final String toSpeak = txtChat.getText().toString();
        //if statement on how to respond with the user's input.
        if (TextUtils.isEmpty(toSpeak)) {
            return;
        } else {
            //what can i do for you?
            //interview me, I'm ready for you sir/madam

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

            //a 1 second delay on how the assistant should answer the questions
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (toSpeak.equalsIgnoreCase(questions[0]) || toSpeak.equalsIgnoreCase(questions[1])) {
                        //answer back to the user
                        speech.speak(answer[0], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[2]) || toSpeak.equalsIgnoreCase(questions[3])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[1], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[4]) || toSpeak.equalsIgnoreCase(questions[5])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[2], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[6]) || toSpeak.equalsIgnoreCase(questions[7])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[3], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[8]) || toSpeak.equalsIgnoreCase(questions[9])
                            || toSpeak.equalsIgnoreCase(questions[10])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[4], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[11])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[5], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[12])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[6], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[13])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[7], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[14])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[8], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[15]) || toSpeak.equalsIgnoreCase(questions[16])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[9], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[17]) || toSpeak.equalsIgnoreCase(questions[18])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[10], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[19])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[11], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[20]) || toSpeak.equalsIgnoreCase(questions[21])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[12], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[22]) || toSpeak.equalsIgnoreCase(questions[23])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[13], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[24])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[14], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[25]) || toSpeak.equalsIgnoreCase(questions[26])
                            || toSpeak.equalsIgnoreCase(questions[27])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[15], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[28]) || toSpeak.equalsIgnoreCase(questions[29])
                            || toSpeak.equalsIgnoreCase(questions[30]) || toSpeak.equalsIgnoreCase(questions[31])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[16], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[32]) || toSpeak.equalsIgnoreCase(questions[33])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[17], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[34]) || toSpeak.equalsIgnoreCase(questions[35])
                            || toSpeak.equalsIgnoreCase(questions[36])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[18], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[37])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[19], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[38])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[20], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[39])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[21], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[40]) || toSpeak.equalsIgnoreCase(questions[41])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[22], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[42])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[23], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[43]) || toSpeak.equalsIgnoreCase(questions[44])
                            || toSpeak.equalsIgnoreCase(questions[45]) || toSpeak.equalsIgnoreCase(questions[46])
                            || toSpeak.equalsIgnoreCase(questions[47])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[24], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[48]) || toSpeak.equalsIgnoreCase(questions[49])
                            || toSpeak.equalsIgnoreCase(questions[50]) || toSpeak.equalsIgnoreCase(questions[51])
                            || toSpeak.equalsIgnoreCase(questions[52]) || toSpeak.equalsIgnoreCase(questions[53])
                            || toSpeak.equalsIgnoreCase(questions[54]) || toSpeak.equalsIgnoreCase(questions[55])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[25], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[56]) || toSpeak.equalsIgnoreCase(questions[57])
                            || toSpeak.equalsIgnoreCase(questions[58]) || toSpeak.equalsIgnoreCase(questions[59])
                            || toSpeak.equalsIgnoreCase(questions[60])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[26], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[61]) || toSpeak.equalsIgnoreCase(questions[62])
                            || toSpeak.equalsIgnoreCase(questions[63]) || toSpeak.equalsIgnoreCase(questions[64])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[27], TextToSpeech.QUEUE_FLUSH, null);

                    } else if (toSpeak.equalsIgnoreCase(questions[65])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak(answer[28], TextToSpeech.QUEUE_FLUSH, null);

                    }  else if (toSpeak.equalsIgnoreCase(obey[0])||toSpeak.equalsIgnoreCase(obey[1])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak("Today's date is, "+DateFormat.getDateInstance().format(new Date()), TextToSpeech.QUEUE_FLUSH, null);
                    }else if (toSpeak.equalsIgnoreCase(obey[2])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak("The time now it's. "+DateFormat.getTimeInstance().format(new Date()), TextToSpeech.QUEUE_FLUSH, null);
                        if (toSpeak.equalsIgnoreCase("Come again?")){
                            speech.speak("I said it's. "+DateFormat.getTimeInstance().format(new Date()), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }else if (toSpeak.equalsIgnoreCase(obey[3])||toSpeak.equalsIgnoreCase(obey[4])) {
                        //if the input of the user is not on the string-array then reply this
                        speech.speak("okay, so which name would you wish to call me?" , TextToSpeech.QUEUE_FLUSH, null);
                        if (toSpeak.endsWith("Personal Assistant")){
                            toSpeak.replace("Personal Assistant",""+speech);
                        }

                    }else {
                        if (!(toSpeak.contains("?"))) {
                            speech.speak("is that a question?", TextToSpeech.QUEUE_FLUSH, null);
                        } else {
                            speech.speak("I don't know how to answer that, since I'm programmed with basics only", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                }
            }, ONE_SECOND_DELAY);
        }
    }
}
