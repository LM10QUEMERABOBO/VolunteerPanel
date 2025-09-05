package com.example.volunteerpanel;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.volunteerpanel.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;
    TextView email,name;
    ImageView imageView;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = openOrCreateDatabase("volunteer.db", MODE_PRIVATE, null);
        String user_table="CREATE TABLE IF NOT EXISTS volunteer(v_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),email VARCHAR(100),contact VARCHAR(15),password VARCHAR(15),image BLOB);";
        db.execSQL(user_table);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_addevent,R.id.nav_showvolunteer,R.id.nav_showevent)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        sharedPreferences = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        email = findViewById(R.id.dashboard_email);
        name = findViewById(R.id.dashboard_name);
//        imageView = findViewById(R.id.imageView);
        email.setText(sharedPreferences.getString(ConstantSp.email, ""));
        name.setText(sharedPreferences.getString(ConstantSp.name, ""));
//        imageView.setImageResource(R.drawable.sample_image);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}