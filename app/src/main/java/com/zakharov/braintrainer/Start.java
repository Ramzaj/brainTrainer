package com.zakharov.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 *  @author Alex Zakharov, 2018/03/06
 */

public class Start extends AppCompatActivity {

    Button startButton;

    public void start(View view) {
        startActivity(new Intent(getApplicationContext(), Main.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = (Button) findViewById(R.id.startButton);
    }

}
