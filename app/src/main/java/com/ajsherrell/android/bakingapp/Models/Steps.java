package com.ajsherrell.android.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl = NO_VIDEO_URL_PROVIDED;
    private String thumbnailUrl = NO_THUMBNAIL_URL_PROVIDED;

    private static final String NO_VIDEO_URL_PROVIDED = null;
    private static final String NO_THUMBNAIL_URL_PROVIDED = null;

    // 3 constructors for different scenarios:
    public Steps(int id, String shortDescription, String description) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public Steps(int id, String shortDescription, String description, String videoUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public Steps(int id, String shortDescription, String description, String videoUrl,
                 String thumbnailUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    protected Steps(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
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
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailUrl);
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
}
