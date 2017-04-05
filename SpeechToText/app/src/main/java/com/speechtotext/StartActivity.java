package com.speechtotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWODSST;
    private Button btnWDSST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnWODSST = (Button) findViewById(R.id.button2);
        btnWDSST = (Button) findViewById(R.id.button);

        btnWDSST.setOnClickListener(this);
        btnWODSST.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        
        if (v.getId() == R.id.button) {
             intent=new Intent(StartActivity.this,WithDialogSTT.class);
        } else if (v.getId() == R.id.button2) {
             intent=new Intent(StartActivity.this,WithoutDialogSTT.class);
        }
        startActivity(intent);
    }
}
