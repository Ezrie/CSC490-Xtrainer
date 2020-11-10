package code.main.data;

import android.os.Parcel;
import android.os.Parcelable;

public class IsolationScheduleContainer implements Parcelable {
    //Class to act as container for WorkoutDataObject if workout type is isolation
    //Contains muscle groups with their selected days

    public enum Days{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};

    private Days[] SelectedDays;
    private MuscleDataObject MuscleGroup;

    public IsolationScheduleContainer(Days[] selectedDays, MuscleDataObject muscleGroup) {
        SelectedDays = selectedDays;
        MuscleGroup = muscleGroup;
    }

    public Days[] getSelectedDays() {
        return SelectedDays;
    }

    public void setSelectedDays(Days[] selectedDays) {
        SelectedDays = selectedDays;
    }

    public MuscleDataObject getMuscleGroup() {
        return MuscleGroup;
    }

    public void setMuscleGroup(MuscleDataObject muscleGroup) {
        MuscleGroup = muscleGroup;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeArray(SelectedDays);
        out.writeParcelable(MuscleGroup, 0);
    }

    private IsolationScheduleContainer(Parcel in) {
        SelectedDays = (Days[]) in.readArray(IsolationScheduleContainer.class.getClassLoader());
        MuscleGroup = in.readParcelable(MuscleDataObject.class.getClassLoader());
    }

    public static final Creator<IsolationScheduleContainer> CREATOR = new Creator<IsolationScheduleContainer>() {
        @Override
        public IsolationScheduleContainer createFromParcel(Parcel in) {
            return new IsolationScheduleContainer(in);
        }

        @Override
        public IsolationScheduleContainer[] newArray(int size) {
            return new IsolationScheduleContainer[size];
        }
    };
}
