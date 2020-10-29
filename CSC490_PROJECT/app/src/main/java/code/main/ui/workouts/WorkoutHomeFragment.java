package code.main.ui.workouts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import code.main.R;
import code.main.data.GroupDayContainer;
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.activity_workouts, container, false);

        //temp
        Workouts.add(new WorkoutDataObject("title example 1", "example desc 1", new ArrayList<GroupDayContainer>(Arrays.asList(new GroupDayContainer(new ArrayList<GroupDayContainer.Days>(Arrays.asList(GroupDayContainer.Days.FRIDAY, GroupDayContainer.Days.SUNDAY)), new MuscleDataObject("Body region", "Musclegroup"))))));
        Workouts.add(new WorkoutDataObject("title example 2", "example desc 2", new ArrayList<GroupDayContainer>(Arrays.asList(new GroupDayContainer(new ArrayList<GroupDayContainer.Days>(Arrays.asList(GroupDayContainer.Days.FRIDAY, GroupDayContainer.Days.SUNDAY)), new MuscleDataObject("Body region", "Musclegroup"))))));

        //Set adapter
        Context context = root.getContext();
        RecyclerView recyclerView = (RecyclerView) root;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CustomAdapter(Workouts));

        return root;
    }
}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<WorkoutDataObject> Workouts;

    public CustomAdapter(ArrayList<WorkoutDataObject> workouts) {
        Workouts = workouts;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workouts_list, parent, false);
        return new CustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, int position) {
        holder.mObject = Workouts.get(position);
        holder.mTitleView.setText(Workouts.get(position).getWorkoutTitle());
        holder.mDescriptionView.setText(Workouts.get(position).getWorkoutDescription());
    }

    @Override
    public int getItemCount() {
        return Workouts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public WorkoutDataObject mObject;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.description_title);
            mDescriptionView = (TextView) view.findViewById(R.id.description_content);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
