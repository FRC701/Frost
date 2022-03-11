package org.robovikes.frost.Fragments.Create;

import android.content.pm.ActivityInfo;
import android.media.metrics.Event;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentCreateEventsBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateEvents extends Fragment {
    private Spinner eventSpinner;

    private FragmentCreateEventsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinnerSetup();



        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                BottomNavigationView eventsTeamsBar = root.findViewById(R.id.create_bar);
                NavigationUI.setupWithNavController(eventsTeamsBar, navController);

            }
        }, 10);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void spinnerSetup(){
        View root = binding.getRoot();

        eventSpinner = root.findViewById(R.id.eventName);

        ArrayAdapter<CharSequence> eventAdapter = ArrayAdapter.createFromResource(root.getContext(), R.array.events, android.R.layout.simple_spinner_item);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(eventAdapter);

        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(binding.getRoot().getContext(), choice, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}