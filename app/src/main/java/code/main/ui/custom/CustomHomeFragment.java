package code.main.ui.custom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import code.main.R;
import code.main.data.WorkoutDataObject;
import code.main.ui.workouts.WorkoutGroupFragment;

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
        View root = inflater.inflate(R.layout.fragment_custom_home, container, false);

        Bundle mBundle = this.getArguments();
        if (!(mBundle == null)) {

            SelectedWorkout = mBundle.getParcelable("WorkoutObject");

            EditText Title = (EditText) root.findViewById(R.id.custom_workout_name);
            Title.setText(SelectedWorkout.getWorkoutTitle());

            TextView Type = (TextView) root.findViewById(R.id.workout_details_type);
            Type.setText(SelectedWorkout.getWorkoutType());

            TextView Description = (TextView) root.findViewById(R.id.workout_details_description);
            Description.setText(SelectedWorkout.getWorkoutDescription());
            Description.setMovementMethod(new ScrollingMovementMethod());

            //There will be 7 recyclerViews, one per day
            RecyclerView sundayView = (RecyclerView) root.findViewById(R.id.sunday_list);
            sundayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("SUNDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView mondayView = (RecyclerView) root.findViewById(R.id.monday_list);
            mondayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("MONDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView tuesdayView = (RecyclerView) root.findViewById(R.id.tuesday_list);
            tuesdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("TUESDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView wednesdayView = (RecyclerView) root.findViewById(R.id.wednesday_list);
            wednesdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("WEDNESDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView thursdayView = (RecyclerView) root.findViewById(R.id.thursday_list);
            thursdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("THURSDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView fridayView = (RecyclerView) root.findViewById(R.id.friday_list);
            fridayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("FRIDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));

            RecyclerView saturdayView = (RecyclerView) root.findViewById(R.id.saturday_list);
            saturdayView.setAdapter(new CustomHomeCustomAdapter(SelectedWorkout.getScheduleByDay("SATURDAY"), SelectedWorkout.isPushPull(), this.getFragmentManager()));
        }

        return root;
    }
}

class CustomHomeCustomAdapter extends RecyclerView.Adapter<CustomHomeCustomAdapter.ViewHolder> {

    private String[] Schedule;
    private Boolean isPushPull;
    private FragmentManager Manager;

    public CustomHomeCustomAdapter(String[] schedule, Boolean mIsPushPull, FragmentManager manager) {
        Schedule = schedule;
        isPushPull = mIsPushPull;
        Manager = manager;
    }

    @NonNull
    @Override
    public CustomHomeCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_schedule, parent, false);
        return new CustomHomeCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final CustomHomeCustomAdapter.ViewHolder holder, int position) {
        holder.mButton.setText(Schedule[position]);
        holder.mGroupName = Schedule[position];
        holder.mIsPushPull = isPushPull;
    }

    @Override
    public int getItemCount() {
        return Schedule.length;
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private final Button mButton;
        private String mGroupName;
        private Boolean mIsPushPull;

        public ViewHolder(View view, final FragmentManager manager) {
            super(view);
            mView = view;
            mButton = (Button) view.findViewById(R.id.schedule_item);
            View.OnClickListener mListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == mButton.getId()) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("GroupName", mGroupName);
                        mBundle.putBoolean("IsPushPull", mIsPushPull);

                        Fragment fragment = new WorkoutGroupFragment();
                        fragment.setArguments(mBundle);
                        FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.details_frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            };
            //Button click goes to WorkoutGroupFragment
            mButton.setOnClickListener(mListener);
        }
    }
}