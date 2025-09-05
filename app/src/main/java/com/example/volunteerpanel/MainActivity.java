package com.example.volunteerpanel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.volunteerpanel.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        textView = findViewById(R.id.main_title);
        textView.setText("Welcome "+sharedPreferences.getString(ConstantSp.name, "")+" !");
    }
}