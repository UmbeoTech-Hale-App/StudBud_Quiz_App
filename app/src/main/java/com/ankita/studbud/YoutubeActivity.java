package com.ankita.studbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class YoutubeActivity extends YouTubeBaseActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ListView courseListView;

   // ArrayList<String> titles=new ArrayList<String>();
    ArrayList <String> links = new ArrayList<String>();
    ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();

    private FirebaseDatabase mDatabase;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);


        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth= FirebaseAuth.getInstance();
        mRef=mDatabase.getReference("Course").child("Android").child("Course1");
        courseListView = (ListView) findViewById(R.id.CourseList);



       // calls function to get items list
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

        final ListView itemsListView  = (ListView) findViewById(R.id.CourseList);


        // instantiate the custom list adapter
        CustomListAdapter adapter = new CustomListAdapter(this, coursesArrayList);

        // get the ListView and attach the adapter

        itemsListView.setAdapter(adapter);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {

                        links.add(snapshots.getValue().toString());

                      /*  links.add(snapshots.getValue().toString());*/
                        /*if(quiz1_android[i].equals(snapshots.getValue().toString())){
                            quiz2_score++;
                        }


                        i++;*/
                        Log.i("Info", "onDataChange: course Id= "+links.get(i));
                        i++;
                    }

                    catch (Exception e){
                        Log.i(TAG, "onDataChange: "+e.getMessage());
                    }

                }


                final YouTubePlayerView youtubePlayerView = findViewById(R.id.youtubePlayerView);

                String videoId = links.get(0);
                playVideo(videoId, youtubePlayerView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize("AIzaSyCwSuaCvTApq7WEcTKW_3gWRBkqHRvN9Bs",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cuePlaylist(videoId);

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

