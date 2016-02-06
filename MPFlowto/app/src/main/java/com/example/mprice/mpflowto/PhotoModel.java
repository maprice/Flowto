package com.example.mprice.mpflowto;

import java.util.ArrayList;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoModel {

    String userName;
    String imageCaption;
    String imageURL;
    int imageHeight;
    int likes;
    String profilePictureURL;

    ArrayList<PhotoCommentsModel> comments;


    public void addComment(PhotoCommentsModel comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }


}
