package org.robovikes.frost.Fragments.Scouting.Match;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentMatchTeleBinding;

import java.text.BreakIterator;

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
        Button endMatch = root.findViewById(R.id.endMatch);
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
                    teleScoreL.setText(String.valueOf(totalTeleScoreL));
                }
            }
        });

        telePlusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalTeleScoreR++;
                teleScoreR.setText(String.valueOf(totalTeleScoreR));
            }
        });

        teleMinusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalTeleScoreR > 0){
                    totalTeleScoreR--;
                    teleScoreR.setText(String.valueOf(totalTeleScoreR));
                }
            }
        });

        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_home);
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                BottomNavigationView teleAutoBar = root.findViewById(R.id.tele_auto_bar);
                NavigationUI.setupWithNavController(teleAutoBar, navController);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        }, 10);


        return root;
    }


    @Override
    public void onDestroyView(){
        setRetainInstance(true);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        super.onDestroyView();
        binding = null;
    }


}
