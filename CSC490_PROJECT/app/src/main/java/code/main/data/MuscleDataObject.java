package code.main.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class MuscleDataObject implements Parcelable {

    private String BodyRegion;
    private String MuscleGroupName;

    //Not passed through parcelable
    private Drawable MuscleGroupImage;

    public MuscleDataObject(String bodyRegion, String muscleGroupName, Drawable muscleGroupImage) {
        BodyRegion = bodyRegion;
        MuscleGroupName = muscleGroupName;
        MuscleGroupImage = muscleGroupImage;
    }

    //If no image is provided
    public MuscleDataObject(String bodyRegion, String muscleGroupName) {
        BodyRegion = bodyRegion;
        MuscleGroupName = muscleGroupName;
    }

    public String getBodyRegion() {
        return BodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        BodyRegion = bodyRegion;
    }

    public String getMuscleGroupName() {
        return MuscleGroupName;
    }

    public void setMuscleGroupName(String muscleGroupName) {
        MuscleGroupName = muscleGroupName;
    }

    public Drawable getMuscleGroupImage() {
        if(MuscleGroupImage != null) {
            return MuscleGroupImage;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setMuscleGroupImage(Drawable muscleGroupImage) {
        MuscleGroupImage = muscleGroupImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(BodyRegion);
        out.writeString(MuscleGroupName);
    }

    private MuscleDataObject(Parcel in) {
        BodyRegion = in.readString();
        MuscleGroupName = in.readString();
    }

    public static final Creator<MuscleDataObject> CREATOR = new Creator<MuscleDataObject>() {
        @Override
        public MuscleDataObject createFromParcel(Parcel in) {
            return new MuscleDataObject(in);
        }

        @Override
        public MuscleDataObject[] newArray(int size) {
            return new MuscleDataObject[size];
        }
    };
}
