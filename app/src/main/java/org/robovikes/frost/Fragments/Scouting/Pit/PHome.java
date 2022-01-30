package org.robovikes.frost.Fragments.Scouting.Pit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Button;

import org.robovikes.frost.R;
import org.robovikes.frost.databinding.FragmentPitHomeBinding;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class PHome extends Fragment {

    private FragmentPitHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPitHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button = root.findViewById(R.id.startPit);

        return root;

    button.setOnClickListener(new View.OnClickListener()){
        @Override
        public void onClick(View view) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_pit_active);

        }
    });
    }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}