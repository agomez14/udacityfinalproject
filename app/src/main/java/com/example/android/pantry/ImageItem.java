package com.example.android.pantry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by barryjohnsonsmith on 8/3/15.
 */
public class ImageItem implements Parcelable {
    private String image;
    private String title;
    private String description;

    public ImageItem(String image, String title, String description) {
        super();
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
        out.writeString(image);
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
        image = in.readString();
        title = in.readString();
        title = in.readString();
    }
}
