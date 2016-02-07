package com.example.mprice.mpflowto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mprice on 2/6/16.
 */
public class PhotoCommentsModel implements Parcelable {
    String username;
    String comment;
    String profilePictureUrl;

    public PhotoCommentsModel() {
        // Normal actions performed by class, since this is still a normal object!
    }

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


    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<PhotoCommentsModel> CREATOR
            = new Parcelable.Creator<PhotoCommentsModel>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public PhotoCommentsModel createFromParcel(Parcel in) {
            return new PhotoCommentsModel(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public PhotoCommentsModel[] newArray(int size) {
            return new PhotoCommentsModel[size];
        }
    };

}
