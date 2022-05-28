package com.example.educationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.educationapp.databinding.ActivityLoginBinding;
import com.example.educationapp.databinding.ActivitySignupBinding;
import com.example.educationapp.utils.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    FirebaseAuth mAuth;

    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //firebase instance
        mAuth=FirebaseAuth.getInstance();

        //signin with google
        createRequest();

        //Register User

        binding.createAccount.setOnClickListener(view1 -> {

            registerUser();

        });

        binding.loginGoogleAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user!=null){
//            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//            startActivity(intent);
//        }
    }

    public void MovetoSignin(View view) {
        Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
        startActivity(intent);
        finish();
    }



    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.ok))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(),AccountSuccesfulActivity.class);
                            startActivity(intent);



                        } else {
                            Toast.makeText(SignupActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void registerUser()
    {
        String userName=binding.userName.getText().toString();
        String email=binding.email.getText().toString();
        String phoneNumber=binding.phoneNumber.getText().toString();
        String password=binding.password.getText().toString().trim();
        String confirmPassword=binding.confirmPassword.getText().toString().trim();

        //user name Validation
        if(userName.isEmpty())
        {
            binding.userName.setError("Full Name is Required!.");
            binding.userName.requestFocus();
            return;
        }
        //user Email Validation

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.email.setError("Plz provide valid email");
            binding.email.requestFocus();
            return;
        }

        //user Phone Validation

        if(phoneNumber.isEmpty())
        {
            binding.phoneNumber.setError("Phone number is required!");
            binding.phoneNumber.requestFocus();
            return;
        }

        //user password Validation

        if(password.isEmpty())
        {
            binding.password.setError("password is required!");
            binding.password.requestFocus();
            return;
        }
        //password length Validation
        if(password.length() < 6)
        {
            binding.password.setError("Minimum password length should b 6 character!");
            binding.password.requestFocus();
            return;

        }

        //user Correct Password Validation

        if(confirmPassword.isEmpty())
        {
            binding.confirmPassword.setError("Password is required!");
            binding.confirmPassword.requestFocus();
            return;
        }

        //password length validation
        if(confirmPassword.length() < 6)
        {
            binding.confirmPassword.setError("Minimum password length should b 6 character!");
            binding.confirmPassword.requestFocus();
            return;

        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                        "com.education.app", Context.MODE_PRIVATE);
                prefs.edit().putString("userEmail", email.replaceAll("\\.",",")).apply();

                User user=new User(userName,email,password,confirmPassword,phoneNumber);
                FirebaseDatabase.getInstance().getReference(email.replaceAll("\\.",","))
                        .child("user").setValue(user).addOnCompleteListener(task1 -> {
                    if (!task.isSuccessful()) {

                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignupActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(SignupActivity.this, "User has been Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this,AccountSuccesfulActivity.class));
                        finish();
                    }
//                    if(task1.isSuccessful())
//                    {
//                        Toast.makeText(SignupActivity.this, "User has been Register Succesfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(SignupActivity.this,AccountSuccesfulActivity.class));
//                        finish();
//
//                    }
//                    else
//                    {
//                        Toast.makeText(SignupActivity.this, "Fail to Register, Try again ", Toast.LENGTH_SHORT).show();
//
//                    }
                });
            }
            else
            {
                Toast.makeText(SignupActivity.this, "User already has account", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Log.e("TAG", "onFailure: "+e.getMessage() ));

    }
}
