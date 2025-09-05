package com.example.volunteerpanel.ui.showevent;

import androidx.fragment.app.Fragment;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
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

import com.example.volunteerpanel.R;
import com.example.volunteerpanel.databinding.FragmentSlideshowBinding;

import com.example.volunteerpanel.ConstantSp;

import java.util.ArrayList;
import java.util.List;

public class showevent extends Fragment {
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<Event> eventList;
    private FragmentSlideshowBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_event_list, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.rvEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        dbHelper = new DatabaseHelper(this);
        eventList = getAllEvents();

        adapter = new EventAdapter(getContext(), eventList);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @SuppressLint("Range")
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String path = requireContext().getDatabasePath("volunteer.db").getPath(); // Replace with your DB name
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.query("events", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("date")),
                        cursor.getString(cursor.getColumnIndex("time")),
                        cursor.getString(cursor.getColumnIndex("location"))
                );
                events.add(event);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();
        return events;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
