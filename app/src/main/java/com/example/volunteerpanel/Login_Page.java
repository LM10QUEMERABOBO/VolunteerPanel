package com.example.volunteerpanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Page extends AppCompatActivity {

    EditText login_email,login_password;

    TextView forget_password,already_account;

    Button login_button;

    SharedPreferences sharedPreferences;

    SQLiteDatabase db;

    String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    String get_email,get_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        sharedPreferences = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        db= openOrCreateDatabase("volunteer.db",MODE_PRIVATE,null);
        String user_table="CREATE TABLE IF NOT EXISTS volunteer(v_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),email VARCHAR(100),contact VARCHAR(15),password VARCHAR(15),image BLOB);";
        db.execSQL(user_table);

        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        forget_password=findViewById(R.id.forget_password);
        already_account=findViewById(R.id.already_account);
        login_button=findViewById(R.id.login_button);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Page.this, Forget_Password.class);
                startActivity(intent);
            }
        });

        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login_Page.this, SignUp_Page.class);
//                startActivity(intent);
                Toast.makeText(Login_Page.this, "Can't make new admin account!!", Toast.LENGTH_LONG).show();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_email = login_email.getText().toString().trim();
                get_password = login_password.getText().toString().trim();

                if(get_email.isEmpty() || get_password.isEmpty()){
                    Toast.makeText(Login_Page.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (get_password.length()<6) {
                    login_password.setError("Minimum 6 Characters");
                    } else{

                        if(get_email.equals("admin@git.event") && get_password.equals("admin@1234")){
                            sharedPreferences.edit().putString(ConstantSp.name, "Admin").commit();
                            sharedPreferences.edit().putString(ConstantSp.email, get_email).commit();
                            sharedPreferences.edit().putString(ConstantSp.password, get_password).commit();
                            Toast.makeText(Login_Page.this, "Login Successfully!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_Page.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login_Page.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });
    }
}