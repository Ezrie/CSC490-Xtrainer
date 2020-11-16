package code.main;

import android.content.Intent;
import android.net.Uri;
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



public class SettingsActivity extends AppCompatActivity {



    private ImageView photo;
    private TextView name;
    private TextView id;
    private TextView email;
    private TextView title;
    private Button sign_out_btn;
    private Button disconnect_btn;
    private GoogleSignInOptions gso;
    private String title_text = "Settings Page";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        //viewModel = ViewModelProviders.of(this).get(ProfileView.class);

        Bundle bundle = getIntent().getExtras();

        String personName = bundle.getString("PERSON_NAME");
        String personEmail = bundle.getString("PERSON_EMAIL");
        String personId = bundle.getString("PERSON_ID");
        Uri personPhoto = getIntent().getParcelableExtra("image_URI");

        //connect widgets to names
        photo = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        title = findViewById(R.id.settings_title);
        sign_out_btn = findViewById(R.id.sign_out_button);
        disconnect_btn = findViewById(R.id.disconnect_button);

        name.setText(personName);
        email.setText(personEmail);
        id.setText(personId);
        photo.setImageURI(personPhoto);
        title.setText(title_text);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                //.requestIdToken("730176124480-35nifq78ep4f6gqvjnsod05jfpfheoaa.apps.googleusercontent.com")
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



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
                        //TODO: USE SQL QUERIES TO DELETE THIS ACCOUNTS DATA
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
                        //TODO: USE SQL QUERIES TO DELETE THIS ACCOUNTS DATA
                        goSignInActivity();
                    }
                });
    }

    private void goSignInActivity(){
        Intent intent = new Intent(SettingsActivity.this, SigninActivity.class);
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
