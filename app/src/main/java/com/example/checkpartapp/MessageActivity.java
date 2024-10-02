package com.example.checkpartapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MessageActivity extends AppCompatActivity {
    Button btnBack, btnGenerateSpeech;
    TextView txtResult;
    TextToSpeech t1;
    private boolean isTtsReady = false;  // Flag to check if TTS is ready

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);

        btnBack = findViewById(R.id.btnBack);
        btnGenerateSpeech = findViewById(R.id.btnGenerateSpeech);
        txtResult = findViewById(R.id.txtResult);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    int result = t1.setLanguage(Locale.ENGLISH);

                    t1.setSpeechRate(2.0f);


                    // Check if the language is supported
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        isTtsReady = true;  // Mark TTS as ready
                        speakResult();  // Automatically trigger speech after initialization
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }

            }
        });

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getBundleExtra("myPackage");
        String result = myBundle.getString("rs");

        txtResult.setText(result);

        if (result.equals("OK")) {
            txtResult.setTextColor(Color.parseColor("#6495ED"));
            txtResult.setBackgroundColor(Color.parseColor("#DAF7A6"));
            // Automatically trigger the Check button click
            btnGenerateSpeech.performClick();
        } else {
            txtResult.setTextColor(Color.WHITE);
            txtResult.setBackgroundColor(Color.parseColor("#cd6155"));
            btnGenerateSpeech.performClick();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGenerateSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTtsReady) {
                    speakResult();  // Trigger TTS on button click
                } else {
                    Log.e("TTS", "TextToSpeech not ready yet");
                }
            }
        });
    }

    private void speakResult() {
        String text = txtResult.getText().toString();
        if (isTtsReady) {
            // Check if the text is "NG" and modify it
            if (text.equalsIgnoreCase("NG")) {
                text = "N G";  // Separate the letters with a space
                t1.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }else if(text.equalsIgnoreCase("OK")) {
                text = "O K";  // Separate the letters with a space
                t1.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }

        }

    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech to release resources
        if (t1 != null&&isTtsReady) {
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }
}