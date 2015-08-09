package com.example.android.pantry;

/**
 * Created by barryjohnsonsmith on 8/8/15.
 */
public class GridItem {
    private String image;
    private String title;
    private String description;

    public GridItem() {
        super();
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

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription(){
        return description;}
    public void setDescription(String description)  {
        this.description = description;}
}
