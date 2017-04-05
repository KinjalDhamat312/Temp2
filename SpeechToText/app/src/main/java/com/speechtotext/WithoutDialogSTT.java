package com.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class WithoutDialogSTT extends AppCompatActivity  implements View.OnClickListener {

    private TextView mText;
    private SpeechRecognizer sr;
    private static final String TAG = "MyStt3Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition_test);
        Button speakButton = (Button) findViewById(R.id.btnSpeech);
        mText = (TextView) findViewById(R.id.tvResult);
        speakButton.setOnClickListener(this);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());

    }
    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {
            Log.d(TAG, "onReadyForSpeech");

        }
        public void onBeginningOfSpeech()
        {
            mText.setText("Speech start");
            Log.d(TAG, "onBeginningOfSpeech");
        }
        public void onRmsChanged(float rmsdB)
        {
            Log.d(TAG, "onRmsChanged");
        }
        public void onBufferReceived(byte[] buffer)
        {
            Log.d(TAG, "onBufferReceived");
        }
        public void onEndOfSpeech()
        {
            mText.setText("Speech end");
            Log.d(TAG, "onEndofSpeech");
        }
        public void onError(int error)
        {
            Log.d(TAG,  "error " +  error);

            switch (error){
                case 1:
                    mText.setText("ERROR: Network operation timed out ");
                    break;
                case 2:
                    mText.setText("ERROR: Other network related errors ");
                    break;
                case 3:
                    mText.setText("ERROR: Audio recording error ");
                    break;
                case 4:
                    mText.setText("ERROR: Server sends error status");
                    break;
                case 5:
                    mText.setText("ERROR: Other client side errors. ");
                    break;
                case 6:
                    mText.setText("ERROR: No speech input ");
                    break;
                case 7:
                    mText.setText("ERROR: No recognition result matched.");
                    break;
                case 8:
                    mText.setText("ERROR: RecognitionService busy ");
                    break;
                case 9:
                    mText.setText("ERROR: Insufficient permissions ");
                    break;
                default:
                    mText.setText("ERROR:");
                    break;
            }
        }
        public void onResults(Bundle results)
        {
            String str = new String();
            Log.d(TAG, "onResults " + results);
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
                Log.d(TAG, "result " + data.get(i));
                str += data.get(i);
            }
            mText.setText("results: "+data.get(0));
        }
        public void onPartialResults(Bundle partialResults)
        {
            ArrayList data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            mText.setText("results partial: "+data.get(0));
            Log.d(TAG, "onPartialResults");
        }
        public void onEvent(int eventType, Bundle params)
        {
            Log.d(TAG, "onEvent " + eventType);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.btnSpeech)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()/*"hi_IN"*/ /*"ja_JP"*/);
//            intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 50000000);
//            intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500);
            intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            sr.startListening(intent);

        }
    }
}

