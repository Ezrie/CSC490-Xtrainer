package code.main.ui.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import code.main.R;

public class WorkoutExerciseFragment extends Fragment {

    protected String groupName;
    protected String exerciseName;
    protected String exerciseDescription;

    //Empty constructor for fragment manager
    public WorkoutExerciseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_exercise, container, false);

        Bundle mBundle = this.getArguments();
        if(!this.getArguments().isEmpty()) {

            groupName = mBundle.getString("GroupName");
            exerciseName = mBundle.getString("ExerciseName");

            //TODO: Pull exercise data from database.

            //TODO: Set images and videos (set visibility).

            //temp dummy data
            exerciseDescription = "Exercise description here";

            TextView Title = (TextView) root.findViewById(R.id.exercise_header);
            Title.setText((groupName + " Exercises"));

            TextView Name = (TextView) root.findViewById(R.id.exercise_name);
            Name.setText(exerciseName);

            TextView Description = (TextView) root.findViewById(R.id.exercise_description);
            Description.setText(exerciseDescription);
        }
        return root;
    }
}