package code.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import code.main.ui.profile.ProfileView;

public class ProfileActivity extends AppCompatActivity {

    private ProfileView viewModel;

    private ImageView photo;
    private TextView name;
    private TextView id;
    private TextView email;
    private Button sign_out_btn;
    private Button disconnect_btn;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        //viewModel = ViewModelProviders.of(this).get(ProfileView.class);
        /* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        Bundle bundle = getIntent().getExtras();

        String personName = bundle.getString("PERSON_NAME");
        String personEmail = bundle.getString("PERSON_EMAIL");
        String personId = bundle.getString("PERSON_ID");
        Uri personPhoto = getIntent().getParcelableExtra("image_URI");
        /* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
         */
        String personName;
        String personEmail;
        String personId;
        ImageView personPhoto;

        photo = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        sign_out_btn = findViewById(R.id.sign_out_button);
        disconnect_btn = findViewById(R.id.disconnect_button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                //.requestIdToken("730176124480-35nifq78ep4f6gqvjnsod05jfpfheoaa.apps.googleusercontent.com")
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        /*
        name.setText(personName);
        email.setText(personEmail);
        id.setText(personId);
        photo.setImageURI(personPhoto);
         */

        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.sign_out_button:
                        signOut(mGoogleSignInClient);
                        break;
                    // ...
                }
            }
        });

        disconnect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.disconnect_button:
                        revokeAccess(mGoogleSignInClient);
                        break;
                    // ...
                }
            }
        });

    }

    private void signOut(GoogleSignInClient mGoogleSignInClient) {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // goes to sign in activity
                        goSignInActivity();
                        //startActivity(new Intent(ProfileActivity.this, SigninActivity.class));
                        //finish();
                    }
                });
    }

    private void revokeAccess(GoogleSignInClient mGoogleSignInClient){
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        //TO DO: USE SQL QUERIES TO DELETE THIS ACCOUNTS DATA
                        goSignInActivity();
                    }
                });
    }

    private void goSignInActivity(){
        Intent intent = new Intent(ProfileActivity.this, SigninActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account){
        String personName = account.getDisplayName();
        String personEmail = account.getEmail();
        String personId = account.getId();
        ImageView personPhoto;

        if (account != null){
            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
        }

        else
            goSignInActivity();
    }
}
