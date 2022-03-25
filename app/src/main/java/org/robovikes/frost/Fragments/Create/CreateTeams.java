package org.robovikes.frost.Fragments.Create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentCreateTeamsBinding;

public class CreateTeams extends Fragment {

    private FragmentCreateTeamsBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateTeamsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button createEvent = root.findViewById(R.id.createTeam);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teamNumber = root.findViewById(R.id.teamNumber);
                if (!teamNumber.getText().toString().equals("")) {
                    int number = Integer.parseInt(teamNumber.getText().toString().trim());
                    createTeam(number);
                    teamNumber.setText(null);
                } else {
                    if (teamNumber.getText().toString().equals("")) {
                        teamNumber.setError("This field is required");
                    }
                }
            }
        });
        return root;
    }
    public void createTeam(int team) {
        database.getReference("Events/UCD/teams/" + team + "/bpa").setValue(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}