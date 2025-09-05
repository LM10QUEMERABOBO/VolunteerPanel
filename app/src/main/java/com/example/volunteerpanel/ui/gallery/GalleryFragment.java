package com.example.volunteerpanel.ui.gallery;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.volunteerpanel.ConstantSp;
import com.example.volunteerpanel.R;
import com.example.volunteerpanel.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    SharedPreferences sharedPreferences;
    EditText etName, etEmail, etPhone,etpass;
    Button btnAddEvent;
    SQLiteDatabase db;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_volunteer, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        db = getActivity().openOrCreateDatabase("volunteer.db", Context.MODE_PRIVATE, null);
        String user_table = "CREATE TABLE IF NOT EXISTS volunteer(v_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),email VARCHAR(100),contact VARCHAR(15),password VARCHAR(15),image BLOB);";
        db.execSQL(user_table);
        etName = view.findViewById(R.id.volunteer_name);
        etEmail = view.findViewById(R.id.volunteer_email);
        etPhone = view.findViewById(R.id.volunteer_contact);
        etpass = view.findViewById(R.id.volunteer_password);
        btnAddEvent = view.findViewById(R.id.volunteer_button);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etpass.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                if(etPhone.getText().toString().length()!=10){
                    etPhone.setError("Invalid phone number");
                }
                if(etpass.getText().toString().length()<6){
                    etpass.setError("Password must be at least 6 characters");
                }
                if(!etEmail.getText().toString().contains("@")){
                    etEmail.setError("Invalid email");
                }
                if(!etEmail.getText().toString().contains(".")){
                    etEmail.setError("Invalid email");
                }
                else {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String contact = etPhone.getText().toString();
                    String password = etpass.getText().toString();
                    String query = "INSERT INTO volunteer(name,email,contact,password) VALUES('" + name + "','" + email + "','" + contact + "','" + password + "')";
                    db.execSQL(query);
                    Toast.makeText(getContext(), "Volunteer added successfully", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etEmail.setText("");
                    etPhone.setText("");
                    etpass.setText("");
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}