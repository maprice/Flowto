package com.example.mprice.mpflowto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoAdapter extends ArrayAdapter<PhotoModel> {

    public PhotoAdapter(Context context, int resource, List<PhotoModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoModel photoModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        RoundedImageView rivProfile = (RoundedImageView) convertView.findViewById(R.id.rivProfile);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvPostedTime = (TextView) convertView.findViewById(R.id.tvPostedTime);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);

        tvUsername.setText(photoModel.userName);
        tvPostedTime.setText(photoModel.postedTime);
        tvLikes.setText(photoModel.likes + "");

        LinearLayout llComments = (LinearLayout) convertView.findViewById(R.id.llComments);

        if (llComments.getChildCount() > 0) {
            llComments.removeAllViews();
        }

        addComments(2, llComments, photoModel);

        tvCaption.setText(photoModel.imageCaption);
        ivPhoto.setImageResource(0);
        rivProfile.setImageResource(0);

        Picasso.with(getContext()).load(photoModel.imageURL).placeholder(R.drawable.default_placeholder).into(ivPhoto);
        Picasso.with(getContext()).load(photoModel.profilePictureURL).into(rivProfile);
        return convertView;

    }

    private void addComments(int count, LinearLayout commentLayout, PhotoModel photoModel) {
        for (int i = 1; i < count + 1; i++) {
            if (photoModel.comments.size() <= i) {
                return;
            }
            PhotoCommentsModel firstComment = photoModel.comments.get(i);
            View commentView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_comment, null, false);
            TextView tvComment = (TextView) commentView.findViewById(R.id.tvComment);
            RoundedImageView rivCommentProfile = (RoundedImageView) commentView.findViewById(R.id.rivCommentProfile);
            TextView tvUsername = (TextView) commentView.findViewById(R.id.tvUsername);

            tvUsername.setText(firstComment.username);
            tvComment.setText(firstComment.comment);
            Picasso.with(getContext()).load(firstComment.profilePictureUrl).into(rivCommentProfile);

            commentLayout.addView(commentView);
        }
    }

}
