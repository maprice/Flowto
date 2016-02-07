package com.example.mprice.mpflowto.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoModel implements Parcelable {
   public String userName;
    public String imageCaption;
    public String imageURL;
    public String profilePictureURL;
    public String postedTime;
    public int imageHeight;
    public int likes;
    public ArrayList<PhotoCommentsModel> comments;

    public PhotoModel() {
        comments = new ArrayList();
    }

    public void addComment(PhotoCommentsModel comment) {
        comments.add(comment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(imageCaption);
        dest.writeString(imageURL);
        dest.writeString(profilePictureURL);

        dest.writeInt(imageHeight);
        dest.writeInt(likes);

        dest.writeTypedList(comments);
    }

    private PhotoModel(Parcel in) {
        this();

        userName = in.readString();
        imageCaption = in.readString();
        imageURL = in.readString();
        profilePictureURL = in.readString();
        imageHeight = in.readInt();
        likes = in.readInt();

        in.readTypedList(comments, PhotoCommentsModel.CREATOR);
    }

    public static final Parcelable.Creator<PhotoModel> CREATOR
            = new Parcelable.Creator<PhotoModel>() {

        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };

}
