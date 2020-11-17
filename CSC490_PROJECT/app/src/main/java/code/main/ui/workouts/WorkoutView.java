package code.main.ui.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutView extends ViewModel {

    private MutableLiveData<String> SelectedWorkoutObject = new MutableLiveData<>();

    public LiveData<String> getSelectedWorkoutObject() {
        return SelectedWorkoutObject;
    }

    public void setSelectedWorkoutObject(String selectedWorkoutObject) {
        SelectedWorkoutObject.postValue(selectedWorkoutObject);
    }
}
