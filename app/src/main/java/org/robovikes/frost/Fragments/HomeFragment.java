package org.robovikes.frost.Fragments;

import static android.content.Context.MODE_PRIVATE;
import static org.robovikes.frost.Fragments.Scouting.Match.MAutoData.savedCheck;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.robovikes.frost.R;
import org.robovikes.frost.Utils.SavePage;
import org.robovikes.frost.databinding.FragmentHomeBinding;
import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ConstraintLayout layout = (ConstraintLayout) root.findViewById(R.id.layoutConstraint);
        ConstraintLayout layout2 = (ConstraintLayout) root.findViewById(R.id.constraintLayout2);
        int count = layout.getChildCount();
        int count2 = layout2.getChildCount();
        TextView view = root.findViewById(R.id.textView3);
        CheckBox button = root.findViewById(R.id.radioButton);
        button.setChecked(getActivity().getPreferences(MODE_PRIVATE).getBoolean("button", false));
        view.setText(getActivity().getPreferences(MODE_PRIVATE).getString("bob", ""));
        for(Integer id : savedCheck.keySet()) {
            CheckBox box = root.findViewById(id);
            box.setChecked(savedCheck.get(id));
        }
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        View root = binding.getRoot();
        View v = root.findViewById(R.id.layoutConstraint);
        SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        CheckBox button = root.findViewById(R.id.radioButton);
        editor.putBoolean("button", button.isChecked());
        editor.apply();
        System.out.println(button.isChecked());
        binding = null;
    }
}