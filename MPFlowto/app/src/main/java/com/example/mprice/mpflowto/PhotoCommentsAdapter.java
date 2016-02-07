package com.example.mprice.mpflowto;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_comment, parent, false);
        }

        TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);
        RoundedImageView rivProfile = (RoundedImageView) convertView.findViewById(R.id.rivCommentProfile);
        TextView mUsername = (TextView) convertView.findViewById(R.id.tvUsername);


        mUsername.setText(photoModel.username);
        tvComment.setText(photoModel.comment);
        tvComment.setMaxLines(4);
        rivProfile.setImageResource(0);

        int dark = getContext().getResources().getColor(R.color.colorPrimaryDark);
        int accents = getContext().getResources().getColor(R.color.colorAccentDark);

        SpannableString hashText = new SpannableString(tvComment.getText().toString());
        Matcher matcher = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(hashText);
        while (matcher.find()) {
            hashText.setSpan(new ForegroundColorSpan(dark), matcher.start(), matcher.end(), 0);
            tvComment.setText(hashText);
        }
        matcher = Pattern.compile("@([A-Za-z0-9_-]+)").matcher(hashText);
        while (matcher.find()) {
            hashText.setSpan(new ForegroundColorSpan(accents), matcher.start(), matcher.end(), 0);
            tvComment.setText(hashText);
        }

        Picasso.with(getContext()).load(photoModel.profilePictureUrl).into(rivProfile);

        return convertView;
    }
}
