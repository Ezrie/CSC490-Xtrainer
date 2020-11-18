package code.main.ui.custom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import code.main.R;

public class CustomSelectFragment extends Fragment {

    //0-6 Sun-Sat
    protected static ArrayList<Integer> dayList;
    protected static ArrayList<String> groupList;
    protected CustomView sharedView;
    private Boolean isPushPull;

    //Empty constructor for fragment manager
    public CustomSelectFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedView = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CustomView.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Finds View and it's layout
        View root = inflater.inflate(R.layout.fragment_custom_select, container, false);

        //7 Days of week buttons. Checked listeners set
        ToggleButton sundayButton = root.findViewById(R.id.select_sunday);
        sundayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(0);
                } else {
                    dayList.remove((Integer) 0);
                }
            }
        });
        ToggleButton mondayButton = root.findViewById(R.id.select_monday);
        mondayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(1);
                } else {
                    dayList.remove((Integer) 1);
                }
            }
        });
        ToggleButton tuesdayButton = root.findViewById(R.id.select_tuesday);
        tuesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(2);
                } else {
                    dayList.remove((Integer) 2);
                }
            }
        });
        ToggleButton wednesdayButton = root.findViewById(R.id.select_wednesday);
        wednesdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(3);
                } else {
                    dayList.remove((Integer) 3);
                }
            }
        });
        ToggleButton thursdayButton = root.findViewById(R.id.select_thursday);
        thursdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(4);
                } else {
                    dayList.remove((Integer) 4);
                }
            }
        });
        ToggleButton fridayButton = root.findViewById(R.id.select_friday);
        fridayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(5);
                } else {
                    dayList.remove((Integer) 5);
                }
            }
        });
        ToggleButton saturdayButton = root.findViewById(R.id.select_saturday);
        saturdayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Add day to schedule
                    dayList.add(6);
                } else {
                    dayList.remove((Integer) 6);
                }
            }
        });

        Bundle mBundle = this.getArguments();
        //Should never be null
        if (!Objects.requireNonNull(this.getArguments()).isEmpty()) {
            //Day clicked on to start. This days' button should be toggled.
            Integer day = mBundle.getInt("Day");
            dayList.add(day);
            switch (day) {
                case 0:
                    sundayButton.setChecked(true);
                case 1:
                    mondayButton.setChecked(true);
                case 2:
                    tuesdayButton.setChecked(true);
                case 3:
                    wednesdayButton.setChecked(true);
                case 4:
                    thursdayButton.setChecked(true);
                case 5:
                    fridayButton.setChecked(true);
                case 6:
                    saturdayButton.setChecked(true);
            }
        }
        //TODO: Pull all groups from database, given if it's iso or push/pull.
        //temp dummy data
        String[] groups = new String[8];
        groups[0] = "group 1";
        groups[1] = "group 2";
        groups[2] = "group 3";
        groups[3] = "group 4";
        groups[4] = "group 5";
        groups[5] = "group 6";
        groups[6] = "group 7";
        groups[7] = "group 8";

        //Button that cancels: pop out of fragment without saving
        Button cancel_button = root.findViewById(R.id.select_cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getFragmentManager()).popBackStack();
            }
        });

        //Button that saves: uses CustomView to sync workout schedule data
        Button save_button = root.findViewById(R.id.select_ok_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For each groupName, set days in schedule in CustomView
                sharedView.setNewSchedule((String[]) groupList.toArray(), (Integer[]) dayList.toArray());
                Log.e("VERBOSE", "Set new schedule");
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.custom_select_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new CustomSelectCustomAdapter(groups, this.getFragmentManager()));
        return root;
    }
}

class CustomSelectCustomAdapter extends RecyclerView.Adapter<CustomSelectCustomAdapter.ViewHolder> {

    private String[] groupNames;
    private FragmentManager Manager;

    public CustomSelectCustomAdapter(String[] GroupNames, FragmentManager manager) {
        groupNames = GroupNames;
        Manager = manager;
    }

    @NonNull
    @Override
    public CustomSelectCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_select, parent, false);
        return new CustomSelectCustomAdapter.ViewHolder(view, Manager);
    }

    //Initializes each ViewHolder item and sets listeners for actions
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final CustomSelectCustomAdapter.ViewHolder holder, int position) {
        holder.groupName.setText(groupNames[position]);
    }

    @Override
    public int getItemCount() {
        return groupNames.length;
    }


    //Helper class for adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView groupName;

        public ViewHolder(final View view, final FragmentManager manager) {
            super(view);
            CheckBox checkBox = view.findViewById(R.id.custom_select_checkbox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        //Add to list of selected groups
                        CustomSelectFragment.groupList.add(groupName.getText().toString());
                    } else {
                        //Make sure it's not in list of selected groups
                        CustomSelectFragment.groupList.remove(groupName.getText().toString());
                    }

                }
            });
        }
    }
}
