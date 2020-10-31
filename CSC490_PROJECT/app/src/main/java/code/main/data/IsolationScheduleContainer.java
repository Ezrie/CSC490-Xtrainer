package code.main.data;

import java.util.ArrayList;

public class IsolationScheduleContainer {
    //Class to act as container for WorkoutDataObject if workout type is isolation
    //Contains muscle groups with their selected days

    public enum Days{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};

    private ArrayList<Days> SelectedDays;
    private MuscleDataObject MuscleGroup;

    public IsolationScheduleContainer(ArrayList<Days> selectedDays, MuscleDataObject muscleGroup) {
        SelectedDays = selectedDays;
        MuscleGroup = muscleGroup;
    }

    public ArrayList<Days> getSelectedDays() {
        return SelectedDays;
    }

    public void setSelectedDays(ArrayList<Days> selectedDays) {
        SelectedDays = selectedDays;
    }

    public MuscleDataObject getMuscleGroup() {
        return MuscleGroup;
    }

    public void setMuscleGroup(MuscleDataObject muscleGroup) {
        MuscleGroup = muscleGroup;
    }
}
