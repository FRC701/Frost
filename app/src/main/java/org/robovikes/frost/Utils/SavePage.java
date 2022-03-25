package org.robovikes.frost.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SavePage {

    public static void loadSave(Fragment fragment, ViewGroup view) {
        SharedPreferences preferences = fragment.getActivity().getPreferences(MODE_PRIVATE);
        for(CheckBox box : getCheckBoxes(view)) {
            if (preferences.contains(fragment.getResources().getResourceEntryName(box.getId()))) {
                box.setChecked(preferences.getBoolean(fragment.getResources().getResourceEntryName(box.getId()), false));
            }
        }
        for(RadioButton button : getRadioButtons(view)) {
            if (preferences.contains(fragment.getResources().getResourceEntryName(button.getId()))) {
                button.setChecked(preferences.getBoolean(fragment.getResources().getResourceEntryName(button.getId()), false));
            }
        }
        for(EditText text : getEditTexts(view)) {
            if (preferences.contains(fragment.getResources().getResourceEntryName(text.getId()))) {
                text.setText(preferences.getString(fragment.getResources().getResourceEntryName(text.getId()), ""));
            }
        }
        for(SeekBar bar : getSeekBars(view)) {
            if (preferences.contains(fragment.getResources().getResourceEntryName(bar.getId()))) {
                bar.setProgress(preferences.getInt(fragment.getResources().getResourceEntryName(bar.getId()), -1));
            }
        }
    }
    public static void clearSave(Fragment fragment, ViewGroup view) {
        Activity activity = fragment.getActivity();
        SharedPreferences preferences = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for(CheckBox box : getCheckBoxes(view)) {
            String boxKey = fragment.getResources().getResourceEntryName(box.getId());
            editor.remove(boxKey);
        }
        for(RadioButton button : getRadioButtons(view)) {
            String boxKey = fragment.getResources().getResourceEntryName(button.getId());
            editor.remove(boxKey);
        }
        for(EditText editText : getEditTexts(view)) {
            String boxKey = fragment.getResources().getResourceEntryName(editText.getId());
            editor.remove(boxKey);
        }
        for(SeekBar bar : getSeekBars(view)) {
            String boxKey = fragment.getResources().getResourceEntryName(bar.getId());
            editor.remove(boxKey);
        }
        editor.apply();
    }
    public static void saveLayout(Fragment fragment, ViewGroup view) {
        SharedPreferences preferences = fragment.getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for(CheckBox box : getCheckBoxes(view)) {
            editor.putBoolean(fragment.getResources().getResourceEntryName(box.getId()), box.isChecked());
        }
        for(RadioButton button : getRadioButtons(view)) {
            editor.putBoolean(fragment.getResources().getResourceEntryName(button.getId()), button.isChecked());
        }
        for(EditText editText : getEditTexts(view)) {
            editor.putString(fragment.getResources().getResourceEntryName(editText.getId()), editText.getText().toString());
        }
        for(SeekBar bar : getSeekBars(view)) {
            editor.putInt(fragment.getResources().getResourceEntryName(bar.getId()), bar.getProgress());
        }
        editor.apply();
    }
    private static List<CheckBox> getCheckBoxes(ViewGroup view) {
        List<ViewGroup> group = getLayouts(view);
        List<CheckBox> checkBoxes = new ArrayList<>();
        for(int i = 0; i  < group.size(); i++) {
            ViewGroup layout = (ViewGroup) getLayouts(view).get(i);
            int children = layout.getChildCount();
            for(int i2 = 0; i2 < children; i2++) {
                if (layout.getChildAt(i2) instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) layout.getChildAt(i2);
                    checkBoxes.add(checkBox);
                }
            }
        }
        return checkBoxes;
    }
    private static List<RadioButton> getRadioButtons(ViewGroup view) {
        List<RadioGroup> group = getRadioGroups(view);
        List<RadioButton> radioButtons = new ArrayList<>();
        for(int i = 0; i  < group.size(); i++) {
            ViewGroup layout = (ViewGroup) getLayouts(view).get(i);
            int children = layout.getChildCount();
            for(int i2 = 0; i2 < children; i2++) {
                if (layout.getChildAt(i2) instanceof RadioButton) {
                    RadioButton button = (RadioButton) layout.getChildAt(i2);
                    radioButtons.add(button);
                    System.out.println(button + ":" + button.isActivated());
                }
            }
        }
        return radioButtons;
    }

    private static List<TextView> getTextViews(ViewGroup view) {
        List<ViewGroup> group = getLayouts(view);
        List<TextView> textViews = new ArrayList<>();
        for(int i = 0; i  < group.size(); i++) {
            ViewGroup layout = (ViewGroup) getLayouts(view).get(i);
            int children = layout.getChildCount();
            for(int i2 = 0; i2 < children; i2++) {
                if (layout.getChildAt(i2) instanceof MaterialTextView) {
                    TextView text = (TextView) layout.getChildAt(i2);
                    System.out.println(text + ":" + text.getText());
                    textViews.add(text);
                }
            }
        }
        return textViews;
    }
    private static List<SeekBar> getSeekBars(ViewGroup view) {
        List<ViewGroup> group = getLayouts(view);
        List<SeekBar> seekBars = new ArrayList<>();
        for(int i = 0; i  < group.size(); i++) {
            ViewGroup layout = (ViewGroup) getLayouts(view).get(i);
            int children = layout.getChildCount();
            for(int i2 = 0; i2 < children; i2++) {
                if (layout.getChildAt(i2) instanceof SeekBar) {
                    SeekBar seekBar = (SeekBar) layout.getChildAt(i2);
                    System.out.println(seekBar + ":" + seekBar.getProgress());
                    seekBars.add(seekBar);
                }
            }
        }
        return seekBars;
    }
    private static List<EditText> getEditTexts(ViewGroup view) {
        List<ViewGroup> group = getLayouts(view);
        List<EditText> editTexts = new ArrayList<>();
        for(int i = 0; i  < group.size(); i++) {
            ViewGroup layout = (ViewGroup) getLayouts(view).get(i);
            int children = layout.getChildCount();
            for(int i2 = 0; i2 < children; i2++) {
                if (layout.getChildAt(i2) instanceof EditText) {
                    EditText text = (EditText) layout.getChildAt(i2);
                    System.out.println(text + ":" + text.getText());
                    editTexts.add(text);
                }
            }
        }
        return editTexts;
    }
    private static List<ViewGroup> getLayouts(ViewGroup view) {
        List<ViewGroup> Layouts = new ArrayList<>();
        int children = view.getChildCount();
        for(int i = 0; i < children; i++) {
            if(view.getChildAt(i) instanceof ViewGroup) {
                if(view.getChildAt(i) instanceof ConstraintLayout || view.getChildAt(i) instanceof LinearLayout ) {
                    Layouts.add((ViewGroup) view.getChildAt(i));
                }
                ViewGroup group = (ViewGroup) view.getChildAt(i);
                for(int i2 = 0; i2 < group.getChildCount(); i2++) {
                    if (group.getChildAt(i2) instanceof ViewGroup) {
                        if(group.getChildAt(i2) instanceof ConstraintLayout || group.getChildAt(i2) instanceof LinearLayout ) {
                            Layouts.add((ViewGroup) group.getChildAt(i2));
                        }
                        ViewGroup group2 = (ViewGroup) group.getChildAt(i2);
                        for(int i3 = 0; i3 < group2.getChildCount(); i3++) {
                            if (group2.getChildAt(i3) instanceof ViewGroup) {
                                if(group2.getChildAt(i3) instanceof ConstraintLayout || group2.getChildAt(i3) instanceof LinearLayout ) {
                                    Layouts.add((ViewGroup) group2.getChildAt(i3));
                                }
                                ViewGroup group3 = (ViewGroup) group2.getChildAt(i3);
                                for(int i4 = 0; i4 < group3.getChildCount(); i4++) {
                                    if (group3.getChildAt(i4) instanceof ViewGroup) {
                                        if(group3.getChildAt(i4) instanceof ConstraintLayout || group3.getChildAt(i4) instanceof LinearLayout ) {
                                            Layouts.add((ViewGroup) group3.getChildAt(i4));
                                        }
                                        ViewGroup group4 = (ViewGroup) group3.getChildAt(i4);
                                        for(int i5 = 0; i5 < group3.getChildCount(); i5++) {
                                            if (group4.getChildAt(i5) instanceof ViewGroup) {
                                                if(group4.getChildAt(i5) instanceof ConstraintLayout || group4.getChildAt(i5) instanceof LinearLayout ) {
                                                    Layouts.add((ViewGroup) group4.getChildAt(i5));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return Layouts;
    }
    private static List<RadioGroup> getRadioGroups(ViewGroup view) {
        List<RadioGroup> Layouts = new ArrayList<>();
        int children = view.getChildCount();
        for(int i = 0; i < children; i++) {
            if(view.getChildAt(i) instanceof ViewGroup) {
                if(view.getChildAt(i) instanceof RadioGroup) {
                    Layouts.add((RadioGroup) view.getChildAt(i));
                }
                ViewGroup group = (ViewGroup) view.getChildAt(i);
                for(int i2 = 0; i2 < group.getChildCount(); i2++) {
                    if (group.getChildAt(i2) instanceof ViewGroup) {
                        if(group.getChildAt(i2) instanceof RadioGroup) {
                            Layouts.add((RadioGroup) group.getChildAt(i2));
                        }
                        ViewGroup group2 = (ViewGroup) group.getChildAt(i2);
                        for(int i3 = 0; i3 < group2.getChildCount(); i3++) {
                            if (group2.getChildAt(i3) instanceof ViewGroup) {
                                if(group2.getChildAt(i3) instanceof RadioGroup) {
                                    Layouts.add((RadioGroup) group2.getChildAt(i3));
                                }
                                ViewGroup group3 = (ViewGroup) group2.getChildAt(i3);
                                for(int i4 = 0; i4 < group3.getChildCount(); i4++) {
                                    if (group3.getChildAt(i4) instanceof ViewGroup) {
                                        if(group3.getChildAt(i4) instanceof RadioGroup) {
                                            Layouts.add((RadioGroup) group3.getChildAt(i4));
                                        }
                                        ViewGroup group4 = (ViewGroup) group3.getChildAt(i4);
                                        for(int i5 = 0; i5 < group3.getChildCount(); i5++) {
                                            if (group4.getChildAt(i5) instanceof ViewGroup) {
                                                if(group4.getChildAt(i5) instanceof RadioGroup) {
                                                    Layouts.add((RadioGroup) group4.getChildAt(i5));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return Layouts;
    }
    public static void saveData(ViewGroup view) {

    }
    public static void savePath(String path, Object value){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference(path).setValue(value);
    }
}
