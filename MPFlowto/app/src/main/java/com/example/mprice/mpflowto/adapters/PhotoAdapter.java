package com.example.mprice.mpflowto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mprice.mpflowto.utils.PhotoUtils;
import com.example.mprice.mpflowto.R;
import com.example.mprice.mpflowto.models.PhotoCommentsModel;
import com.example.mprice.mpflowto.models.PhotoModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoAdapter extends ArrayAdapter<PhotoModel> {

    private static final int COMMENT_COUNT = 2;

    public PhotoAdapter(Context context, int resource, List<PhotoModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    static class ViewHolder {
        @Bind(R.id.tvCaption)
        TextView tvCaption;
        @Bind(R.id.ivPhoto)
        ImageView ivPhoto;
        @Bind(R.id.rivProfile)
        RoundedImageView rivProfile;
        @Bind(R.id.tvUsername)
        TextView tvUsername;
        @Bind(R.id.tvPostedTime)
        TextView tvPostedTime;
        @Bind(R.id.tvLikes)
        TextView tvLikes;

        @Bind(R.id.llComments)
        LinearLayout llComments;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoModel photoModel = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvUsername.setText(photoModel.userName);
        viewHolder.tvPostedTime.setText(photoModel.postedTime);
        viewHolder.tvLikes.setText(photoModel.likes + "");
        viewHolder.tvCaption.setText(photoModel.imageCaption);
        viewHolder.ivPhoto.setImageResource(0);
        viewHolder.rivProfile.setImageResource(0);


        PhotoUtils.highlightText(viewHolder.tvCaption, getContext());

        Picasso.with(getContext()).load(photoModel.imageURL).placeholder(R.drawable.default_placeholder).into(viewHolder.ivPhoto);
        Picasso.with(getContext()).load(photoModel.profilePictureURL).into(viewHolder.rivProfile);

        updateComment(COMMENT_COUNT, viewHolder.llComments, photoModel);
        return convertView;

    }

    private void updateComment(int count, LinearLayout commentLayout, PhotoModel photoModel) {
        for (int i = 0; i < count; i++) {
            View commentView;
            PhotoCommentsModel commentModel;

            if (photoModel.comments.size() > i) {
                commentModel = photoModel.comments.get(i);
            } else {
                commentLayout.removeViewAt(i);
                continue;
            }

            if (commentLayout.getChildCount() > i) {
                commentView = commentLayout.getChildAt(i);
            } else {
                commentView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_comment, null, false);
                commentLayout.addView(commentView);
            }

            TextView tvComment = (TextView) commentView.findViewById(R.id.tvComment);
            RoundedImageView rivCommentProfile = (RoundedImageView) commentView.findViewById(R.id.rivCommentProfile);
            TextView tvUsername = (TextView) commentView.findViewById(R.id.tvUsername);

            tvUsername.setText(commentModel.username);
            tvComment.setText(commentModel.comment);
            Picasso.with(getContext()).load(commentModel.profilePictureUrl).into(rivCommentProfile);

            PhotoUtils.highlightText(tvComment, getContext());
        }
    }
}
