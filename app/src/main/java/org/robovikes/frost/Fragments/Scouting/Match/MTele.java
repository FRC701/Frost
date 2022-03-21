package org.robovikes.frost.Fragments.Scouting.Match;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentActivity;
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
    protected FragmentActivity Activity;
    private int totalTeleScoreL = 0;
    private int totalTeleScoreR = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            Activity = (FragmentActivity) context;
        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentMatchTeleBinding.inflate(inflater, container, false);
        ViewGroup root = binding.getRoot();
        Button telePlusL = root.findViewById(R.id.telePlusL);
        Button teleMinusL = root.findViewById(R.id.teleMinusL);
        Button telePlusR = root.findViewById(R.id.telePlusR);
        Button teleMinusR = root.findViewById(R.id.teleMinusR);
        Button endMatch = root.findViewById(R.id.button_end_matchScout);
        TextView teleScoreL = root.findViewById(R.id.textView_upperScoreTele);
        TextView teleScoreR = root.findViewById(R.id.textView_lowerScoreTele);
        teleScoreL.setText(String.valueOf(totalTeleScoreL));
        teleScoreR.setText(String.valueOf(totalTeleScoreR));
        SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
        totalTeleScoreR = preferences.getInt("TeleLowerScore", -1);
        totalTeleScoreL = preferences.getInt("TeleUpperScore", -1);
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
                SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("TeleUpperScore");
                editor.remove("TeleLowerScore");
                editor.remove("AutoUpperScore");
                editor.remove("AutoLowerScore");
                editor.apply();
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
    public void onPause(){
        super.onPause();
        Activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("TeleUpperScore", totalTeleScoreL);
        editor.putInt("TeleLowerScore", totalTeleScoreR);
        editor.apply();
        binding = null;
    }
}
