package com.ankita.studbud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Profile_fragment extends Fragment {


    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mfirebaseAuth;
    public Profile_fragment() {
    }


    TextView username;
    TextView name;
    TextView tag1;
    TextView tag2,tag3,tag4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDatabase =FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Student");
        mfirebaseAuth=FirebaseAuth.getInstance();

        return inflater.inflate(R.layout.fragment_profile_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username=view.findViewById(R.id.user_profile_short_bio);
        name=view.findViewById(R.id.user_profile_name);
        //username.setText(mRef.child(mfirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name").getValue());
        //username.setText(mfirebaseAuth.getInstance().getCurrentUser().getEmail());
        tag1=view.findViewById(R.id.tag1);
        tag3=view.findViewById(R.id.tag3);
        tag4=view.findViewById(R.id.tag4);
        tag2=view.findViewById(R.id.tag2);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, String> data = (Map<Object, String>) snapshots.getValue();

                        name.setText(data.get("Name"));
                        username.setText(data.get("Email id"));
                        tag1.setText("Score for Quiz one : "+data.get("AND_Course1"));
                        tag2.setText("Score of Quiz two : "+data.get("AND_Course2"));
                        tag3.setText("Email : "+data.get("Email id"));
                        tag4.setText("Username : "+data.get("Name"));


                    }

                    catch (Exception e){
                        Log.i(TAG, "onDataChange: "+e.getMessage());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



}
