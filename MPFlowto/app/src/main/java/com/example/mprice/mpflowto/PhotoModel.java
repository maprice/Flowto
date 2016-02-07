package com.example.mprice.mpflowto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoModel implements Parcelable {

    String userName;
    String imageCaption;
    String imageURL;
    String profilePictureURL;
    int imageHeight;
    int likes;

    ArrayList<PhotoCommentsModel> comments;


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


    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<PhotoModel> CREATOR
            = new Parcelable.Creator<PhotoModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };

}
