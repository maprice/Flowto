package com.example.mprice.mpflowto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mprice.mpflowto.adapters.PhotoAdapter;
import com.example.mprice.mpflowto.models.PhotoCommentsModel;
import com.example.mprice.mpflowto.models.PhotoModel;
import com.example.mprice.mpflowto.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.ocpsoft.pretty.time.PrettyTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PhotoFeedActivity extends AppCompatActivity {

    @Bind(R.id.lvPhotos)
    ListView mPhotoListView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<PhotoModel> photos;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        photos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, 0, photos);
        mPhotoListView.setAdapter(photoAdapter);

        makeNetworkRequest();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeNetworkRequest();
            }
        });
        // Configure the refreshing colors
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark,
                R.color.colorAccentDark);

        mPhotoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhotoFeedActivity.this, PhotoDetailActivity.class);
                PhotoModel model = photoAdapter.getItem(position);
                intent.putExtra("photoModel", model);
                startActivity(intent);
            }
        });
    }

    private void makeNetworkRequest() {
        String URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                JSONArray photosJSON = null;
                photos.clear();
                try {
                    photosJSON = response.getJSONArray("data");

                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        PhotoModel photo = new PhotoModel();

                        photo.userName = photoJSON.getJSONObject("user").optString("full_name");
                        if (photo.userName == null) {
                            photo.userName = photoJSON.getJSONObject("user").getString("username");
                        }

                        photo.imageCaption = photoJSON.getJSONObject("caption").getString("text");
                        photo.imageURL = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likes = photoJSON.getJSONObject("likes").getInt("count");
                        photo.profilePictureURL = photoJSON.getJSONObject("user").getString("profile_picture");
                        long postedTime = photoJSON.getJSONObject("caption").getLong("created_time");

                        PrettyTime p = new PrettyTime();
                        String timeString = p.format(new Date(postedTime * 1000));
                        photo.postedTime = timeString;

                        JSONArray commentsJSON = photoJSON.getJSONObject("comments").getJSONArray("data");

                        for (int j = 0; j < commentsJSON.length(); j++) {
                            PhotoCommentsModel commentsModel = new PhotoCommentsModel();

                            JSONObject commentJSON = commentsJSON.getJSONObject(j);
                            commentsModel.profilePictureUrl = commentJSON.optJSONObject("from").getString("profile_picture");
                            commentsModel.username = commentJSON.getJSONObject("from").getString("username");
                            commentsModel.comment = commentJSON.getString("text");
                            photo.addComment(commentsModel);
                        }

                        photos.add(photo);
                    }
                } catch (JSONException e) {

                }
                photoAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
