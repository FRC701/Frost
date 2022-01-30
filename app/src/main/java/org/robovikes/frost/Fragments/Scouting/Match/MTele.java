package org.robovikes.frost.Fragments.Scouting.Match;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchTeleBinding;

public class MTele extends Fragment{

    private FragmentMatchTeleBinding binding;
    private int totalTeleScoreL = 0;
    private int totalTeleScoreR = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentMatchTeleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button telePlusL = root.findViewById(R.id.telePlusL);
        Button teleMinusL = root.findViewById(R.id.teleMinusL);
        Button telePlusR = root.findViewById(R.id.telePlusR);
        Button teleMinusR = root.findViewById(R.id.teleMinusR);
        TextView teleScoreL = root.findViewById(R.id.teleScoreL);
        TextView teleScoreR = root.findViewById(R.id.teleScoreR);
        teleScoreL.setText(String.valueOf(totalTeleScoreL));
        teleScoreR.setText(String.valueOf(totalTeleScoreR));

        telePlusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalTeleScoreL++;
                teleScoreL.setText(String.valueOf(totalTeleScoreL));
            }
        });

        teleMinusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalTeleScoreL > 0){
                    totalTeleScoreL--;
                    teleMinusL.setText(String.valueOf(totalTeleScoreL));
                }
            }
        });

        telePlusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalTeleScoreR++;
                telePlusR.setText(String.valueOf(totalTeleScoreR));
            }
        });

        teleMinusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalTeleScoreR > 0){
                    totalTeleScoreR--;
                    teleMinusR.setText(String.valueOf(totalTeleScoreR));
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
