package code.main;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import code.main.database.DatabaseAdapter;
import code.main.database.DatabaseHelper;
import code.main.ui.WorkoutViewModel;

public class WorkoutActivity extends AppCompatActivity {

    private WorkoutViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        setContentView(R.layout.activity_workout_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        DatabaseAdapter adapter = new DatabaseAdapter(this);
        DatabaseHelper datbase = adapter.getDatabase();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profile, R.id.navigation_workouts, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.e("VERBOSE", "Destination: "+destination.getLabel().toString());
                viewModel.setCurrentTab(destination.getLabel().toString());
            }
        });
    }
}