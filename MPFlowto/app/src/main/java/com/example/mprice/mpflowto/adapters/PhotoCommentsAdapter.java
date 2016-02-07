package com.example.mprice.mpflowto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mprice.mpflowto.utils.PhotoUtils;
import com.example.mprice.mpflowto.R;
import com.example.mprice.mpflowto.models.PhotoCommentsModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mprice on 2/6/16.
 */
public class PhotoCommentsAdapter extends ArrayAdapter<PhotoCommentsModel> {

    private ViewHolder viewHolder;

    public PhotoCommentsAdapter(Context context, int resource, List<PhotoCommentsModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);

    }

    static class ViewHolder {
        @Bind(R.id.tvComment)
        TextView comment;
        @Bind(R.id.rivCommentProfile)
        RoundedImageView profileView;
        @Bind(R.id.tvUsername)
        TextView username;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoCommentsModel photoModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_comment, parent, false);
            viewHolder = new ViewHolder(convertView);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(photoModel.username);
        viewHolder.comment.setText(photoModel.comment);
        viewHolder.comment.setMaxLines(4);
        viewHolder.profileView.setImageResource(0);

        PhotoUtils.highlightText(viewHolder.comment, getContext());
        Picasso.with(getContext()).load(photoModel.profilePictureUrl).into(viewHolder.profileView);

        return convertView;
    }
}
