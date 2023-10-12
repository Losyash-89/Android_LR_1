package mai.team2.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class BullsAndCowsActivity extends AppCompatActivity {

    private String NumberAttempt = "Попытка\n"; // временно закомментировал
    private String UserNumber = "Число\n";
    private String NumberCows = "Коров:\n";
    private String NumberBull = "Быков:\n";

    private ImageView[] hearts;
    ImageView Image1,Image2,Image3,Image4;

    TextView Example;

    /*private int HeartNum = 0;*/
    private String Number = "";
    private String MysteryNumber;     // загаданное рандомное число(из 4 знаков)
    private int Attempt = 0;              // номер попытки
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulls_and_cows);

        RandomMysteryNumber(); // генерация загаданного числа

        TextView TextUserNumber = findViewById(R.id.UserNumber);

        /*
        Image1= findViewById(R.id.imageView1); // для системы жизней
        Image2= findViewById(R.id.imageView2);
        Image3= findViewById(R.id.imageView3);
        Image4= findViewById(R.id.imageView4);*/
        Button input = findViewById(R.id.input);
        Example = findViewById(R.id. textView_Example);
        Example.setText(MysteryNumber); // тестовое выведение загаданного числа на экран

        input.setOnClickListener(new View.OnClickListener() {                                  // самое проблемное место, здесь я планировал создать кнопку ввода, чтобы при нажатии полученное число отправлялось в функцию Analysis
            @Override
            public void onClick(View view) {
                Analysis(Number);
                Number = "";
            }
        });
}

    public void onClickNumber(View v){
        Button b = findViewById(v.getId());
        Number += b.getText().toString();
        Example.setText(Number);
    }

    public void RandomMysteryNumber(){    // макимально тупая функция рандомных неповторяющихся чисел
        int a1, a2, a3, a4;
        boolean b = false;
        do{
            a1 = (int) (Math.random() * 10);
            a2 = (int) (Math.random() * 10);
            a3 = (int) (Math.random() * 10);
            a4 = (int) (Math.random() * 10);

            if ((a1!=a2) & (a1!=a3) & (a1!=a4) & (a2!=a3) & (a2!=a4) & (a3!=a4))
                b = true;

        } while (b != true);
        MysteryNumber = Integer.toString(a1) + Integer.toString(a2) + Integer.toString(a3) + Integer.toString(a4);
    }
    public void Analysis (String number){      // сравнение введенного числа с загаданным
        /*number = Number;*/
        int cows = 0;
        int bulls = 0;
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (number.charAt(i) == MysteryNumber.charAt(j)){
                    if (i == j)
                        bulls += 1;
                    else
                        cows += 1;
                }
            }
        }

        NumberCows += cows + "\n";
        NumberBull += bulls + "\n";
        Attempt ++;
        NumberAttempt += Attempt + "\n";
        UserNumber += number + "\n";

        /*if (bulls<4){
            hearts[HeartNum].setVisibility(View.INVISIBLE);
            HeartNum++;
        }*/

        if ((Attempt<=4) && (bulls==4)){
            Intent intent = new Intent(BullsAndCowsActivity.this, BullsAndCowsActivity_win.class);
            startActivity(intent);
        } else {
            if ((Attempt==4) && (bulls<4)){
                Intent intent = new Intent(BullsAndCowsActivity.this, BullsAndCowsActivity_end.class);
                startActivity(intent);
            }
        }

        DisplayInfo();
    }

    public void DisplayInfo(){                  // вывод информации на экран
        TextView TextNumberAttempt = findViewById(R.id.NumberAttempt);
        TextView TextUserNumber = findViewById(R.id.UserNumber);
        TextView TextNumberCows = findViewById(R.id.NumberCows);
        TextView TextNumberBull = findViewById(R.id.NumberBull);
        TextNumberAttempt.setText(NumberAttempt);
        TextUserNumber.setText(UserNumber);
        TextNumberCows.setText(NumberCows);
        TextNumberBull.setText(NumberBull);
        /*Example.setText(UserNumber);*/

    }

    public void showToast(String text) {         // для вывода ошибок
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClick(View view) {
        RandomMysteryNumber();
        showToast(MysteryNumber);

    }
}



