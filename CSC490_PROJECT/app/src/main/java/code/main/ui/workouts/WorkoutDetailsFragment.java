package code.main.ui.workouts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import code.main.R;
import code.main.data.WorkoutDataObject;

public class WorkoutDetailsFragment extends Fragment {

    //Arrays that hold the title, descriptions, and routine of the workouts.
    protected WorkoutDataObject SelectedWorkout;

    //Empty constructor for fragment manager
    public WorkoutDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Read from local file for any custom workouts here
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("VERBOSE", "Details is created");
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_workout_details, container, false);

        Bundle mBundle = this.getArguments();
        if(!this.getArguments().isEmpty()) {

            SelectedWorkout = mBundle.getParcelable("WorkoutObject");

            TextView Title = (TextView) root.findViewById(R.id.workout_details_name);
            Title.setText(SelectedWorkout.getWorkoutTitle());

            TextView Type = (TextView) root.findViewById(R.id.workout_details_type);
            Type.setText(SelectedWorkout.getWorkoutType());

            TextView Description = (TextView) root.findViewById(R.id.workout_details_description);
            Description.setText(SelectedWorkout.getWorkoutDescription());
            Description.setMovementMethod(new ScrollingMovementMethod());

            //temp dummy data
            String[] temp = new String[1];
            temp[0] = "LEG DAY";


            //There will be 7 recyclerViews, one per day
            RecyclerView sundayView = (RecyclerView) root.findViewById(R.id.sunday_list);
            sundayView.setAdapter(new DetailsCustomAdapter(temp, this.getFragmentManager()));

            RecyclerView mondayView = (RecyclerView) root.findViewById(R.id.monday_list);
            mondayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("MONDAY"), this.getFragmentManager()));

            RecyclerView tuesdayView = (RecyclerView) root.findViewById(R.id.tuesday_list);
            tuesdayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("TUESDAY"), this.getFragmentManager()));

            RecyclerView wednesdayView = (RecyclerView) root.findViewById(R.id.wednesday_list);
            wednesdayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("WEDNESDAY"), this.getFragmentManager()));

            RecyclerView thursdayView = (RecyclerView) root.findViewById(R.id.thursday_list);
            thursdayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("THURSDAY"), this.getFragmentManager()));

            RecyclerView fridayView = (RecyclerView) root.findViewById(R.id.friday_list);
            fridayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("FRIDAY"), this.getFragmentManager()));

            RecyclerView saturdayView = (RecyclerView) root.findViewById(R.id.saturday_list);
            saturdayView.setAdapter(new DetailsCustomAdapter(SelectedWorkout.getScheduleByDay("SATURDAY"), this.getFragmentManager()));
        }

        return root;
    }
}

class DetailsCustomAdapter extends RecyclerView.Adapter<DetailsCustomAdapter.ViewHolder> {

    private String[] Schedule;
    private FragmentManager Manager;

    public DetailsCustomAdapter(String[] schedule, FragmentManager manager) {
        Schedule = schedule;
        Manager = manager;
    }

    @NonNull
    @Override
    public DetailsCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_list, parent, false);
        return new DetailsCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final DetailsCustomAdapter.ViewHolder holder, int position) {
        holder.mGroup = Schedule[position];
    }

    @Override
    public int getItemCount() {
        return Schedule.length;
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        private final Button mButton;
        private String mGroup;

        public ViewHolder(View view, FragmentManager manager) {
            super(view);
            mView = view;
            mButton = (Button) view.findViewById(R.id.schedule_item);
            mButton.setText(mGroup);
            View.OnClickListener mListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //All touch events for workouts_list.xml
                    if (v.getId() == mButton.getId()) {
                        Toast.makeText(v.getContext(), "BUTTON PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    }
                }
            };
            //Button click goes to WorkoutGroupFragment
            mButton.setOnClickListener(mListener);
        }
    }
}