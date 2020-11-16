package code.main.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import code.main.R;
import code.main.ui.workouts.WorkoutView;

public class SettingsFragment extends Fragment {

    private WorkoutView WorkoutView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WorkoutView = ViewModelProviders.of(this).get(WorkoutView.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        //final TextView textView = root.findViewById(R.id.text_settings);

        //textView.setText("Settings To Be Added");

        /*
        WorkoutView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
        return root;
    }
}