package org.robovikes.frost.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchesBinding;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    private FragmentMatchesBinding binding;
    private ArrayList<String> redTeamNumbers = new ArrayList<>();
    private ArrayList<String> blueTeamNumbers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        redTeamNumbers.add("1");
        redTeamNumbers.add("2");
        redTeamNumbers.add("3");
        redTeamNumbers.add("4");

        blueTeamNumbers.add("1");
        blueTeamNumbers.add("2");
        blueTeamNumbers.add("3");
        blueTeamNumbers.add("4");

        ListView listView = root.findViewById(R.id.matchList);
        MatchListAdapter customTeamList = new MatchListAdapter(getActivity(), blueTeamNumbers, redTeamNumbers);
        listView.setAdapter(customTeamList);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}