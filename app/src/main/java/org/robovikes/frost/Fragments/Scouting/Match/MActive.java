package org.robovikes.frost.Fragments.Scouting.Match;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchActiveBinding;

public class MActive  extends Fragment{

    private Spinner teamSpinner;
    private FragmentMatchActiveBinding binding;
    private int match = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchActiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        minusMatch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(match > 1){
                    match--;
                    matchNum.setText(String.valueOf(match));
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_pit_active);
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                BottomNavigationView scoutingBar = root.findViewById(R.id.scouting_bar);}
            }, 10);

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
