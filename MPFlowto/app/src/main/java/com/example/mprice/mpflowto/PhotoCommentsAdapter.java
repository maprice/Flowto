package com.example.mprice.mpflowto;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mprice on 2/6/16.
 */
public class PhotoCommentsAdapter extends ArrayAdapter<PhotoCommentsModel> {

    public PhotoCommentsAdapter(Context context, int resource, List<PhotoCommentsModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoCommentsModel photoModel = getItem(position);
        Log.e("tag", photoModel.profilePictureUrl + "is at position:" + position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_comment, parent, false);
        }

        TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);
        RoundedImageView rivProfile = (RoundedImageView) convertView.findViewById(R.id.rivCommentProfile);


        tvComment.setText(photoModel.comment);
        rivProfile.setImageResource(0);

        Picasso.with(getContext()).load(photoModel.profilePictureUrl).into(rivProfile);

        return convertView;
    }
}
