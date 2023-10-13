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

    private Button[] affectedButtons = new Button[4];

    TextView Example;

    private String Number = "";       // временная переменная для введения числа пользователем
    private String MysteryNumber;     // загаданное рандомное число(из 4 знаков)
    private int Attempt = 0;              // номер попытки
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulls_and_cows);

        RandomMysteryNumber(); // генерация загаданного числa


        Button input = findViewById(R.id.input);
        Example = findViewById(R.id. textView_Example);
        Example.setText(MysteryNumber); // тестовое выведение загаданного числа на экран

        input.setOnClickListener(new View.OnClickListener() {                                  // самое проблемное место, здесь я планировал создать кнопку ввода, чтобы при нажатии полученное число отправлялось в функцию Analysis
            @Override
            public void onClick(View view) {
                if ((Number.length() == 4) & (Number.charAt(0) != 0)) {
                    Analysis();
                }
            }
        });
}

    public void onClickNumber(View v){      // цифровые клавиши
        int l = Number.length();
        if (Number.length() < 4){
            affectedButtons[l] = findViewById(v.getId());
            Number += affectedButtons[l].getText().toString();
            Example.setText(Number);
            affectedButtons[l].setEnabled(false);
        }else
            Analysis();
    }

    public void RandomMysteryNumber(){    //  функция рандомных неповторяющихся чисел
        int a1, a2, a3, a4;
        do{
            a1 = (int) (Math.random() * 10);
            a2 = (int) (Math.random() * 10);
            a3 = (int) (Math.random() * 10);
            a4 = (int) (Math.random() * 10);

        } while ((a1==a2) | (a1==a3) | (a1==a4) | (a2==a3) | (a2==a4) | (a3==a4));
        MysteryNumber = Integer.toString(a1) + Integer.toString(a2) + Integer.toString(a3) + Integer.toString(a4);
    }
    public void Analysis (){      // сравнение введенного числа с загаданным
        /*number = Number;*/
        int cows = 0;
        int bulls = 0;
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (Number.charAt(i) == MysteryNumber.charAt(j)){
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
        UserNumber += Number + "\n";
        Number = "";


        if ((Attempt==4) && (bulls<4)){        // проигрыш
            Intent intent = new Intent(BullsAndCowsActivity.this, BullsAndCowsActivity_end.class);
            startActivity(intent);
        } else {
            if ((Attempt<=4) && (bulls==4)){    // выигрыш
                Intent intent = new Intent(BullsAndCowsActivity.this, BullsAndCowsActivity_win.class);
                startActivity(intent);
            }
        }

        for (int i=0; i<4; i++){
            affectedButtons[i].setEnabled(true);
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
        Example.setText(Number);
        /*Example.setText(UserNumber);*/

    }

    public void showToast(String text) {         // для вывода ошибок
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}



