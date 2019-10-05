package com.ankita.studbud;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Profile_fragment extends Fragment {


    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mfirebaseAuth;
    public Profile_fragment() {
    }


    TextView username;
    TextView name;
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
        username.setText(mfirebaseAuth.getInstance().getCurrentUser().getEmail());
        name.setText(mfirebaseAuth.getInstance().getCurrentUser().getDisplayName());

    }



}
