package code.main.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import code.main.R;

public class ProfileActivityDetails extends AppCompatActivity {

    TextView day;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        day = findViewById(R.id.textView5);

        getData();
        setData();
    }

    private void getData(){

        data = getIntent().getStringExtra("day");
    }

    private void setData(){
    day.setText(data);
    }
}