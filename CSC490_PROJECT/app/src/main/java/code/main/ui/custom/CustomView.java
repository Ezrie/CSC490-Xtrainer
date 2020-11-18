package code.main.ui.custom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import code.main.data.IsolationScheduleContainer;
import code.main.data.MuscleDataObject;
import code.main.data.PushPullScheduleContainer;
import code.main.data.WorkoutDataObject;

public class CustomView extends ViewModel {

    //WorkoutDataObject contains array
    private MutableLiveData<WorkoutDataObject> Workout = new MutableLiveData<>();

    public void setNewSchedule(String[] groupNames, Integer[] dayArrays) {
        ArrayList<Object> allSchedules = new ArrayList<>();
        for (int i = 0; i < groupNames.length; i++) {
            //Because this is being called from CustomSelectFragment, the selected days are applied to each group
            allSchedules.add(getScheduleFor(groupNames[i], dayArrays));
        }
        Workout.getValue().setSchedule(allSchedules.toArray());
    }

    //Gets one Object[] instance within the workout's schedule when combined with another schedule
    //Helper method used for creating new schedules
    private Object getScheduleFor(String groupName, Integer[] days) {
        //Translate Integers to either schedule container depending on isPushPull
        if (Objects.requireNonNull(Workout.getValue()).isPushPull()) {

            ArrayList<PushPullScheduleContainer.Days> existingDays = new ArrayList<>();
            //See if groupName already exists. Get days if so.
            PushPullScheduleContainer[] currentSchedule = (PushPullScheduleContainer[]) Workout.getValue().getSchedule();
            for (int i = 0; i < currentSchedule.length; i++) {
                if (PushPullScheduleContainer.ExerciseTypeEnum.valueOf(groupName).equals(currentSchedule[i].getDayType())) {
                    for (int j = 0; j < currentSchedule[i].getSelectedDays().length; j++) {
                        existingDays.add(currentSchedule[i].getSelectedDays()[j]);
                    }
                    //Found groupName already, so break
                    break;
                }
            }
            for (int i = 0; i < days.length; i++) {
                switch (days[i]) {
                    case 0:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.SUNDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.SUNDAY);
                        }
                    case 1:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.MONDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.MONDAY);
                        }
                    case 2:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.TUESDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.TUESDAY);
                        }
                    case 3:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.WEDNESDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.WEDNESDAY);
                        }
                    case 4:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.THURSDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.THURSDAY);
                        }
                    case 5:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.FRIDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.FRIDAY);
                        }
                    case 6:
                        if (!(existingDays.contains(PushPullScheduleContainer.Days.SATURDAY))) {
                            existingDays.add(PushPullScheduleContainer.Days.SATURDAY);
                        }
                }
            }

            return new PushPullScheduleContainer((PushPullScheduleContainer.Days[]) existingDays.toArray(), PushPullScheduleContainer.ExerciseTypeEnum.valueOf(groupName));
        } else {
            //Repeat, but for isolation

            ArrayList<IsolationScheduleContainer.Days> existingDays = new ArrayList<>();
            //See if groupName already exists. Get days if so.
            IsolationScheduleContainer[] currentSchedule = (IsolationScheduleContainer[]) Workout.getValue().getSchedule();
            for (int i = 0; i < currentSchedule.length; i++) {
                if (groupName.equalsIgnoreCase(currentSchedule[i].getMuscleGroup().getMuscleGroupName())) {
                    for (int j = 0; j < currentSchedule[i].getSelectedDays().length; j++) {
                        existingDays.add(currentSchedule[i].getSelectedDays()[j]);
                    }
                    //Found groupName already, so break
                    break;
                }
            }
            for (int i = 0; i < days.length; i++) {
                switch (days[i]) {
                    case 0:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.SUNDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.SUNDAY);
                        }
                    case 1:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.MONDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.MONDAY);
                        }
                    case 2:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.TUESDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.TUESDAY);
                        }
                    case 3:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.WEDNESDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.WEDNESDAY);
                        }
                    case 4:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.THURSDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.THURSDAY);
                        }
                    case 5:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.FRIDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.FRIDAY);
                        }
                    case 6:
                        if (!(existingDays.contains(IsolationScheduleContainer.Days.SATURDAY))) {
                            existingDays.add(IsolationScheduleContainer.Days.SATURDAY);
                        }
                }
            }
            //TODO: get additional data from groupName from db.
            MuscleDataObject group = new MuscleDataObject("temp", groupName);
            return new IsolationScheduleContainer((IsolationScheduleContainer.Days[]) existingDays.toArray(), group);
        }
    }

    public LiveData<WorkoutDataObject> getWorkout() {
        return Workout;
    }

    public void setWorkout(WorkoutDataObject workout) {
        Workout.postValue(workout);
    }
}
