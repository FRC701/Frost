package org.robovikes.frost.Fragments.Scouting.Match;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchHomeBinding;

public class MHome extends Fragment {

    private Spinner teamSpinner;
    private FragmentMatchHomeBinding binding;
    private int match = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setUpSpinners();

        Button button = root.findViewById(R.id.startMatch);
        Button plusMatch = root.findViewById(R.id.plusMatch);
        Button minusMatch = root.findViewById(R.id.minusMatch);
        TextView matchNum = root.findViewById(R.id.matchNumber);
        matchNum.setText(String.valueOf(match));

        plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match++;
                matchNum.setText(String.valueOf(match));
            }
        });

        minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match > 1) {
                    match--;
                    matchNum.setText(String.valueOf(match));
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_auto);
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                BottomNavigationView scoutingBar = root.findViewById(R.id.scouting_bar);
                NavigationUI.setupWithNavController(scoutingBar, navController);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        }, 10);

        return root;
    }

    @Override
    public void onDestroyView() {
        setRetainInstance(true);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        super.onDestroyView();
        binding = null;
    }

    public void setUpSpinners() {
        View root = binding.getRoot();

        teamSpinner = root.findViewById(R.id.teamSpinner);

        ArrayAdapter<CharSequence> teamAdapter = ArrayAdapter.createFromResource(root.getContext(), R.array.teams, android.R.layout.simple_spinner_item);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(teamAdapter);

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             String choice = parent.getItemAtPosition(position).toString();
             Toast.makeText(binding.getRoot().getContext(), choice, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}