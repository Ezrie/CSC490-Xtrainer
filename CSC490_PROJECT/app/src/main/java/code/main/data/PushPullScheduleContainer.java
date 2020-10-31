package code.main.data;

import java.util.ArrayList;

public class PushPullScheduleContainer {
    //Class to act as container for WorkoutDataObject if workout type is isolation
    //Contains muscle groups with their selected days

    public enum Days{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
    public enum ExerciseTypeEnum {PUSH, PULL, LEG, NONE};

    private ArrayList<Days> SelectedDays;
    private ExerciseTypeEnum DayType;

    public PushPullScheduleContainer(ArrayList<Days> selectedDays, ExerciseTypeEnum dayType) {
        SelectedDays = selectedDays;
        DayType = dayType;
    }

    public ArrayList<Days> getSelectedDays() {
        return SelectedDays;
    }

    public void setSelectedDays(ArrayList<Days> selectedDays) {
        SelectedDays = selectedDays;
    }

    public ExerciseTypeEnum getDayType() {
        return DayType;
    }

    public void setDayType(ExerciseTypeEnum dayType) {
        DayType = dayType;
    }
}
