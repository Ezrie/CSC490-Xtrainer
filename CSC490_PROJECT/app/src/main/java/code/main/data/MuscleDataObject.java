package code.main.data;

import android.graphics.drawable.Drawable;

public class MuscleDataObject {

    private String BodyRegion;
    private String MuscleGroup;
    private Drawable MuscleGroupImage;

    public MuscleDataObject(String bodyRegion, String muscleGroup, Drawable muscleGroupImage) {
        BodyRegion = bodyRegion;
        MuscleGroup = muscleGroup;
        MuscleGroupImage = muscleGroupImage;
    }

    //If no image is provided
    public MuscleDataObject(String bodyRegion, String muscleGroup) {
        BodyRegion = bodyRegion;
        MuscleGroup = muscleGroup;
    }

    public String getBodyRegion() {
        return BodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        BodyRegion = bodyRegion;
    }

    public String getMuscleGroup() {
        return MuscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        MuscleGroup = muscleGroup;
    }

    public Drawable getMuscleGroupImage() {
        return MuscleGroupImage;
    }

    public void setMuscleGroupImage(Drawable muscleGroupImage) {
        MuscleGroupImage = muscleGroupImage;
    }
}
