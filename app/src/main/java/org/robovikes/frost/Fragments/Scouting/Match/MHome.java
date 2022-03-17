package org.robovikes.frost.Fragments.Scouting.Match;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchHomeBinding;

import java.util.ArrayList;

public class MHome extends Fragment {

    private Spinner teamSpinner;
    private FragmentMatchHomeBinding binding;
    private int match = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setUpSpinners();

        Button button = root.findViewById(R.id.button_start_match_scouting);
        Button plusMatch = root.findViewById(R.id.plusMatch);
        Button minusMatch = root.findViewById(R.id.minusMatch);
        TextView matchNum = root.findViewById(R.id.textView_matchNumber);
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
            }
        }, 10);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setUpSpinners() {
        View root = binding.getRoot();

        teamSpinner = root.findViewById(R.id.teamSpinner);
        ArrayList<Integer> teams = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Events/teams");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot rawSnapshot) {
                for (DataSnapshot snapshot : rawSnapshot.getChildren()) {
                    System.out.println(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<Integer> teamAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, teams);
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