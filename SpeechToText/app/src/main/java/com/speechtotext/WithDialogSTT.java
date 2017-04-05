package com.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class WithDialogSTT extends AppCompatActivity {

    private TextView tvSHow;
    private ImageView imgSpeech;
    private int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSHow=(TextView)findViewById(R.id.tv_show_result);
        imgSpeech=(ImageView)findViewById(R.id.img_speech);

        imgSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recongizeSpeech();
            }
        });
    }

    private void recongizeSpeech() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()/*"hi_IN"*/ /*"ja_JP"*/);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE && data!=null){
            ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            tvSHow.setText(result.get(0));
        }
    }
}
