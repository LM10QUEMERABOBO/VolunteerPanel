package com.example.volunteerpanel.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.volunteerpanel.ConstantSp;
import com.example.volunteerpanel.R;
import com.example.volunteerpanel.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    SharedPreferences sharedPreferences;
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        sharedPreferences = getActivity().getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        textView = view.findViewById(R.id.main_title);
        textView.setText("Welcome "+sharedPreferences.getString(ConstantSp.name,"")+" !");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}