package code.main.data;

import java.util.ArrayList;

public class WorkoutDataObject {

    private String WorkoutTitle;
    private String WorkoutDescription;
    private ArrayList<GroupDayContainer> Schedule;

    public WorkoutDataObject(String workoutTitle, String workoutDescription, ArrayList<GroupDayContainer> schedule) {
        WorkoutTitle = workoutTitle;
        WorkoutDescription = workoutDescription;
        Schedule = schedule;
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

    public ArrayList<GroupDayContainer> getSchedule() {
        return Schedule;
    }

    public void setSchedule(ArrayList<GroupDayContainer> schedule) {
        Schedule = schedule;
    }
}
