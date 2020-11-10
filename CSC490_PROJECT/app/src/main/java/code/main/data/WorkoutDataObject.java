package code.main.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class WorkoutDataObject implements Parcelable {

    private boolean isPushPull;
    private String WorkoutTitle;
    private String WorkoutDescription;
    private Object[] Schedule;

    public WorkoutDataObject(boolean isPushPull, String workoutTitle, String workoutDescription, Object[] schedule) {
        this.isPushPull = isPushPull;
        WorkoutTitle = workoutTitle;
        WorkoutDescription = workoutDescription;
        if (this.isPushPull) {
            Schedule = (PushPullScheduleContainer[]) schedule;
        } else {
            Schedule = (IsolationScheduleContainer[]) schedule;
        }
    }

    public String[] getScheduleByDay(String Day) {
        ArrayList<String> schedule = new ArrayList<String>();
        if(isPushPull) {
            for(int i = 0; i < Schedule.length; i++) {
                for(int j = 0; j < ((PushPullScheduleContainer) Schedule[i]).getSelectedDays().length; j++) {
                    if (((PushPullScheduleContainer)Schedule[i]).getSelectedDays()[j].equals(Day)) {
                        schedule.add(((PushPullScheduleContainer)Schedule[i]).getDayType().toString());
                    }
                }
            }
        } else {
            for(int i = 0; i < Schedule.length; i++) {
                for(int j = 0; j < ((IsolationScheduleContainer)Schedule[i]).getSelectedDays().length; j++) {
                    if (((IsolationScheduleContainer)Schedule[i]).getSelectedDays()[j].equals(Day)) {
                        schedule.add(((IsolationScheduleContainer)Schedule[i]).getMuscleGroup().getMuscleGroupName());
                    }
                }
            }
        }
        String[] finalSchedule = new String[schedule.size()];
        schedule.toArray(finalSchedule);
        return finalSchedule;
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

    public Object[] getSchedule() {
        return Schedule;
    }

    public void setSchedule(Object[] schedule) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    //Must be in the same order that WorkoutDataObject(Parcel) is in.
    @Override
    public void writeToParcel(Parcel out, int flags) {
        byte mByte;
        if (isPushPull) {
            mByte = 1;
        } else {
            mByte = 0;
        }

        out.writeByte(mByte);
        out.writeString(WorkoutTitle);
        out.writeString(WorkoutDescription);

        if(isPushPull) {
            out.writeParcelableArray((PushPullScheduleContainer[]) Schedule, 0);
        } else {
            out.writeParcelableArray((IsolationScheduleContainer[]) Schedule, 0);
        }
    }

    //Must be in same order that writeToParcel is in.
    private WorkoutDataObject(Parcel in) {
        isPushPull = (in.readByte() != 0);
        WorkoutTitle = in.readString();
        WorkoutDescription = in.readString();
        if (isPushPull) {
            Schedule = in.readParcelableArray(PushPullScheduleContainer.class.getClassLoader());
        } else {
            Schedule = in.readParcelableArray(IsolationScheduleContainer.class.getClassLoader());
        }
    }

    public static final Parcelable.Creator<WorkoutDataObject> CREATOR
            = new Parcelable.Creator<WorkoutDataObject>() {

        @Override
        public WorkoutDataObject createFromParcel(Parcel in) {
            return new WorkoutDataObject(in);
        }

        @Override
        public WorkoutDataObject[] newArray(int size) {
            return new WorkoutDataObject[size];
        }
    };
}
