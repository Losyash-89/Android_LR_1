package mai.team2.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonIntentToBullsAndCows = (Button) findViewById(R.id.buttonStartGame);
        ButtonIntentToBullsAndCows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenToBullsAndCows = new Intent(MainActivity.this, BullsAndCowsActivity.class);
                startActivity(intenToBullsAndCows);
            }
        });
    }


}