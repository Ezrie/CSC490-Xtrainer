package code.main.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import code.main.R;
import code.main.ui.workouts.WorkoutView;

public class ProfileFragment extends Fragment {

    private ProfileView profileView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileView = ViewModelProviders.of(this).get(code.main.ui.profile.ProfileView.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        return root;
    }
}