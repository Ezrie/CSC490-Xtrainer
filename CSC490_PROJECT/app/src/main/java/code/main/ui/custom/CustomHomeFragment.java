package code.main.ui.custom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import code.main.R;
import code.main.data.PushPullScheduleContainer;
import code.main.data.WorkoutDataObject;
import code.main.ui.workouts.WorkoutDetailsFragment;

public class CustomHomeFragment extends Fragment {

    //Arrays that hold the title, descriptions, and routine of the workouts.
    protected WorkoutDataObject SelectedWorkout;

    //Empty constructor for fragment manager
    public CustomHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Read from local file for any custom workouts here
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        final View root = inflater.inflate(R.layout.fragment_custom_home, container, false);

        Bundle mBundle = this.getArguments();
        if (!(mBundle == null)) {

            String workoutName = mBundle.getString("selected");
            //SelectedWorkout =
            //TODO: Query database for selected workout of name workoutName.

            //temp dummy data. push day, leg day, pull day on wednesday and friday
            PushPullScheduleContainer[] schedule = new PushPullScheduleContainer[4];
            schedule[0] = new PushPullScheduleContainer(new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.WEDNESDAY, PushPullScheduleContainer.Days.FRIDAY}, PushPullScheduleContainer.ExerciseTypeEnum.PUSH);
            schedule[1] = new PushPullScheduleContainer(new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.WEDNESDAY, PushPullScheduleContainer.Days.FRIDAY}, PushPullScheduleContainer.ExerciseTypeEnum.PULL);
            schedule[2] = new PushPullScheduleContainer(new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.WEDNESDAY, PushPullScheduleContainer.Days.SATURDAY}, PushPullScheduleContainer.ExerciseTypeEnum.LEG);
            schedule[3] = new PushPullScheduleContainer(new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.WEDNESDAY, PushPullScheduleContainer.Days.MONDAY}, PushPullScheduleContainer.ExerciseTypeEnum.NONE);
            SelectedWorkout = new WorkoutDataObject(true, workoutName, "Workout description here lets see if it scrolls correctlyyyyyyyyy yyyyyyyyyyyyyyyyyyyy yyyyyyyyyyyyy y y yyyyy yy yy  y y y y yyyy yy  y y y yy  y yyyyyy ", schedule);

            final EditText Title = (EditText) root.findViewById(R.id.custom_workout_name);
            Title.setText(SelectedWorkout.getWorkoutTitle());
            Title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    SelectedWorkout.setWorkoutTitle(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            final TextView Description = (TextView) root.findViewById(R.id.custom_workout_description);
            Description.setText(SelectedWorkout.getWorkoutDescription());
            Description.setMovementMethod(new ScrollingMovementMethod());
            Description.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    SelectedWorkout.setWorkoutDescription(s.toString());
                }
            });

            ImageButton deleteButton = (ImageButton) root.findViewById(R.id.custom_delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                    //TODO: delete from db

                    //Pop back to prev. fragment
                    Objects.requireNonNull(getFragmentManager()).popBackStack();
                }
            });

            ImageButton saveButton = (ImageButton) root.findViewById(R.id.custom_save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean validName = ((TextView) root.findViewById(R.id.custom_workout_name)).getText().length() != 0;
                    if (validName) {
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

                        //TODO: save to db

                        //Go to this workout's details
                        Bundle mBundle = new Bundle();
                        mBundle.putParcelable("WorkoutObject", SelectedWorkout);

                        Fragment fragment = new WorkoutDetailsFragment();
                        fragment.setArguments(mBundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.custom_frame, fragment);
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(getContext(), "Must enter a workout name", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            final int day = 0;

            //There will be 7 recyclerViews, one per day
            RecyclerView sundayView = (RecyclerView) root.findViewById(R.id.sunday_list);
            sundayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("SUNDAY"), this.getFragmentManager()));

            RecyclerView mondayView = (RecyclerView) root.findViewById(R.id.monday_list);
            mondayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("MONDAY"), this.getFragmentManager()));

            RecyclerView tuesdayView = (RecyclerView) root.findViewById(R.id.tuesday_list);
            tuesdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("TUESDAY"), this.getFragmentManager()));

            RecyclerView wednesdayView = (RecyclerView) root.findViewById(R.id.wednesday_list);
            wednesdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("WEDNESDAY"), this.getFragmentManager()));

            RecyclerView thursdayView = (RecyclerView) root.findViewById(R.id.thursday_list);
            thursdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("THURSDAY"), this.getFragmentManager()));

            RecyclerView fridayView = (RecyclerView) root.findViewById(R.id.friday_list);
            fridayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("FRIDAY"), this.getFragmentManager()));

            RecyclerView saturdayView = (RecyclerView) root.findViewById(R.id.saturday_list);
            saturdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("SATURDAY"), this.getFragmentManager()));

            sundayView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int day;
                    //Switch case for 7 possible days
                    switch (v.getId()) {
                        case R.id.sunday_item:
                            day = 0;
                        case R.id.monday_item:
                            day = 1;
                        case R.id.tuesday_item:
                            day = 2;
                        case R.id.wednesday_item:
                            day = 3;
                        case R.id.thursday_item:
                            day = 4;
                        case R.id.friday_item:
                            day = 5;
                        case R.id.saturday_item:
                            day = 6;
                        default:
                            day = -1;
                    }
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("Day", day);
                    mBundle.putParcelable("WorkoutObject", SelectedWorkout);

                    Fragment fragment = new CustomSelectFragment();
                    fragment.setArguments(mBundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.custom_frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }

        return root;
    }
}

class CustomHomeCustomAdapter extends RecyclerView.Adapter<CustomHomeCustomAdapter.ViewHolder> {

    private String[] Schedule;
    private FragmentManager Manager;

    public CustomHomeCustomAdapter(String[] schedule, FragmentManager manager) {
        Schedule = schedule;
        Manager = manager;
    }

    @NonNull
    @Override
    public CustomHomeCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom, parent, false);
        return new CustomHomeCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final CustomHomeCustomAdapter.ViewHolder holder, int position) {
        holder.mButton.setText(Schedule[position]);
    }

    @Override
    public int getItemCount() {
        return Schedule.length;
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;
        public final View mView;

        public ViewHolder(View view, final FragmentManager manager) {
            super(view);
            mButton = (Button) view.findViewById(R.id.custom_schedule_item);
            mView = view;


        }
    }
}