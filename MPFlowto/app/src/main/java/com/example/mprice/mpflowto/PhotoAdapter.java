package com.example.mprice.mpflowto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        tvCaption.setText(photoModel.imageCaption);
        ivPhoto.setImageResource(0);

        Picasso.with(getContext()).load(photoModel.imageURL).into(ivPhoto);
        return convertView;



    }
}
