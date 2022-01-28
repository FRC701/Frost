package org.robovikes.frost.Fragments.Scouting.Pit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.robovikes.frost.databinding.FragmentGalleryBinding;
import org.robovikes.frost.databinding.FragmentPitActiveBinding;
import org.robovikes.frost.databinding.FragmentPitHomeBinding;

public class Active extends Fragment {

    private FragmentPitActiveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPitActiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}