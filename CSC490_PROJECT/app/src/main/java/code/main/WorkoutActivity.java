package code.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import code.main.database.DatabaseAdapter;
import code.main.database.DatabaseHelper;
import code.main.ui.workouts.WorkoutView;

public class WorkoutActivity extends AppCompatActivity {

    //TODO: use onDestroy() or something so you don't have to clean the build after every run...

    private WorkoutView viewModel;
    private DatabaseAdapter adapter;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(WorkoutView.class);

        setContentView(R.layout.workout_navigation_overlay);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        adapter = new DatabaseAdapter(this);
        database = adapter.getDatabase();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profile, R.id.navigation_workouts, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}