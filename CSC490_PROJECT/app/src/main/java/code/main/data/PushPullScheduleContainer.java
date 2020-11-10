package code.main.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PushPullScheduleContainer implements Parcelable {
    //Class to act as container for WorkoutDataObject if workout type is isolation
    //Contains muscle groups with their selected days

    public enum Days{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
    public enum ExerciseTypeEnum {PUSH, PULL, LEG, NONE};

    private Days[] SelectedDays;
    private ExerciseTypeEnum DayType;

    public PushPullScheduleContainer(Days[] selectedDays, ExerciseTypeEnum dayType) {
        SelectedDays = selectedDays;
        DayType = dayType;
    }



    public Days[] getSelectedDays() {
        return SelectedDays;
    }

    public void setSelectedDays(Days[] selectedDays) {
        SelectedDays = selectedDays;
    }

    public ExerciseTypeEnum getDayType() {
        return DayType;
    }

    public void setDayType(ExerciseTypeEnum dayType) {
        DayType = dayType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeArray(SelectedDays);
        out.writeString(DayType.toString());
    }

    private PushPullScheduleContainer(Parcel in) {
        SelectedDays = (Days[]) in.readArray(PushPullScheduleContainer.class.getClassLoader());
        DayType = ExerciseTypeEnum.valueOf(in.readString());
    }

    public static final Creator<PushPullScheduleContainer> CREATOR = new Creator<PushPullScheduleContainer>() {
        @Override
        public PushPullScheduleContainer createFromParcel(Parcel in) {
            return new PushPullScheduleContainer(in);
        }

        @Override
        public PushPullScheduleContainer[] newArray(int size) {
            return new PushPullScheduleContainer[size];
        }
    };
}
