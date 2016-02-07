package com.example.mprice.mpflowto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.lvPhotos)
    ListView mPhotoListView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
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
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        mPhotoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PhotoDetailActivity.class);
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

                try {
                    photosJSON = response.getJSONArray("data");

                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        PhotoModel photo = new PhotoModel();

                        photo.userName = photoJSON.getJSONObject("user").getString("username");
                        photo.imageCaption = photoJSON.getJSONObject("caption").getString("text");
                        photo.imageURL = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likes = photoJSON.getJSONObject("likes").getInt("count");
                        photo.profilePictureURL = photoJSON.getJSONObject("user").getString("profile_picture");
                        long postedTime = photoJSON.getJSONObject("caption").getLong("created_time");

                        photo.postedTime = getDate(postedTime);


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
                Log.e("fail", responseString);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time*1000);
        String date = DateFormat.format("MM/dd mm:ss", cal).toString();
        return date;
    }
}
