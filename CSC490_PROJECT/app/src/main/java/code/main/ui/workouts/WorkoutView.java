package code.main.ui.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import code.main.data.WorkoutDataObject;

public class WorkoutView extends ViewModel {
    private LiveData<WorkoutDataObject> SelectedWorkoutObject;

    public LiveData<WorkoutDataObject> getSelectedWorkoutObject() {
        return SelectedWorkoutObject;
    }

    public void setSelectedWorkoutObject(LiveData<WorkoutDataObject> selectedWorkoutObject) {
        SelectedWorkoutObject = selectedWorkoutObject;
    }
}
