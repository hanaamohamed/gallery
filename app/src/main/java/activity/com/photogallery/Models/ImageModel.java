package activity.com.photogallery.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hanaa mohamed on 09-Sep-16.
 */
public class ImageModel implements Parcelable{
    String imagePath;
    int height;

    public ImageModel(int height, String imagePath) {
        this.height = height;
        this.imagePath = imagePath;
    }

    protected ImageModel(Parcel in) {
        imagePath = in.readString();
        height = in.readInt();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeInt(height);
    }
}
