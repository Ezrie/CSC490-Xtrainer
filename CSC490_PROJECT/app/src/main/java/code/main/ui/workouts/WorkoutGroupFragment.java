package code.main.ui.workouts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import code.main.R;

public class WorkoutGroupFragment extends Fragment {

    protected String groupName;
    protected Boolean isPushPull;
    protected String[] exerciseNames;
    protected double[] exerciseWeights;

    //Empty constructor for fragment manager
    public WorkoutGroupFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_workout_groups, container, false);

        Bundle mBundle = this.getArguments();
        if (!this.getArguments().isEmpty()) {

            groupName = mBundle.getString("GroupName");
            isPushPull = mBundle.getBoolean("IsPushPull");

            //TODO: Pull exercise list & weights from database.

            //temp dummy data
            exerciseNames = new String[4];
            exerciseNames[0] = "EXERCISE 1";
            exerciseNames[1] = "EXERCISE 2";
            exerciseNames[2] = "EXERCISE 3";
            exerciseNames[3] = "EXERCISE 4";

            exerciseWeights = new double[exerciseNames.length];
            exerciseWeights[0] = 2;
            exerciseWeights[1] = 8.3;
            exerciseWeights[2] = 2.001;
            exerciseWeights[3] = .0002;

            TextView Title = (TextView) root.findViewById(R.id.group_header);
            Title.setText((groupName + " Exercises"));
        }
        RecyclerView recyclerView = root.findViewById(R.id.group_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new GroupCustomAdapter(groupName, exerciseNames, exerciseWeights, this.getFragmentManager()));
        return root;
    }

    //TODO: fix keyboard not closing on pause.
    @Override
    public void onPause() {
        super.onPause();
        this.getView().clearFocus();
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
    }
}

class GroupCustomAdapter extends RecyclerView.Adapter<GroupCustomAdapter.ViewHolder> {

    private String groupName;
    private String[] exerciseNames;
    private double[] exerciseWeights;
    private FragmentManager Manager;

    public GroupCustomAdapter(String gName, String[] names, double[] weights, FragmentManager manager) {
        groupName = gName;
        exerciseNames = names;
        exerciseWeights = weights;
        Manager = manager;
    }

    @NonNull
    @Override
    public GroupCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list, parent, false);
        return new GroupCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final GroupCustomAdapter.ViewHolder holder, int position) {
        holder.groupName = groupName;
        holder.mName.setText(exerciseNames[position]);
        holder.mWeight.setText(String.valueOf(exerciseWeights[position]));

        //Allows for overriding the scrolling of the exercise list to scroll the title text
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean isTooLong = ((TextView) v).getLineCount() * ((TextView) v).getLineHeight() > v.getHeight();
                if (event.getAction() == MotionEvent.ACTION_MOVE && isTooLong) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        };
        holder.mName.setOnTouchListener(listener);
    }

    @Override
    public int getItemCount() {
        return exerciseNames.length;
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView mName;
        private EditText mWeight;
        private String groupName;

        public ViewHolder(View view, final FragmentManager manager) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.group_name);
            mWeight = (EditText) view.findViewById(R.id.group_input);
            View.OnClickListener mListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == mName.getId()) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("ExerciseName", mName.getText().toString());
                        mBundle.putString("GroupName", groupName);

                        Fragment fragment = new WorkoutExerciseFragment();
                        fragment.setArguments(mBundle);
                        FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.group_frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            };
            //Button click goes to WorkoutExerciseFragment
            mName.setOnClickListener(mListener);
        }
    }
}
