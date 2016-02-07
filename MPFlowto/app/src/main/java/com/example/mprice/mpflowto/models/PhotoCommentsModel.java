package com.example.mprice.mpflowto.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mprice on 2/6/16.
 */
public class PhotoCommentsModel implements Parcelable {
    public String username;
    public String comment;
    public String profilePictureUrl;

    public PhotoCommentsModel() { }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(comment);
        dest.writeString(profilePictureUrl);
    }

    private PhotoCommentsModel(Parcel in) {
        username = in.readString();
        comment = in.readString();
        profilePictureUrl = in.readString();
    }

    public static final Parcelable.Creator<PhotoCommentsModel> CREATOR
            = new Parcelable.Creator<PhotoCommentsModel>() {

        @Override
        public PhotoCommentsModel createFromParcel(Parcel in) {
            return new PhotoCommentsModel(in);
        }

        @Override
        public PhotoCommentsModel[] newArray(int size) {
            return new PhotoCommentsModel[size];
        }
    };
}
