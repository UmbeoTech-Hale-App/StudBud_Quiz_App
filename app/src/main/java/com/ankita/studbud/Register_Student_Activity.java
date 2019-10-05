package com.ankita.studbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Student_Activity extends AppCompatActivity {

    EditText email,password,name;
    EditText phone,confirmPassword;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Button registerButton;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context = this;
    //TextView user_location;
    //private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        mFirebaseAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.emailid);
        phone=findViewById(R.id.registerPhoneno);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.repeatPassword);
        name=findViewById(R.id.registerName);
        registerButton= findViewById(R.id.RegisterButton);
        mReg= FirebaseDatabase.getInstance().getReference().child("Student");


        //registerBtn = findViewById(R.id.RegisterButton);
        //user_location = findViewById(R.id.helper_location);
        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String e=email.getText().toString();
                String pwd=password.getText().toString();
                String cpwd=confirmPassword.getText().toString();
                String pne=phone.getText().toString();
                String n=name.getText().toString();
                if(n.isEmpty())
                {
                    name.setError("Please enter your name!!!");
                    name.requestFocus();
                }
                else if(e.isEmpty())
                {
                    email.setError("Please Enter email id!!");
                    email.requestFocus();
                }
                else if(pne.isEmpty())
                {
                    phone.setError("Please Enter your phone number!!!");
                    phone.requestFocus();
                }

                else if(pwd.isEmpty())
                {
                    password.setError("Please enter Your Password!!");
                    password.requestFocus();
                }
                else if(cpwd.isEmpty())
                {
                    confirmPassword.setError("Confirm your Password");
                    confirmPassword.requestFocus();
                }
                else if(!(pwd.equals(cpwd)))
                {
                    confirmPassword.setError("Password does not Match");
                    confirmPassword.requestFocus();
                }
                else if(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()&&cpwd.isEmpty()&&n.isEmpty())
                {
                    Toast.makeText(Register_Student_Activity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()&&cpwd.isEmpty()&&n.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(Register_Student_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register_Student_Activity.this, "Signup Unsuccessful, Please Try Again!!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //startActivity(new Intent(HelperRegistrationActivity.this,HelperDashboardActivity.class));
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name").setValue(name.getText().toString());
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email id").setValue(email.getText().toString());
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());

                                Intent toReliefCentreDashboard = new Intent(Register_Student_Activity.this, Student_Dashboard_Activity.class);
                                startActivity(toReliefCentreDashboard);

                            }

                        }
                    });


                }
                else
                {
                    Toast.makeText(Register_Student_Activity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
                }


                //fetchLocation();
            }

        });


//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                fetchLocation();
//
//
//            }
//        });

//        registerBtn = findViewById(R.id.RegisterButton);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toHelperDashboard = new Intent(HelperRegistrationActivity.this, HelperDashboardActivity.class);
//                startActivity(toHelperDashboard);
//            }
//        });
    }
}

