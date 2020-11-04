package code.main.data;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class WorkoutDataObject extends LiveData<WorkoutDataObject> {

    private boolean isPushPull;
    private String WorkoutTitle;
    private String WorkoutDescription;
    private ArrayList<IsolationScheduleContainer> IsolationSchedule;
    private ArrayList<PushPullScheduleContainer> PushPullSchedule;

    public WorkoutDataObject(boolean ispushpull, String workoutTitle, String workoutDescription, ArrayList<?> Schedule) {
        isPushPull = ispushpull;
        WorkoutTitle = workoutTitle;
        WorkoutDescription = workoutDescription;
        if (isPushPull) {
            PushPullSchedule = (ArrayList<PushPullScheduleContainer>) Schedule;
        } else {
            IsolationSchedule = (ArrayList<IsolationScheduleContainer>) Schedule;
        }

    }

    public ArrayList<String> getScheduleByDay(String Day) {
        ArrayList<String> schedule = new ArrayList<String>();
        if(isPushPull) {
            for(PushPullScheduleContainer container:PushPullSchedule) {
                if(container.getSelectedDays().contains(Day)) {
                    schedule.add(container.getDayType().toString());
                }
            }
        } else {
            for(IsolationScheduleContainer container:IsolationSchedule) {
                if(container.getSelectedDays().contains(Day)) {
                    schedule.add(container.getMuscleGroup().getMuscleGroup());
                }
            }
        }
        return schedule;
    }

    public String getWorkoutType() {
        if(isPushPull) {
            return "Push Pull Workout";
        } else {
            return "Muscle Isolation Workout";
        }
    }

    public boolean isPushPull() {
        return isPushPull;
    }

    public void setPushPull(boolean pushPull) {
        isPushPull = pushPull;
    }

    public ArrayList<PushPullScheduleContainer> getPushPullSchedule() {
        return PushPullSchedule;
    }

    public void setPushPullSchedule(ArrayList<PushPullScheduleContainer> pushPullSchedule) {
        PushPullSchedule = pushPullSchedule;
    }

    public String getWorkoutTitle() {
        return WorkoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        WorkoutTitle = workoutTitle;
    }

    public String getWorkoutDescription() {
        return WorkoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        WorkoutDescription = workoutDescription;
    }

    public ArrayList<IsolationScheduleContainer> getIsoSchedule() {
        return IsolationSchedule;
    }

    public void setIsoSchedule(ArrayList<IsolationScheduleContainer> schedule) {
        IsolationSchedule = schedule;
    }
}
