package code.main.data;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;

public class ExerciseDataObject {

    public enum ExerciseTypeEnum {PUSH, PULL, LEG, NONE};

    private MuscleDataObject ParentGroup;
    private String ExerciseName;
    private String ExerciseDescription;
    private ExerciseTypeEnum ExerciseType;
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
}
