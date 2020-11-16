package code.main.ui.settings;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    /*
    private WorkoutView WorkoutView;
    Button signOutButton;
    Button disconnectButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WorkoutView = ViewModelProviders.of(this).get(WorkoutView.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        String id;
        try {
            id = getArguments().getString("PERSON_ID");
        } catch (NullPointerException e) {
            id = "null";
        }

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                //.requestIdToken(CLIENT_SERVER_ID)
                .requestProfile()
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);

        final TextView settings_title = root.findViewById(R.id.settings_title);
        final TextView person_id = root.findViewById(R.id.id);
        final TextView person_email = root.findViewById(R.id.email);
        final TextView person_name = root.findViewById(R.id.name);

        settings_title.setText("Settings To Be Added");
        person_email.setText("Email");
        person_name.setText("Name");

        signOutButton = root.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                goToSignIn();
            }
        });

        disconnectButton = root.findViewById(R.id.disconnect_button);
        disconnectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                mGoogleSignInClient.revokeAccess();
                goToSignIn();
                revokeHelper();
            }
        });

        return root;
    }

    private void goToSignIn(){
        Intent goToSignIn = new Intent(SettingsFragment.this.getActivity(), SigninActivity.class);
        startActivity(goToSignIn);
    }

    //TO DO revoke from back end possibly
    protected void revokeHelper(){

    }*/
}