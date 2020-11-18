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
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import code.main.R;
import code.main.data.DummyWorkoutData;
import code.main.data.WorkoutDataObject;
import code.main.ui.custom.CustomHomeFragment;

public class WorkoutHomeFragment extends Fragment {
    protected WorkoutView sharedView;

    //just constants for dummy data descriptions
    //push pull routine
    private String workout_1, workout_2, workout_3, workout_4, workout_5, workout_6, workout_7 = "";
    private String desc_1, desc_2, desc_3, desc_4, desc_5, desc_6, desc_7 = "";
    //isolation for arms
    private String workout_8, workout_9, workout_10, workout_11, workout_12, workout_13, workout_14 = "";
    private String desc_8, desc_9, desc_10, desc_11, desc_12, desc_13, desc_14 = "";
    //isolation for legs



    //Arrays that hold the title, descriptions, and routine of the workouts.
    protected ArrayList<WorkoutDataObject> Workouts = new ArrayList<>();

    //Empty constructor for fragment manager
    public WorkoutHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedView = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(WorkoutView.class);
        //TODO: get custom workouts and load
        Bundle mBundle = this.getArguments();
        if (!(mBundle == null)) {

            String selected = mBundle.getString("selected");
            sharedView.setSelectedWorkoutObject(selected);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_workout_home, container, false);

        //dummy data initialization
        dummyDataInit();
        //temp dummy data: Fri & Sat have Leg & Pull day
        //TODO: Pull workouts from database.

        Workouts.add(DummyWorkoutData.dummy1);
        Workouts.add(DummyWorkoutData.dummy2);
        Workouts.add(DummyWorkoutData.dummy3);
        Workouts.add(DummyWorkoutData.dummy4);
        Workouts.add(DummyWorkoutData.dummy5);
        Workouts.add(DummyWorkoutData.dummy6);

        //Set up custom workout button
        LinearLayoutCompat custom = root.findViewById(R.id.create_custom_workout);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CustomHomeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.home_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Set adapter
        Context context = root.getContext();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.workout_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HomeCustomAdapter(Workouts, this.getFragmentManager()));

        return root;
    }

    //initialize dummy data
    private void dummyDataInit(){

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_workouts, parent, false);
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
                    //All touch events for workout list.xml
                    if (v.getId() == mButton.getId()) {
                        Toast.makeText(v.getContext(), "SELECTED " + mTitleView.getText().toString().toUpperCase(), Toast.LENGTH_SHORT).show();

                        Bundle mBundle = new Bundle();
                        mBundle.putString("selected", mTitleView.getText().toString());

                        Fragment fragment = new WorkoutHomeFragment();
                        fragment.setArguments(mBundle);
                        FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.home_frame, fragment);
                        fragmentTransaction.commit();

                    } else if (v.getId() == mTitleView.getId()) {
                        Bundle mBundle = new Bundle();
                        mBundle.putParcelable("WorkoutObject", mObject);

                        Fragment fragment = new WorkoutDetailsFragment();
                        fragment.setArguments(mBundle);
                        FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.add(R.id.home_frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            };

            //Title click leads to WorkoutDetailsFragment
            mTitleView.setOnClickListener(mListener);

            mButton.setOnClickListener(mListener);
        }
    }
}