package com.example.volunteerpanel.ui.slideshow;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.volunteerpanel.ConstantSp;
import com.example.volunteerpanel.Login_Page;
import com.example.volunteerpanel.databinding.FragmentSlideshowBinding;
import com.example.volunteerpanel.R;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        // You can put logout logic here
        showLogoutDialog();

        return view;
    }
    private void showLogoutDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().clear().apply();
                    startActivity(new Intent(getActivity(), Login_Page.class));
                    getActivity().finish();
                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}