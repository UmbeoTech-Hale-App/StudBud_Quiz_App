package com.ankita.studbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {


    Button loginButton;
    TextView registerTransfer;
    private DatabaseReference student,reliefCenter;
    EditText email,password,phone;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView toRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        student = FirebaseDatabase.getInstance().getReference("Helper Registration");
        reliefCenter=FirebaseDatabase.getInstance().getReference("Sub Admin Registration");
        loginButton=findViewById(R.id.loginButn);
        mAuth=FirebaseAuth.getInstance();
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        phone=findViewById(R.id.loginphoneno);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser=mAuth.getCurrentUser();
                if(mUser!=null)
                {
                    student.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                // run some code
                                Toast.makeText(Login_Activity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Login_Activity.this, Student_Dashboard_Activity.class);
                                startActivity(i);
                                View inflatedView = getLayoutInflater().inflate(R.layout.nav_header_main, null);
                                TextView text =  inflatedView.findViewById(R.id.username);
                                TextView  text1=inflatedView.findViewById(R.id.displayEmail);
                                text.setText(email.getText().toString());
                                // text1.setText(landmark.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    reliefCenter.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                Toast.makeText(Login_Activity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Login_Activity.this, Teacher_Dashboard_Activity.class);
                                startActivity(i);
                                View inflatedView = getLayoutInflater().inflate(R.layout.nav_header_teacher__dashboard_, null);
                                TextView text =  inflatedView.findViewById(R.id.teacher_id);
                                TextView  text1=inflatedView.findViewById(R.id.teacher_email);
                                text.setText(email.getText().toString());
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(Login_Activity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e=email.getText().toString();
                String pwd=password.getText().toString();
                String pne=phone.getText().toString();
                if(e.isEmpty())
                {
                    email.setError("Please Enter email id!!");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter Your Password!!");
                    password.requestFocus();
                }
                else if(pne.isEmpty())
                {
                    phone.setError("Please enter phone  no!!!");
                    phone.requestFocus();
                }
                else if(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty())
                {
                    Toast.makeText(Login_Activity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()))
                {
                    mAuth.signInWithEmailAndPassword(e,pwd).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Login_Activity.this, "Login Error,Please Login again!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                student.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                            // run some code
                                            Toast.makeText(Login_Activity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                            Intent i=new Intent(Login_Activity.this, Student_Dashboard_Activity.class);
                                            startActivity(i);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                Intent i= new Intent(Login_Activity.this, Student_Dashboard_Activity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login_Activity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTransfer=findViewById(R.id.toregister);
        registerTransfer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent(Login_Activity.this,Register_Option_Activity.class);
                startActivity(launchActivity1);

            }
        });

    }
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
