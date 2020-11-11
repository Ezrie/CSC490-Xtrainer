package code.main.data;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseDataObject implements Parcelable {

    public enum ExerciseTypeEnum {PUSH, PULL, LEG, NONE};

    private MuscleDataObject ParentGroup;
    private String ExerciseName;
    private String ExerciseDescription;
    private ExerciseTypeEnum ExerciseType;

    //Not passed through parcelable
    private Drawable ExerciseImage;
    private AnimatedImageDrawable ExerciseGif;

    public ExerciseDataObject(MuscleDataObject parentGroup, String exerciseName, String exerciseDescription, ExerciseTypeEnum exerciseType, Drawable exerciseImage, AnimatedImageDrawable exerciseGif) {
        ParentGroup = parentGroup;
        ExerciseName = exerciseName;
        ExerciseDescription = exerciseDescription;
        ExerciseType = exerciseType;
        ExerciseImage = exerciseImage;
        ExerciseGif = exerciseGif;
    }

    //If no images or animations are provided
    public ExerciseDataObject(MuscleDataObject parentGroup, String exerciseName, String exerciseDescription, ExerciseTypeEnum exerciseType) {
        ParentGroup = parentGroup;
        ExerciseName = exerciseName;
        ExerciseDescription = exerciseDescription;
        ExerciseType = exerciseType;
    }

    public MuscleDataObject getParentGroup() {
        return ParentGroup;
    }

    public void setParentGroup(MuscleDataObject parentGroup) {
        ParentGroup = parentGroup;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return ExerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        ExerciseDescription = exerciseDescription;
    }

    public ExerciseTypeEnum getExerciseType() {
        return ExerciseType;
    }

    public void setExerciseType(ExerciseTypeEnum exerciseType) {
        ExerciseType = exerciseType;
    }

    public Drawable getExerciseImage() {
        return ExerciseImage;
    }

    public void setExerciseImage(Drawable exerciseImage) {
        ExerciseImage = exerciseImage;
    }

    public AnimatedImageDrawable getExerciseGif() {
        return ExerciseGif;
    }

    public void setExerciseGif(AnimatedImageDrawable exerciseGif) {
        ExerciseGif = exerciseGif;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(ParentGroup, 0);
        out.writeString(ExerciseName);
        out.writeString(ExerciseDescription);
        out.writeString(ExerciseType.toString());
    }

    private ExerciseDataObject(Parcel in) {
        ParentGroup = in.readParcelable(MuscleDataObject.class.getClassLoader());
        ExerciseName = in.readString();
        ExerciseDescription = in.readString();
        ExerciseType = ExerciseTypeEnum.valueOf(in.readString());
    }

    public static final Creator<ExerciseDataObject> CREATOR = new Creator<ExerciseDataObject>() {
        @Override
        public ExerciseDataObject createFromParcel(Parcel in) {
            return new ExerciseDataObject(in);
        }

        @Override
        public ExerciseDataObject[] newArray(int size) {
            return new ExerciseDataObject[size];
        }
    };

}
