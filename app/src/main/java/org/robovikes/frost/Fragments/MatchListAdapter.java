package org.robovikes.frost.Fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.robovikes.frost.R;

import java.util.ArrayList;
import java.util.Random;

public class MatchListAdapter extends ArrayAdapter{

    public Activity context;
    private ArrayList<String> blueTeamNumbers;
    private ArrayList<String> redTeamNumbers;

    public MatchListAdapter(Activity context, ArrayList<String> blueTeamNumbers, ArrayList redTeamNumbers){
        super(context, R.layout.match_layout, blueTeamNumbers);
        this.context = context;
        this.blueTeamNumbers = blueTeamNumbers;
        this.redTeamNumbers = redTeamNumbers;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView == null){
            row = inflater.inflate(R.layout.match_layout, null, true);
            TextView blue = row.findViewById(R.id.blue1);
            TextView red = row.findViewById(R.id.red1);
            TextView score = row.findViewById(R.id.score);
            TextView match = row.findViewById(R.id.match);
            ProgressBar imageFlag = row.findViewById(R.id.progressBar);
            int blueScore;
            int redScore;
            double totalScore;
            double bluePercent;
            int finalScore;
            blueScore = new Random().nextInt(100) + 1;
            redScore = new Random().nextInt(100) + 1;
            totalScore = redScore + blueScore + 0.0;
            bluePercent = blueScore / totalScore;
            finalScore = (int) (bluePercent * 1000);
            score.setText(blueScore + " - " + redScore);
            imageFlag.setProgress(finalScore);
            blue.setText(blueTeamNumbers.get(position));
            red.setText(redTeamNumbers.get(position));
            match.setText("Match" + (position + 1));
        }
        return row;
    }
}
