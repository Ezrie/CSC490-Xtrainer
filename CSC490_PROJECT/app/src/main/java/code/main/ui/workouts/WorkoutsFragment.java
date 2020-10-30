package code.main.ui.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import code.main.R;

public class WorkoutsFragment extends Fragment {

    private WorkoutView WorkoutView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        WorkoutView = ViewModelProviders.of(this).get(WorkoutView.class);
        View root = inflater.inflate(R.layout.activity_workouts, container, false);
        //Finds the text container
        //final TextView textView = root.findViewById(R.id.text_workouts);

        //Default text
        //textView.setText("Workouts here");

        //Updates View if text is changed
        WorkoutView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}