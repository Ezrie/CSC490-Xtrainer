package code.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;

public class SigninActivity extends AppCompatActivity {

    private static final String TAG ="";
    SignInButton signInButton;
    private static final int RC_SIGN_IN =  1;
    protected static final String CLIENT_SERVER_ID = "730176124480-35nifq78ep4f6gqvjnsod05jfpfheoaa.apps.googleusercontent.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinactivity);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                //.requestIdToken(CLIENT_SERVER_ID)
                .requestProfile()
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        if (completedTask.isComplete()){
            goProfileActivity();
        }
        else
            Toast.makeText(getApplicationContext(),"Sign in failed", Toast.LENGTH_LONG).show();
            /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);@@@@@@@@@@@@@@@@@@@*/
    }

    private void goProfileActivity(){
        Intent intent = new Intent(SigninActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    /*@Override @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        //TO DO: UPDATE UI WHETHER THAT BE HIDE SIGN IN BUTTON OR WHAT
        // Signed in successfully, show authenticated UI.

        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            String personId = account.getIdToken();
            Uri personPhoto = account.getPhotoUrl();

            Intent i = new Intent(SigninActivity.this, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PERSON_NAME", personName);
            bundle.putString("PERSON_EMAIL", personEmail);
            //bundle.putString("PERSON_ID", personId);
            bundle.putString("PERSON_ID", personId);
            i.putExtras(bundle);
            i.putExtra("image_URI", personPhoto);
            startActivity(i);
            finish();
        }

    }@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
}
