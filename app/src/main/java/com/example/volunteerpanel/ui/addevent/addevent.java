package com.example.volunteerpanel.ui.addevent;
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
import com.example.volunteerpanel.R;
import com.example.volunteerpanel.databinding.FragmentSlideshowBinding;

import com.example.volunteerpanel.ConstantSp;

public class addevent extends Fragment{
    SharedPreferences sharedPreferences;
    SQLiteDatabase db;
    EditText etEventName, etEventDate, etEventLocation, etEventTime,etEventDescription;
    Button btnAddEvent;
    private FragmentSlideshowBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_event, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        db = getActivity().openOrCreateDatabase("volunteer.db", Context.MODE_PRIVATE, null);
        String user_table = "CREATE TABLE IF NOT EXISTS volunteer(v_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),email VARCHAR(100),contact VARCHAR(15),password VARCHAR(15),image BLOB);";
        db.execSQL(user_table);
        String event_table = "CREATE TABLE IF NOT EXISTS events(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),description VARCHAR(100),date VARCHAR(15),time VARCHAR(15),location VARCHAR(15));";
        db.execSQL(event_table);
        etEventName = view.findViewById(R.id.etEventName);
        etEventDate = view.findViewById(R.id.etEventDate);
        etEventLocation = view.findViewById(R.id.etEventLocation);
        etEventTime = view.findViewById(R.id.etEventTime);
        etEventDescription = view.findViewById(R.id.etEventDescription);
        btnAddEvent = view.findViewById(R.id.etEvent_button);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEventName.getText().toString().isEmpty() || etEventDate.getText().toString().isEmpty() || etEventLocation.getText().toString().isEmpty() || etEventTime.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    String eventName = etEventName.getText().toString();
                    String eventDate = etEventDate.getText().toString();
                    String eventLocation = etEventLocation.getText().toString();
                    String eventTime = etEventTime.getText().toString();
                    String eventDescription = etEventDescription.getText().toString();
                    String query = "INSERT INTO events(name,description,date,time,location) VALUES('" + eventName + "','" + eventDescription + "','" + eventDate + "','" + eventTime + "','" + eventLocation + "')";
                    db.execSQL(query);
                    etEventName.setText("");
                    etEventDate.setText("");
                    etEventLocation.setText("");
                    etEventTime.setText("");
                    etEventDescription.setText("");
                    Toast.makeText(getContext(), "Event added successfully", Toast.LENGTH_SHORT).show();
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
