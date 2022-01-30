package org.robovikes.frost.Fragments.Scouting.Match;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchAutoBinding;

public class Auto extends Fragment{

    private FragmentMatchAutoBinding binding;
    private int scoreL = 0;
    private int scoreR = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentMatchAutoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button autoPlusL = root.findViewById(R.id.autoPlusL);
        Button autoMinusL = root.findViewById(R.id.autoMinusL);
        Button autoPlusR = root.findViewById(R.id.autoPlusR);
        Button autoMinusR = root.findViewById(R.id.autoMinusR);
        TextView autoScoreL = root.findViewById(R.id.autoScoreL);
        TextView autoScoreR = root.findViewById(R.id.autoScoreR);
        autoScoreL.setText(String.valueOf(scoreL));
        autoScoreR.setText(String.valueOf(scoreR));

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
