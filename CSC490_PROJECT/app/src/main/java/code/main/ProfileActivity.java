package code.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import code.main.database.DatabaseAdapter;
import code.main.database.DatabaseHelper;
import code.main.ui.profile.ProfileView;
import code.main.ui.profile.adapterProfile;

public class ProfileActivity extends AppCompatActivity {
    //TODO: use onDestroy() or something so you don't have to clean the build after every run...

    private ProfileView viewModel;
    private DatabaseAdapter adapter;
    private DatabaseHelper database;

    RecyclerView recyclerView;
    String s1[], s2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_navigation_overlay);

        //viewModel = ViewModelProviders.of(this).get(ProfileView.class);

        recyclerView = findViewById(R.id.rsv1);
        s1 = getResources().getStringArray(R.array.test1);
        s2 = getResources().getStringArray(R.array.test2);

        adapterProfile mapdter = new adapterProfile(this, s1, s2);
        recyclerView.setAdapter(mapdter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
