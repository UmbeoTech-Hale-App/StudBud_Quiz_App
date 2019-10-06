package com.ankita.studbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YoutubeActivity extends YouTubeBaseActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ListView courseListView;
    ArrayList<String> titles=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        courseListView = (ListView) findViewById(R.id.CourseList);
        ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();// calls function to get items list
        coursesArrayList.add(new Courses("1.Introduction to Android","Description on Content"));
        coursesArrayList.add(new Courses("2.Basic Application on Android","Description on Content"));
        coursesArrayList.add(new Courses("3.Exploring Various Layouts","Description on Content"));
        coursesArrayList.add(new Courses("4.Firebase Database Basic","Description on Content"));
        coursesArrayList.add(new Courses("5.QUIZ -- 1","Description on Content"));
        coursesArrayList.add(new Courses("6.Login & Sign Up Activity","Description on Content"));
        coursesArrayList.add(new Courses("7.SQlite Database","Description on Content"));
        coursesArrayList.add(new Courses("8.Flappy Birds Clone","Description on Content"));
        coursesArrayList.add(new Courses("9.Uber Clone","Description on Content"));
        coursesArrayList.add(new Courses("10.QUIZ -- 2","Description on Content"));
        // instantiate the custom list adapter
        CustomListAdapter adapter = new CustomListAdapter(this, coursesArrayList);

        // get the ListView and attach the adapter
        ListView itemsListView  = (ListView) findViewById(R.id.CourseList);
        itemsListView.setAdapter(adapter);


        final YouTubePlayerView youtubePlayerView = findViewById(R.id.youtubePlayerView);

        String videoId = "EuPSibuIKIg";
        playVideo(videoId, youtubePlayerView);
    }
    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize("AIzaSyCwSuaCvTApq7WEcTKW_3gWRBkqHRvN9Bs",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(videoId);

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}

