package com.example.volunteerpanel.ui.showvolunteer;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volunteerpanel.ConstantSp;
import com.example.volunteerpanel.R;
import com.example.volunteerpanel.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.List;

public class showvolunteer extends Fragment {
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private VolunteerAdapter adapter;
    SQLiteDatabase db;
//    private DatabaseHelper dbHelper;
    private List<Volunteer> volunteerList;
    private FragmentSlideshowBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_list, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        db = getActivity().openOrCreateDatabase("volunteer.db", Context.MODE_PRIVATE, null);
        String user_table = "CREATE TABLE IF NOT EXISTS volunteer(v_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50),email VARCHAR(100),contact VARCHAR(15),password VARCHAR(15),image BLOB);";
        db.execSQL(user_table);
        recyclerView = view.findViewById(R.id.rvVolunteers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        dbHelper = new DatabaseHelper(this);
        volunteerList = getAllVolunteers();

        adapter = new VolunteerAdapter(getContext(), volunteerList);
        recyclerView.setAdapter(adapter);
        return view;
    }
    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();\
        String path = requireContext().getDatabasePath("volunteer.db").getPath(); // Replace with your DB name
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.query("volunteer", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") Volunteer volunteer = new Volunteer(
                        cursor.getInt(cursor.getColumnIndex("v_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("contact")),
                        cursor.getString(cursor.getColumnIndex("password"))
                );
                volunteers.add(volunteer);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return volunteers;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
