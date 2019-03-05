package com.ajsherrell.android.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

// used resource: https://github.com/FasterXML/jackson

public class Steps implements Parcelable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("videoUrl")
    private String videoUrl = NO_VIDEO_URL_PROVIDED;
    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl = NO_THUMBNAIL_URL_PROVIDED;

    private static final String NO_VIDEO_URL_PROVIDED = null;
    private static final String NO_THUMBNAIL_URL_PROVIDED = null;

    public Steps() {
        this.id = 0;
        this.shortDescription = "";
        this.description = "";
        this.videoUrl = "";
        this.thumbnailUrl = "";
    }

    protected Steps(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoUrl = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.shortDescription);
        parcel.writeString(this.description);
        parcel.writeString(this.videoUrl);
        parcel.writeString(this.thumbnailUrl);
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public boolean hasVideo() {
        return videoUrl != NO_VIDEO_URL_PROVIDED;
    }

    public boolean hasThumbnail() {
        return thumbnailUrl != NO_THUMBNAIL_URL_PROVIDED;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "id=" + id + "," + "\n" +
                "shortDescription=" + shortDescription + "," + "\n" +
                "description=" + description + "," + "\n" +
                "videoUrl=" + videoUrl + "," + "\n" +
                "thumbnailUrl=" + thumbnailUrl +
                "}";
    }
}
