package com.example.volunteerpanel.ui.showvolunteer;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

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

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private Context context;
    private List<Volunteer> volunteerList;
//    private DatabaseHelper dbHelper;

    public VolunteerAdapter(Context context, List<Volunteer> volunteerList) {
        this.context = context;
        this.volunteerList = volunteerList;
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_volunteer, parent, false);
        return new VolunteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        Volunteer volunteer = volunteerList.get(position);
        holder.tvName.setText(volunteer.getName());
        holder.tvEmail.setText(volunteer.getEmail());
        holder.tvPhone.setText(volunteer.getPhone());
        holder.tvCollege.setText(volunteer.getCollegeName());

        holder.btnRemoveVolunteer.setOnClickListener(v -> {
          deleteVolunteer(volunteer.getId());
            volunteerList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return volunteerList.size();
    }

    public static class VolunteerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvPhone, tvCollege;

        Button btnRemoveVolunteer;

        public VolunteerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvVolunteerName);
            tvEmail = itemView.findViewById(R.id.tvVolunteerEmail);
            tvPhone = itemView.findViewById(R.id.tvVolunteerPhone);
            tvCollege = itemView.findViewById(R.id.tvVolunteerCollege);
            btnRemoveVolunteer = itemView.findViewById(R.id.btnRemoveVolunteer);
        }
    }
    public void deleteVolunteer(long volunteerId) {
        SQLiteDatabase db = context.openOrCreateDatabase("volunteer.db", MODE_PRIVATE, null);
        db.delete("volunteer", "v_id" + " = ?", new String[]{String.valueOf(volunteerId)});
        Toast.makeText(context, "Volunteer deleted successfully", Toast.LENGTH_LONG).show();
        db.close();
    }
}
