package code.main.ui.workouts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import code.main.R;
import code.main.data.IsolationScheduleContainer;
import code.main.data.MuscleDataObject;
import code.main.data.WorkoutDataObject;

public class WorkoutHomeFragment extends Fragment {

    //Arrays that hold the title, descriptions, and routine of the workouts.
    protected ArrayList<WorkoutDataObject> Workouts = new ArrayList<>();

    //Empty constructor for fragment manager
    public WorkoutHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Read from local file for any custom workouts here
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.activity_workouts, container, false);

        //temp dummy data
        Workouts.add(new WorkoutDataObject(false, "title example 1", "example desc 1", new ArrayList<IsolationScheduleContainer>(Arrays.asList(new IsolationScheduleContainer(new ArrayList<IsolationScheduleContainer.Days>(Arrays.asList(IsolationScheduleContainer.Days.FRIDAY, IsolationScheduleContainer.Days.SUNDAY)), new MuscleDataObject("Body region", "Musclegroup"))))));
        Workouts.add(new WorkoutDataObject(false, "title example 2", "example desc 2", new ArrayList<IsolationScheduleContainer>(Arrays.asList(new IsolationScheduleContainer(new ArrayList<IsolationScheduleContainer.Days>(Arrays.asList(IsolationScheduleContainer.Days.FRIDAY, IsolationScheduleContainer.Days.SUNDAY)), new MuscleDataObject("Body region", "Musclegroup"))))));
        Workouts.add(new WorkoutDataObject(false, "title example 3 max linesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", "example desc 3 lets gooooooooooooooooooooooooooooooo ooooooooooooooo oooooooooooooooooooooooooo  o oooooo    oooooooooooooooooooooo o       o o o o oo ooo oo ooooooo ooooooo   oooooooo ooooo h", new ArrayList<IsolationScheduleContainer>(Arrays.asList(new IsolationScheduleContainer(new ArrayList<IsolationScheduleContainer.Days>(Arrays.asList(IsolationScheduleContainer.Days.FRIDAY, IsolationScheduleContainer.Days.SUNDAY)), new MuscleDataObject("Body region", "Musclegroup"))))));


        //Set adapter
        Context context = root.getContext();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.workout_list);
        recyclerView.setAdapter(new HomeCustomAdapter(Workouts, this.getFragmentManager()));

        return root;
    }
}

class HomeCustomAdapter extends RecyclerView.Adapter<HomeCustomAdapter.ViewHolder> {

    private ArrayList<WorkoutDataObject> Workouts;
    private FragmentManager Manager;

    public HomeCustomAdapter(ArrayList<WorkoutDataObject> workouts, FragmentManager manager) {
        Workouts = workouts;
        Manager = manager;
    }

    @NonNull
    @Override
    public HomeCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workouts_list, parent, false);
        return new HomeCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(HomeCustomAdapter.ViewHolder holder, int position) {
        holder.mObject = Workouts.get(position);
        holder.mTitleView.setText(holder.mObject.getWorkoutTitle());
        holder.mDescriptionView.setText(holder.mObject.getWorkoutDescription());
        holder.mDescriptionView.setMovementMethod(new ScrollingMovementMethod());

        //Allows for overriding the scrolling of the list of workouts to scroll the description text
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
        holder.mDescriptionView.setOnTouchListener(listener);
    }

    @Override
    public int getItemCount() {
        return Workouts.size();
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        private final Button mButton;
        private WorkoutDataObject mObject;

        public ViewHolder(View view, final FragmentManager manager) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.description_title);
            mDescriptionView = (TextView) view.findViewById(R.id.description_content);
            mButton = (Button) view.findViewById(R.id.select_button);
            View.OnClickListener mListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //All touch events for workouts_list.xml
                    if (v.getId() == mButton.getId()) {
                        Toast.makeText(v.getContext(), "SELECTED WORKOUT " + mTitleView.getText(), Toast.LENGTH_SHORT).show();

                    } else if (v.getId() == mTitleView.getId()) {
                        FragmentTransaction t = manager.beginTransaction();
                        Fragment mFrag = new WorkoutDetailsFragment();
                        t.replace(R.id.home_frame, mFrag);
                        t.commit();
                    }
                }
            };

            //Title click leads to WorkoutDetailsFragment
            mTitleView.setOnClickListener(mListener);
            //TODO: Select button click changes user's selected workout (Profile)
            mButton.setOnClickListener(mListener);
        }
    }
}