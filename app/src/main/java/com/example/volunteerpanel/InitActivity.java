package com.example.volunteerpanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
//import com.example.volunteerpanel.R;

public class InitActivity extends AppCompatActivity {


    ImageView image;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        sharedPreferences = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        image = findViewById(R.id.splash_image);

        Glide.with(this).asGif()
                .load("https://i.pinimg.com/originals/fe/a9/2f/fea92f50aa6db93e3a5e9ae9aa27b2a7.gif")
                .placeholder(R.mipmap.ic_launcher)
                .into(image);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sharedPreferences.getString(ConstantSp.name,"").equals("")){
                    Intent intent = new Intent(InitActivity.this, Login_Page.class);
                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(InitActivity.this, MainActivity2.class);
                    startActivity(intent);
                }


            }
        }, 3000);

    }
}