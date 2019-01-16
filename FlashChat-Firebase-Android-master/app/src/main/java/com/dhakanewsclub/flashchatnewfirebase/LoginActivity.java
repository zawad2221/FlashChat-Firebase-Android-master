package com.dhakanewsclub.flashchatnewfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private final String TAG = "FlashChat";

    // TODO: Add member variables here:

    private FirebaseAuth mAuth;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dhakanewsclub.flashchatnewfirebase.R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(com.dhakanewsclub.flashchatnewfirebase.R.id.login_email);
        mPasswordView = (EditText) findViewById(com.dhakanewsclub.flashchatnewfirebase.R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == com.dhakanewsclub.flashchatnewfirebase.R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth
        mAuth=FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        // TODO: Call attemptLogin() here
        attemptLogin();

    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.dhakanewsclub.flashchatnewfirebase.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {
        String email= mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if(email.equals("")&&password.equals("")) return;
        Toast.makeText(this,"Login is prossaing ",Toast.LENGTH_LONG).show();


        // TODO: Use FirebaseAuth to sign in with email & password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Log.d(TAG,"login success");
                            Intent intent = new Intent(LoginActivity.this,MainChatActivity.class);
                            finish();
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                           Log.d(TAG,"login failed "+task.getException());
                           showErrorDialog("there was a problem to sign in");

                        }

                        // ...
                    }
                });


    }

    // TODO: Show error on screen with an alert dialog
    private void showErrorDialog(String maessage) {
        new  AlertDialog.Builder(this)
                .setTitle("Opp")
                .setMessage(maessage)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }



}