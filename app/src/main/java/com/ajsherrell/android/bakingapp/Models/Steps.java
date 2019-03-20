/*
 * Copyright 2019 AJ Sherrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    @JsonProperty("videoURL")
    private String videoURL;
    @JsonProperty("thumbnailURL")
    private String thumbnailURL;


    public Steps() {
        this.id = 0;
        this.shortDescription = "";
        this.description = "";
        this.videoURL = "";
        this.thumbnailURL = "";
    }

    protected Steps(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
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
        parcel.writeString(this.videoURL);
        parcel.writeString(this.thumbnailURL);
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
        return videoURL;
    }

    public String getThumbnailUrl() {
        return thumbnailURL;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "id=" + id + "," + "\n" +
                "shortDescription=" + shortDescription + "," + "\n" +
                "description=" + description + "," + "\n" +
                "videoUrl=" + videoURL + "," + "\n" +
                "thumbnailUrl=" + thumbnailURL +
                "}";
    }
}
