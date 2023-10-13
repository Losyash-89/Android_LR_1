package mai.team2.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BullsAndCowsActivity_win extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulls_and_cows_win);
        findViewById(R.id.button_to_main_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);*/
                openActivity();
            }
        });
    }
    public void openActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}