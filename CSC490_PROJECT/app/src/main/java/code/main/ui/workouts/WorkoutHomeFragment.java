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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.main.R;
import code.main.data.PushPullScheduleContainer;
import code.main.data.WorkoutDataObject;

public class WorkoutHomeFragment extends Fragment {
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
        //Read from local file for any custom workouts here
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_workout_home, container, false);

        //dummy data initialization
        dummyDataInit();
        //temp dummy data: Fri & Sat have Leg & Pull day
        //TODO: Pull workouts from database.
        PushPullScheduleContainer.Days[] schedule = new PushPullScheduleContainer.Days[2];
        schedule[0] = PushPullScheduleContainer.Days.WEDNESDAY;
        schedule[1] = PushPullScheduleContainer.Days.SATURDAY;
        PushPullScheduleContainer[] container1 = new PushPullScheduleContainer[4];
        container1[0] = new PushPullScheduleContainer(schedule, PushPullScheduleContainer.ExerciseTypeEnum.LEG);
        container1[1] = new PushPullScheduleContainer(schedule, PushPullScheduleContainer.ExerciseTypeEnum.PULL);
        container1[2] = new PushPullScheduleContainer(schedule, PushPullScheduleContainer.ExerciseTypeEnum.PULL);
        container1[3] = new PushPullScheduleContainer(schedule, PushPullScheduleContainer.ExerciseTypeEnum.PULL);

        Workouts.add(new WorkoutDataObject(true,workout_1, desc_1, container1));
        Workouts.add(new WorkoutDataObject(true,workout_2, desc_2, container1));
        Workouts.add(new WorkoutDataObject(true,workout_3, desc_3, container1));
        Workouts.add(new WorkoutDataObject(true,workout_4, desc_4, container1));
        Workouts.add(new WorkoutDataObject(true,workout_5, desc_5, container1));
        Workouts.add(new WorkoutDataObject(true,workout_6, desc_6, container1));
        Workouts.add(new WorkoutDataObject(true,workout_7, desc_7, container1));
        /*Workouts.add(new WorkoutDataObject(false,workout_8, desc_8, container1));
        Workouts.add(new WorkoutDataObject(false,workout_9, desc_9, container1));
        Workouts.add(new WorkoutDataObject(false,workout_10, desc_10, container1));
        Workouts.add(new WorkoutDataObject(false,workout_11, desc_11, container1));
        Workouts.add(new WorkoutDataObject(false,workout_12, desc_12, container1));
        Workouts.add(new WorkoutDataObject(false,workout_13, desc_13, container1));
        Workouts.add(new WorkoutDataObject(false,workout_14, desc_14, container1));
        */


        //Set adapter
        Context context = root.getContext();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.workout_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HomeCustomAdapter(Workouts, this.getFragmentManager()));

        return root;
    }

    //initialize dummy data
    private void dummyDataInit(){
        //sample push/pull program
        //legs
        workout_1 = "Dumbbell Squat";
        desc_1 = "Have your feet hip-width apart.  Rest a dumbbell on each shoulder while still holding it with your hands to support most " +
                "of the weight. Keeping your body straight, lower yourself into the sitting position. Once your quadriceps are parallel to the " +
                "ground (you can go further to target the glutes more), push back up to the standing position.";
        workout_2 = "Leg Extension Machine";
        desc_2 = "In the starting position, have your legs set to be in a flexed state; have your calves pulled " +
                "slightly underneath your quadriceps.  Pushing with your quadriceps, extend your legs until " +
                "they reach their full range of motion. Then, lower your legs in a controlled manner to the " +
                "starting position.";
        workout_3 = "Cable Leg Extension";
        desc_3 = "Have a seat or bench in front of a cable machine with the pulley at ground level. Sitting down " +
                "with your back facing the machine, strap the cable to your ankle. While maintaining an " +
                "upward posture on on the bench, raise the strapped leg until it is leveled with the bench. " +
                "Lower the leg in a controlled manner for the next rep.";

        //pull exercises
        workout_4 = "Standing Barbell Upright Row ";
        desc_4 = "With the barbell in front of you, have a close grip on the center of the bar. " +
                "Using your shoulders, pull the bar to your chest. Make sure to have your " +
                "elbows above the bar at all times. Hold the position for 2 seconds then lower " +
                "the bar slowly.";
        workout_5 = "Bent-Over Row with Dumbbell";
        desc_5 = "Find a flat bench and sit on the edge of the seat. With a dumbbell in " +
                "each hand, lean forward to get your chest as close to your knees. " +
                "With your arms slightly bent, pull the dumbbells from your sides and " +
                "right up to shoulder height. Slowly lower the dumbbells to the starting " +
                "position for the next rep.";
        //push exercises
        workout_6 = "Inclined Push-up";
        desc_6 = "Find a bench for this exercise. Facing the bench, you want to have your legs height-distance " +
                "apart from the bench and lean forward so that you are in a push up position with your hands " +
                "on the sides of the bench. Keeping elbows tucked on the sides and downwards towards the chest, " +
                "push off the bench through your chest muscles. When the arms are fully extended with a slight bent, " +
                "lower yourself in a controlled manner to repeat the motion.";
        workout_7 = "Overhead Tricep Extension with Cable";
        desc_7 = "Set up a cable at hip or ground level. With a rope attached to the cable, stand and face " +
                "away from the cable.  Pull the cable behind your head with your upper arms aligned with " +
                "your head. Keeping the elbows locked, pull the rope forward by extending the arms, then " +
                "slowly move back to the starting position.";

        //isolation for arms
        //deltoids
        workout_7 = "Barbell Front Raise ";
        desc_7 = "Have a barbell in front of you and with an overhand grip grab the bar shoulder-width " +
                "apart. With a slight bend in the elbows, raise the bar forward right below the shoulder line " +
                "in front of you. Then, slowly lower the weight back to down in front of your thighs to prep " +
                "the next rep.";
        workout_8 = "Dumbbell Front Raise";
        desc_8 = "Have a dumbbell in each hand and while standing have your palms facing down. Raise " +
                "the dumbbells up right below the shoulder line, then lower the weights slowly in a controlled " +
                "manner to the front of your thighs.";

        //forearms
        workout_9 = "Dumbbell Wrist Twist";
        desc_9 = "Hold a dumbbell in each of your hands. With your hands towards at your sides, twist your " +
                "write clockwise and counterclockwise while keeping your elbows locked.  Make sure to " +
                "keep a firm grip while rotating the dumbbells to focus on your forearms more effectively.";

        //biceps
        workout_10 = "Barbell Bicep Curl";
        desc_10 = "Place both hands on the barbell shoulder-width apart and palms facing up. Make sure to " +
                "keep your arms at your side and elbows locked. It is also important that you maintain the " +
                "standing body posture as you don't want to use your back or shoulders in this exercise. " +
                "Slowly lift the bar up by just moving the forearms through the biceps until the weight is near " +
                "the chest. Slowly lower the bar for the next rep.";
        workout_11 = "Hammer Curls";
        desc_11 = "Have a dumbbell in each hand and at your sides. Your palms should be facing you. Make " +
                "sure to keep your arms at your side and elbows locked. It is also important that you " +
                "maintain the standing body posture as you don't want to use your back or shoulders in " +
                "this exercise. It might help to lean forward while doing this exercise. Slowly lift " +
                "the dumbbells up your sides by just moving the forearms through the biceps until " +
                "the weight is near the chest. Slowly lower the bar for the next rep.";
        workout_12 = "Dumbbell Reverse Curl";
        desc_12 = "Have a dumbbell in each hand and palms facing down. Make sure to keep your arms at your " +
                "side and elbows locked. It is also important that you maintain the standing body posture as " +
                "you don't want to use your back or shoulders in this exercise. Slowly lift the dumbbells up " +
                "by just moving the forearms through the biceps until the weight is near the chest. Slowly " +
                "lower the bar for the next rep.";

        //triceps
        workout_13 = "Overhead Tricep Extension with Cable";
        desc_13 = "Set up a cable at hip or ground level. With a rope attached to the cable, stand and face " +
                "away from the cable.  Pull the cable behind your head with your upper arms aligned with " +
                "your head. Keeping the elbows locked, pull the rope forward by extending the arms, then " +
                "slowly move back to the starting position.";
        workout_14 = "Rope pulldown";
        desc_14 = "Set a cable above your height with a rope attached. Facing the machine, grab the rope with " +
                "both hands and while keeping the elbows locked, pull the rope down until the arms are fully " +
                "extended. Then, slowly release your arms and return to the starting position in a controlled " +
                "manner.";
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
                        Toast.makeText(v.getContext(), "SELECTED WORKOUT : " + mTitleView.getText(), Toast.LENGTH_SHORT).show();
                        //TODO: Select button click changes user's selected workout (in Profile)
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