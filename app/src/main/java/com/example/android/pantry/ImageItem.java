package com.example.android.pantry;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by barryjohnsonsmith on 8/3/15.
 */
public class ImageItem implements Parcelable {
    private Bitmap image;
    private String title;
    private String description;

    public ImageItem(Bitmap image, String title, String description) {
        super();
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription(){ return description;}
    public void setDescription(String description)  {this.description = description;}
    public void setTitle(String title) {
        this.title = title;
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(image, flags);
        out.writeString(title);
        out.writeString(description);
    }

    public static final Parcelable.Creator<ImageItem> CREATOR
            = new Parcelable.Creator<ImageItem>() {
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };

    private ImageItem(Parcel in) {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        title = in.readString();
        title = in.readString();
    }
}
