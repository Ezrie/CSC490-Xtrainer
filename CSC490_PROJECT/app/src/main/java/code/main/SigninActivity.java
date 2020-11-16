package code.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import code.main.ui.settings.SettingsFragment;

public class SigninActivity extends AppCompatActivity {

    private static final String TAG ="";
    SignInButton signInButton;
    private static final int RC_SIGN_IN =  1;
    private String personName;
    private String personEmail;
    private String personId;
    private Uri personPhoto;
    protected static final String CLIENT_SERVER_ID = "730176124480-35nifq78ep4f6gqvjnsod05jfpfheoaa.apps.googleusercontent.com";

    public SigninActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
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
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    @Override
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
            personName = account.getDisplayName();
            personEmail = account.getEmail();
            personId = account.getId();
            personPhoto = account.getPhotoUrl();

            Intent i = new Intent(SigninActivity.this, SettingsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PERSON_NAME", personName);
            bundle.putString("PERSON_EMAIL", personEmail);

            bundle.putString("PERSON_ID", personId);
            i.putExtras(bundle);
            i.putExtra("image_URI", personPhoto);

            /*
            Fragment settings_fragment = new SettingsFragment();
            settings_fragment.setArguments(bundle);
            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.settings_fragment, settings_fragment);
            transaction.commit();
            */

            startActivity(i);
            finish();
        }

    }

    public String getToken(){
        return this.personId;
    }

    public void setToken(String token){
        this.personId = token;
    }

}

