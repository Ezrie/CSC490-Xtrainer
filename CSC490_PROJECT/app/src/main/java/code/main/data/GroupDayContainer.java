package code.main.data;

import java.util.ArrayList;

public class GroupDayContainer {
    //Class to act as container for WorkoutDataObject
    //Contains muscle groups with their selected days

    public enum Days{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};

    private ArrayList<Days> SelectedDays;
    private MuscleDataObject MuscleGroup;

    public GroupDayContainer(ArrayList<Days> selectedDays, MuscleDataObject muscleGroup) {
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
