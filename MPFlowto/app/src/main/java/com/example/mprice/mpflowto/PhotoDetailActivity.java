package com.example.mprice.mpflowto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends Activity {


    @Bind(R.id.rivProfile)
    ImageView mProfileView;

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

        mCaption.setText(photoModel.imageCaption);
        mPhotoView.setImageResource(0);
        mProfileView.setImageResource(0);

        Picasso.with(this).load(photoModel.imageURL).into(mPhotoView);
        Picasso.with(this).load(photoModel.profilePictureURL).into(mProfileView);
        mList = photoModel.comments;
        Log.e("value of comments", "Comment" + photoModel.comments.get(0).comment + "of size " + mList.size());

        mPhotoCommentAdapter = new PhotoCommentsAdapter(this, 0, mList);

        mComments.setAdapter(mPhotoCommentAdapter);
    }
}
