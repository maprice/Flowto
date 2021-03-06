package com.example.mprice.mpflowto.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mprice.mpflowto.adapters.PhotoCommentsAdapter;
import com.example.mprice.mpflowto.models.PhotoCommentsModel;
import com.example.mprice.mpflowto.models.PhotoModel;
import com.example.mprice.mpflowto.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mprice on 2/5/16.
 */
public class PhotoDetailActivity extends AppCompatActivity {

    @Bind(R.id.ivPhoto)
    ImageView mPhotoView;

    @Bind(R.id.tvCaption)
    TextView mCaption;

    @Bind(R.id.lvComments)
    ListView mComments;

    private PhotoCommentsAdapter mPhotoCommentAdapter;
    private ArrayList<PhotoCommentsModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);

        PhotoModel photoModel = getIntent().getParcelableExtra("photoModel");

        mCaption.setText(photoModel.userName);
        mPhotoView.setImageResource(0);

        Picasso.with(this).load(photoModel.imageURL).into(mPhotoView);
        mList = photoModel.comments;

        mPhotoCommentAdapter = new PhotoCommentsAdapter(this, 0, mList);

        mComments.setAdapter(mPhotoCommentAdapter);
    }
}
