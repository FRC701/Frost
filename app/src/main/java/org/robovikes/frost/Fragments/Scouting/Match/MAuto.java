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

public class MAuto extends Fragment{

    private FragmentMatchAutoBinding binding;
    private int totalAutoScoreL = 0;
    private int totalAutoScoreR = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentMatchAutoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button autoPlusL = root.findViewById(R.id.autoPlusL);
        Button autoMinusL = root.findViewById(R.id.autoMinusL);
        Button autoPlusR = root.findViewById(R.id.autoPlusR);
        Button autoMinusR = root.findViewById(R.id.autoMinusR);
        TextView autoScoreL = root.findViewById(R.id.autoScoreL);
        TextView autoScoreR = root.findViewById(R.id.autoScoreR);
        autoScoreL.setText(String.valueOf(totalAutoScoreL));
        autoScoreR.setText(String.valueOf(totalAutoScoreR));

        autoPlusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAutoScoreL++;
                autoScoreL.setText(String.valueOf(totalAutoScoreL));
            }
        });

        autoMinusL.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(totalAutoScoreL > 0);{
                 totalAutoScoreL--;
                 autoScoreL.setText(String.valueOf(totalAutoScoreL));
                }
            }
        }));

        autoPlusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAutoScoreR++;
                autoScoreR.setText(String.valueOf(totalAutoScoreR));
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
