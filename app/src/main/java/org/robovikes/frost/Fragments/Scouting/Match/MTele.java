package org.robovikes.frost.Fragments.Scouting.Match;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.robovikes.frost.DataManager.Event;
import org.robovikes.frost.EventManager;
import org.robovikes.frost.R;
import org.robovikes.frost.Utils.SavePage;
import org.robovikes.frost.databinding.FragmentMatchTeleBinding;

import java.lang.ref.Reference;

public class MTele extends Fragment{

    private FragmentMatchTeleBinding binding;
    private int totalTeleScoreL = 0;
    private int totalTeleScoreR = 0;
    private String TOTAL_TELE_SCORE_L;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentMatchTeleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button telePlusL = root.findViewById(R.id.telePlusL);
        Button teleMinusL = root.findViewById(R.id.teleMinusL);
        Button telePlusR = root.findViewById(R.id.telePlusR);
        Button teleMinusR = root.findViewById(R.id.teleMinusR);
        Button endMatch = root.findViewById(R.id.button_end_matchScout);
        TextView teleScoreL = root.findViewById(R.id.textView_upperScoreTele);
        TextView teleScoreR = root.findViewById(R.id.textView_lowerScoreTele);
        teleScoreL.setText(String.valueOf(totalTeleScoreL));
        teleScoreR.setText(String.valueOf(totalTeleScoreR));
        SavePage.loadSave(this, (ViewGroup) root);
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
                LinearLayout layout = root.findViewById(R.id.matchTeleLayout);
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference match = db.getReference("Events/" + Event.getCurrentEvent() + "/teams/" + 1 + "/matches/" + 1);
                match.child("points").setValue(5);
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
        super.onDestroyView();
        View root = binding.getRoot();
        SavePage.saveLayout(this, (ViewGroup) root);
        binding = null;
    }
}
