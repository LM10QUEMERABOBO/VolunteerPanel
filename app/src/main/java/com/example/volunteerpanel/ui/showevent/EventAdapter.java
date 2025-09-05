package com.example.volunteerpanel.ui.showevent;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.volunteerpanel.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<Event> eventList;
//    private DatabaseHelper dbHelper;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
//        this.dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvName.setText(event.getName());
        holder.tvDate.setText(event.getDate());
        holder.tvLocation.setText(event.getLocation());

        holder.btnDeleteEvent.setOnClickListener(v -> {
          deleteEvent(event.getId());
            eventList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Event deleted", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvLocation;
        Button btnDeleteEvent;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvEventDate);
            tvLocation = itemView.findViewById(R.id.tvEventLocation);
            btnDeleteEvent = itemView.findViewById(R.id.btnDeleteEvent);
        }
    }
    public void deleteEvent(long volunteerId) {
        SQLiteDatabase db = context.openOrCreateDatabase("volunteer.db", MODE_PRIVATE, null);
        db.delete("events", "id" + " = ?", new String[]{String.valueOf(volunteerId)});
        Toast.makeText(context, "event deleted successfully", Toast.LENGTH_LONG).show();
        db.close();
    }
}
