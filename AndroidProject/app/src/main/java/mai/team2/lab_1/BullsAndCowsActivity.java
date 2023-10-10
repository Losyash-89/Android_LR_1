package mai.team2.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BullsAndCowsActivity extends AppCompatActivity {

    private String NumberAttempt = "Попытка\n";
    private String UserNumber = "Число\n";
    private String NumberCows = "Коров:\n";
    private String NumberBull = "Быков:\n";

    private String MysteryNumber;     // загаданное рандомное число(из 4 знаков)
    private int Attempt = 0;              // номер попытки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulls_and_cows);

        RandomMysteryNumber(); // генерация загаданного числа

        Analysis("5678"); // 0 0 (быков коров)
        Analysis("1234"); // 4 0
        Analysis("4321"); // 0 4
        Analysis("1782"); // 1 1

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



