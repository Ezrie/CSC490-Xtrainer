package code.main.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import code.main.R;
import code.main.SignInActivity;

public class SignInFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestId()
                        //.requestIdToken(CLIENT_SERVER_ID)
                        .requestProfile()
                        .requestEmail()
                        .build();

                // Build a GoogleSignInClient with the options specified by gso.
                final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);
                SignInButton signInButton = root.findViewById(R.id.sign_in_button);
                signInButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                                startActivityForResult(signInIntent, 1);
                        }
                });

                return root;
        }
}
