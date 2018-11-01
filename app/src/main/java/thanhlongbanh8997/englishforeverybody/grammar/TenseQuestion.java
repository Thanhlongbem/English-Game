package thanhlongbanh8997.englishforeverybody.grammar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


import thanhlongbanh8997.englishforeverybody.ActivityChampion;
import thanhlongbanh8997.englishforeverybody.MainActivity;
import thanhlongbanh8997.englishforeverybody.R;

public class TenseQuestion extends AppCompatActivity {
    TextView tvQuestion;
    Button btn_grm_a;
    Button btn_grm_b;
    Button btn_grm_c;
    Button btn_grm_d;
    TextView tv_answer;
    Button btn_ok_grammar;
    Button btn_next_grammar;
    Button btn_save_grammar;
    Button btn_dont_save_grammar;
    TextToSpeech t1;
    TextView tvGrmCountDown;
    LinearLayout grmContent;
    LinearLayout ln_front_grm;
    TextView tv_grm_state;
    CountDownTimer count;
    TextView tvGrammarMoney;


    public int timeCountDown15 = 30000;
    public int timeCountDown610 = 30000;
    public int timeCountDown1115 = 30000;
    public int i=1;
    public static int da = 0;
    public int kq_grm = 0;
    public int highScore;


    public int random = 0;
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_tense_question);
        initViewAndClick();
    }

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public void initViewAndClick(){
        tvQuestion = findViewById(R.id.tvQuestion);
        btn_grm_a = findViewById(R.id.btn_grm_a);
        btn_grm_b = findViewById(R.id.btn_grm_b);
        btn_grm_c = findViewById(R.id.btn_grm_c);
        btn_grm_d = findViewById(R.id.btn_grm_d);
        tv_answer = findViewById(R.id.tv_answer);
        btn_ok_grammar = findViewById(R.id.btn_ok_grammar);
        btn_next_grammar = findViewById(R.id.btn_next_grammar);
        btn_save_grammar = findViewById(R.id.btn_save_grammar);
        tvGrmCountDown = findViewById(R.id.tvGrmCountDown);
        grmContent = findViewById(R.id.grmContent);
        ln_front_grm = findViewById(R.id.ln_front_grm);
        tv_grm_state = findViewById(R.id.tv_grm_state);
        tvGrammarMoney = findViewById(R.id.tvGrammarMoney);
        btn_dont_save_grammar = findViewById(R.id.btn_dont_save_grammar);


        //-------------------------------Khởi tạo đẻ nói
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });


        clickAnswer();

        btn_save_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("SPFolder", Context.MODE_PRIVATE);
                highScore = sharedPreferences.getInt("GRM_tq",0);
                if( i-1 > highScore ){
                    highScore = i-1;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("GRM_tq", highScore);
                    editor.commit();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_dont_save_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });












        btn_grm_a.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_a.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 1;
            }
        });

        btn_grm_b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_b.setBackgroundResource(R.drawable.btn_true);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 2;
            }
        });

        btn_grm_c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_c.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 3;
            }
        });

        btn_grm_d.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_d.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                da = 4;
            }
        });


        random = RandomQuestion(0,3);
        switch (random){
            case 0:
                tvQuestion.setText(R.string.grm_c1);
                btn_grm_a.setText(R.string.grm_c1a);
                btn_grm_b.setText(R.string.grm_c1b);
                btn_grm_c.setText(R.string.grm_c1c);
                btn_grm_d.setText(R.string.grm_c1d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac1)));
                countDownTimer(timeCountDown15);
                break;
            case 1:
                tvQuestion.setText(R.string.grm_c1);
                btn_grm_a.setText(R.string.grm_c1a);
                btn_grm_b.setText(R.string.grm_c1b);
                btn_grm_c.setText(R.string.grm_c1c);
                btn_grm_d.setText(R.string.grm_c1d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac1)));
                countDownTimer(timeCountDown15);
                break;
            case 2:
                tvQuestion.setText(R.string.grm_c2);
                btn_grm_a.setText(R.string.grm_c2a);
                btn_grm_b.setText(R.string.grm_c2b);
                btn_grm_c.setText(R.string.grm_c2c);
                btn_grm_d.setText(R.string.grm_c2d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac2)));
                countDownTimer(timeCountDown15);
                break;
            case 3:
                tvQuestion.setText(R.string.grm_c2);
                btn_grm_a.setText(R.string.grm_c2a);
                btn_grm_b.setText(R.string.grm_c2b);
                btn_grm_c.setText(R.string.grm_c2c);
                btn_grm_d.setText(R.string.grm_c2d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac2)));
                countDownTimer(timeCountDown15);
                break;

        }




        btn_ok_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOK();


            }
        });

        btn_next_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                switch (i){
                    case 1:
                        i++;
                        random = RandomQuestion(2,5);
                        kq_grm = 0;
                        switch (random){
                            case 2:
                                tvQuestion.setText(R.string.grm_c3);
                                btn_grm_a.setText(R.string.grm_c3a);
                                btn_grm_b.setText(R.string.grm_c3b);
                                btn_grm_c.setText(R.string.grm_c3c);
                                btn_grm_d.setText(R.string.grm_c3d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac3)));
                                break;
                            case 3:
                                tvQuestion.setText(R.string.grm_c3);
                                btn_grm_a.setText(R.string.grm_c3a);
                                btn_grm_b.setText(R.string.grm_c3b);
                                btn_grm_c.setText(R.string.grm_c3c);
                                btn_grm_d.setText(R.string.grm_c3d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac3)));
                                break;
                            case 4:
                                tvQuestion.setText(R.string.grm_c4);
                                btn_grm_a.setText(R.string.grm_c4a);
                                btn_grm_b.setText(R.string.grm_c4b);
                                btn_grm_c.setText(R.string.grm_c4c);
                                btn_grm_d.setText(R.string.grm_c4d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac4)));
                                break;
                            case 5:
                                tvQuestion.setText(R.string.grm_c4);
                                btn_grm_a.setText(R.string.grm_c4a);
                                btn_grm_b.setText(R.string.grm_c4b);
                                btn_grm_c.setText(R.string.grm_c4c);
                                btn_grm_d.setText(R.string.grm_c4d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac4)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau1);
                        break;
                    case 2:
                        i++;
                        random = RandomQuestion(4,7);
                        switch (random){
                            case 4:
                                tvQuestion.setText(R.string.grm_c5);
                                btn_grm_a.setText(R.string.grm_c5a);
                                btn_grm_b.setText(R.string.grm_c5b);
                                btn_grm_c.setText(R.string.grm_c5c);
                                btn_grm_d.setText(R.string.grm_c5d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac5)));
                                break;
                            case 5:
                                tvQuestion.setText(R.string.grm_c5);
                                btn_grm_a.setText(R.string.grm_c5a);
                                btn_grm_b.setText(R.string.grm_c5b);
                                btn_grm_c.setText(R.string.grm_c5c);
                                btn_grm_d.setText(R.string.grm_c5d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac5)));
                                break;
                            case 6:
                                tvQuestion.setText(R.string.grm_c6);
                                btn_grm_a.setText(R.string.grm_c6a);
                                btn_grm_b.setText(R.string.grm_c6b);
                                btn_grm_c.setText(R.string.grm_c6c);
                                btn_grm_d.setText(R.string.grm_c6d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac6)));
                                break;
                            case 7:
                                tvQuestion.setText(R.string.grm_c6);
                                btn_grm_a.setText(R.string.grm_c6a);
                                btn_grm_b.setText(R.string.grm_c6b);
                                btn_grm_c.setText(R.string.grm_c6c);
                                btn_grm_d.setText(R.string.grm_c6d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac6)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau2);
                        break;
                    case 3:
                        i++;
                        random = RandomQuestion(6,9);
                        switch (random){
                            case 6:
                                tvQuestion.setText(R.string.grm_c7);
                                btn_grm_a.setText(R.string.grm_c7a);
                                btn_grm_b.setText(R.string.grm_c7b);
                                btn_grm_c.setText(R.string.grm_c7c);
                                btn_grm_d.setText(R.string.grm_c7d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac7)));
                                break;
                            case 7:
                                tvQuestion.setText(R.string.grm_c7);
                                btn_grm_a.setText(R.string.grm_c7a);
                                btn_grm_b.setText(R.string.grm_c7b);
                                btn_grm_c.setText(R.string.grm_c7c);
                                btn_grm_d.setText(R.string.grm_c7d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac7)));
                                break;
                            case 8:
                                tvQuestion.setText(R.string.grm_c8);
                                btn_grm_a.setText(R.string.grm_c8a);
                                btn_grm_b.setText(R.string.grm_c8b);
                                btn_grm_c.setText(R.string.grm_c8c);
                                btn_grm_d.setText(R.string.grm_c8d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac8)));
                                break;
                            case 9:
                                tvQuestion.setText(R.string.grm_c8);
                                btn_grm_a.setText(R.string.grm_c8a);
                                btn_grm_b.setText(R.string.grm_c8b);
                                btn_grm_c.setText(R.string.grm_c8c);
                                btn_grm_d.setText(R.string.grm_c8d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac8)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau3);
                        break;
                    case 4:
                        i++;
                        random = RandomQuestion(8,11);
                        switch (random){
                            case 8:
                                tvQuestion.setText(R.string.grm_c9);
                                btn_grm_a.setText(R.string.grm_c9a);
                                btn_grm_b.setText(R.string.grm_c9b);
                                btn_grm_c.setText(R.string.grm_c9c);
                                btn_grm_d.setText(R.string.grm_c9d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac9)));
                                break;
                            case 9:
                                tvQuestion.setText(R.string.grm_c9);
                                btn_grm_a.setText(R.string.grm_c9a);
                                btn_grm_b.setText(R.string.grm_c9b);
                                btn_grm_c.setText(R.string.grm_c9c);
                                btn_grm_d.setText(R.string.grm_c9d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac9)));
                                break;
                            case 10:
                                tvQuestion.setText(R.string.grm_c10);
                                btn_grm_a.setText(R.string.grm_c10a);
                                btn_grm_b.setText(R.string.grm_c10b);
                                btn_grm_c.setText(R.string.grm_c10c);
                                btn_grm_d.setText(R.string.grm_c10d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac10)));
                                break;
                            case 11:
                                tvQuestion.setText(R.string.grm_c10);
                                btn_grm_a.setText(R.string.grm_c10a);
                                btn_grm_b.setText(R.string.grm_c10b);
                                btn_grm_c.setText(R.string.grm_c10c);
                                btn_grm_d.setText(R.string.grm_c10d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac10)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau4);
                        break;
                    case 5:
                        i++;
                        random = RandomQuestion(10,13);
                        switch (random){
                            case 10:
                                tvQuestion.setText(R.string.grm_c11);
                                btn_grm_a.setText(R.string.grm_c11a);
                                btn_grm_b.setText(R.string.grm_c11b);
                                btn_grm_c.setText(R.string.grm_c11c);
                                btn_grm_d.setText(R.string.grm_c11d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac11)));
                                break;
                            case 11:
                                tvQuestion.setText(R.string.grm_c11);
                                btn_grm_a.setText(R.string.grm_c11a);
                                btn_grm_b.setText(R.string.grm_c11b);
                                btn_grm_c.setText(R.string.grm_c11c);
                                btn_grm_d.setText(R.string.grm_c11d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac11)));
                                break;
                            case 12:
                                tvQuestion.setText(R.string.grm_c12);
                                btn_grm_a.setText(R.string.grm_c12a);
                                btn_grm_b.setText(R.string.grm_c12b);
                                btn_grm_c.setText(R.string.grm_c12c);
                                btn_grm_d.setText(R.string.grm_c12d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac12)));
                                break;
                            case 13:
                                tvQuestion.setText(R.string.grm_c12);
                                btn_grm_a.setText(R.string.grm_c12a);
                                btn_grm_b.setText(R.string.grm_c12b);
                                btn_grm_c.setText(R.string.grm_c12c);
                                btn_grm_d.setText(R.string.grm_c12d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac12)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau5);
                        break;
                    case 6:
                        i++;
                        random = RandomQuestion(12,15);
                        switch (random){
                            case 12:
                                tvQuestion.setText(R.string.grm_c13);
                                btn_grm_a.setText(R.string.grm_c13a);
                                btn_grm_b.setText(R.string.grm_c13b);
                                btn_grm_c.setText(R.string.grm_c13c);
                                btn_grm_d.setText(R.string.grm_c13d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac13)));
                                break;
                            case 13:
                                tvQuestion.setText(R.string.grm_c13);
                                btn_grm_a.setText(R.string.grm_c13a);
                                btn_grm_b.setText(R.string.grm_c13b);
                                btn_grm_c.setText(R.string.grm_c13c);
                                btn_grm_d.setText(R.string.grm_c13d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac13)));
                                break;
                            case 14:
                                tvQuestion.setText(R.string.grm_c14);
                                btn_grm_a.setText(R.string.grm_c14a);
                                btn_grm_b.setText(R.string.grm_c14b);
                                btn_grm_c.setText(R.string.grm_c14c);
                                btn_grm_d.setText(R.string.grm_c14d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac14)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c14);
                                btn_grm_a.setText(R.string.grm_c14a);
                                btn_grm_b.setText(R.string.grm_c14b);
                                btn_grm_c.setText(R.string.grm_c14c);
                                btn_grm_d.setText(R.string.grm_c14d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac14)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau6);
                        break;
                    case 7:
                        i++;
                        random = RandomQuestion(14,17);
                        switch (random){
                            case 14:
                                tvQuestion.setText(R.string.grm_c15);
                                btn_grm_a.setText(R.string.grm_c15a);
                                btn_grm_b.setText(R.string.grm_c15b);
                                btn_grm_c.setText(R.string.grm_c15c);
                                btn_grm_d.setText(R.string.grm_c15d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac15)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c15);
                                btn_grm_a.setText(R.string.grm_c15a);
                                btn_grm_b.setText(R.string.grm_c15b);
                                btn_grm_c.setText(R.string.grm_c15c);
                                btn_grm_d.setText(R.string.grm_c15d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac15)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c16);
                                btn_grm_a.setText(R.string.grm_c16a);
                                btn_grm_b.setText(R.string.grm_c16b);
                                btn_grm_c.setText(R.string.grm_c16c);
                                btn_grm_d.setText(R.string.grm_c16d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac16)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c16);
                                btn_grm_a.setText(R.string.grm_c16a);
                                btn_grm_b.setText(R.string.grm_c16b);
                                btn_grm_c.setText(R.string.grm_c16c);
                                btn_grm_d.setText(R.string.grm_c16d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac16)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau7);
                        break;
                    case 8:
                        i++;
                        random = RandomQuestion(16,19);
                        switch (random){
                            case 15:
                                tvQuestion.setText(R.string.grm_c16);
                                btn_grm_a.setText(R.string.grm_c16a);
                                btn_grm_b.setText(R.string.grm_c16b);
                                btn_grm_c.setText(R.string.grm_c16c);
                                btn_grm_d.setText(R.string.grm_c16d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac16)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c16);
                                btn_grm_a.setText(R.string.grm_c16a);
                                btn_grm_b.setText(R.string.grm_c16b);
                                btn_grm_c.setText(R.string.grm_c16c);
                                btn_grm_d.setText(R.string.grm_c16d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac16)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c17);
                                btn_grm_a.setText(R.string.grm_c17a);
                                btn_grm_b.setText(R.string.grm_c17b);
                                btn_grm_c.setText(R.string.grm_c17c);
                                btn_grm_d.setText(R.string.grm_c17d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac17)));
                                break;
                            case 18:
                                tvQuestion.setText(R.string.grm_c17);
                                btn_grm_a.setText(R.string.grm_c17a);
                                btn_grm_b.setText(R.string.grm_c17b);
                                btn_grm_c.setText(R.string.grm_c17c);
                                btn_grm_d.setText(R.string.grm_c17d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac17)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau8);
                        break;
                    case 9:
                        i++;
                        random = RandomQuestion(18,21);
                        switch (random){
                            case 18:
                                tvQuestion.setText(R.string.grm_c19);
                                btn_grm_a.setText(R.string.grm_c19a);
                                btn_grm_b.setText(R.string.grm_c19b);
                                btn_grm_c.setText(R.string.grm_c19c);
                                btn_grm_d.setText(R.string.grm_c19d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac19)));
                                break;
                            case 19:
                                tvQuestion.setText(R.string.grm_c19);
                                btn_grm_a.setText(R.string.grm_c19a);
                                btn_grm_b.setText(R.string.grm_c19b);
                                btn_grm_c.setText(R.string.grm_c19c);
                                btn_grm_d.setText(R.string.grm_c19d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac19)));
                                break;
                            case 20:
                                tvQuestion.setText(R.string.grm_c20);
                                btn_grm_a.setText(R.string.grm_c20a);
                                btn_grm_b.setText(R.string.grm_c20b);
                                btn_grm_c.setText(R.string.grm_c20c);
                                btn_grm_d.setText(R.string.grm_c20d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac20)));
                                break;
                            case 21:
                                tvQuestion.setText(R.string.grm_c20);
                                btn_grm_a.setText(R.string.grm_c20a);
                                btn_grm_b.setText(R.string.grm_c20b);
                                btn_grm_c.setText(R.string.grm_c20c);
                                btn_grm_d.setText(R.string.grm_c20d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac20)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau9);
                        break;
                    case 10:
                        i++;
                        random = RandomQuestion(20,23);
                        switch (random){
                            case 20:
                                tvQuestion.setText(R.string.grm_c21);
                                btn_grm_a.setText(R.string.grm_c21a);
                                btn_grm_b.setText(R.string.grm_c21b);
                                btn_grm_c.setText(R.string.grm_c21c);
                                btn_grm_d.setText(R.string.grm_c21d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac21)));
                                break;
                            case 21:
                                tvQuestion.setText(R.string.grm_c21);
                                btn_grm_a.setText(R.string.grm_c21a);
                                btn_grm_b.setText(R.string.grm_c21b);
                                btn_grm_c.setText(R.string.grm_c21c);
                                btn_grm_d.setText(R.string.grm_c21d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac21)));
                                break;
                            case 22:
                                tvQuestion.setText(R.string.grm_c22);
                                btn_grm_a.setText(R.string.grm_c22a);
                                btn_grm_b.setText(R.string.grm_c22b);
                                btn_grm_c.setText(R.string.grm_c22c);
                                btn_grm_d.setText(R.string.grm_c22d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac22)));
                                break;
                            case 23:
                                tvQuestion.setText(R.string.grm_c22);
                                btn_grm_a.setText(R.string.grm_c22a);
                                btn_grm_b.setText(R.string.grm_c22b);
                                btn_grm_c.setText(R.string.grm_c22c);
                                btn_grm_d.setText(R.string.grm_c22d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac22)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau10);
                        break;
                    case 11:
                        i++;
                        random = RandomQuestion(22,25);
                        switch (random){
                            case 22:
                                tvQuestion.setText(R.string.grm_c23);
                                btn_grm_a.setText(R.string.grm_c23a);
                                btn_grm_b.setText(R.string.grm_c23b);
                                btn_grm_c.setText(R.string.grm_c23c);
                                btn_grm_d.setText(R.string.grm_c23d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac23)));
                                break;
                            case 23:
                                tvQuestion.setText(R.string.grm_c23);
                                btn_grm_a.setText(R.string.grm_c23a);
                                btn_grm_b.setText(R.string.grm_c23b);
                                btn_grm_c.setText(R.string.grm_c23c);
                                btn_grm_d.setText(R.string.grm_c23d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac23)));
                                break;
                            case 24:
                                tvQuestion.setText(R.string.grm_c24);
                                btn_grm_a.setText(R.string.grm_c24a);
                                btn_grm_b.setText(R.string.grm_c24b);
                                btn_grm_c.setText(R.string.grm_c24c);
                                btn_grm_d.setText(R.string.grm_c24d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac24)));
                                break;
                            case 25:
                                tvQuestion.setText(R.string.grm_c24);
                                btn_grm_a.setText(R.string.grm_c24a);
                                btn_grm_b.setText(R.string.grm_c24b);
                                btn_grm_c.setText(R.string.grm_c24c);
                                btn_grm_d.setText(R.string.grm_c24d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac24)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau11);
                        break;
                    case 12:
                        i++;
                        random = RandomQuestion(24,27);
                        switch (random){
                            case 24:
                                tvQuestion.setText(R.string.grm_c25);
                                btn_grm_a.setText(R.string.grm_c25a);
                                btn_grm_b.setText(R.string.grm_c25b);
                                btn_grm_c.setText(R.string.grm_c25c);
                                btn_grm_d.setText(R.string.grm_c25d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac25)));
                                break;
                            case 25:
                                tvQuestion.setText(R.string.grm_c25);
                                btn_grm_a.setText(R.string.grm_c25a);
                                btn_grm_b.setText(R.string.grm_c25b);
                                btn_grm_c.setText(R.string.grm_c25c);
                                btn_grm_d.setText(R.string.grm_c25d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac25)));
                                break;
                            case 26:
                                tvQuestion.setText(R.string.grm_c26);
                                btn_grm_a.setText(R.string.grm_c26a);
                                btn_grm_b.setText(R.string.grm_c26b);
                                btn_grm_c.setText(R.string.grm_c26c);
                                btn_grm_d.setText(R.string.grm_c26d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac26)));
                                break;
                            case 27:
                                tvQuestion.setText(R.string.grm_c26);
                                btn_grm_a.setText(R.string.grm_c26a);
                                btn_grm_b.setText(R.string.grm_c26b);
                                btn_grm_c.setText(R.string.grm_c26c);
                                btn_grm_d.setText(R.string.grm_c26d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac26)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau12);
                        break;
                    case 13:
                        i++;
                        random = RandomQuestion(26,29);
                        switch (random){
                            case 26:
                                tvQuestion.setText(R.string.grm_c27);
                                btn_grm_a.setText(R.string.grm_c27a);
                                btn_grm_b.setText(R.string.grm_c27b);
                                btn_grm_c.setText(R.string.grm_c27c);
                                btn_grm_d.setText(R.string.grm_c27d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac27)));
                                break;
                            case 27:
                                tvQuestion.setText(R.string.grm_c27);
                                btn_grm_a.setText(R.string.grm_c27a);
                                btn_grm_b.setText(R.string.grm_c27b);
                                btn_grm_c.setText(R.string.grm_c27c);
                                btn_grm_d.setText(R.string.grm_c27d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac27)));
                                break;
                            case 28:
                                tvQuestion.setText(R.string.grm_c28);
                                btn_grm_a.setText(R.string.grm_c28a);
                                btn_grm_b.setText(R.string.grm_c28b);
                                btn_grm_c.setText(R.string.grm_c28c);
                                btn_grm_d.setText(R.string.grm_c28d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac28)));
                                break;
                            case 29:
                                tvQuestion.setText(R.string.grm_c28);
                                btn_grm_a.setText(R.string.grm_c28a);
                                btn_grm_b.setText(R.string.grm_c28b);
                                btn_grm_c.setText(R.string.grm_c28c);
                                btn_grm_d.setText(R.string.grm_c28d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac28)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau13);
                        break;
                    case 14:
                        i++;
                        random = RandomQuestion(28,31);
                        switch (random){
                            case 28:
                                tvQuestion.setText(R.string.grm_c29);
                                btn_grm_a.setText(R.string.grm_c29a);
                                btn_grm_b.setText(R.string.grm_c29b);
                                btn_grm_c.setText(R.string.grm_c29c);
                                btn_grm_d.setText(R.string.grm_c29d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac29)));
                                break;
                            case 29:
                                tvQuestion.setText(R.string.grm_c29);
                                btn_grm_a.setText(R.string.grm_c29a);
                                btn_grm_b.setText(R.string.grm_c29b);
                                btn_grm_c.setText(R.string.grm_c29c);
                                btn_grm_d.setText(R.string.grm_c29d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac29)));
                                break;
                            case 30:
                                tvQuestion.setText(R.string.grm_c30);
                                btn_grm_a.setText(R.string.grm_c30a);
                                btn_grm_b.setText(R.string.grm_c30b);
                                btn_grm_c.setText(R.string.grm_c30c);
                                btn_grm_d.setText(R.string.grm_c30d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac30)));
                                break;
                            case 31:
                                tvQuestion.setText(R.string.grm_c30);
                                btn_grm_a.setText(R.string.grm_c30a);
                                btn_grm_b.setText(R.string.grm_c30b);
                                btn_grm_c.setText(R.string.grm_c30c);
                                btn_grm_d.setText(R.string.grm_c30d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac30)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau14);
                        break;
                    case 15:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                }
                tvGrmCountDown.setVisibility(View.VISIBLE);
                tv_grm_state.setVisibility(View.GONE);
                btn_next_grammar.setVisibility(View.GONE);
                btn_ok_grammar.setVisibility(View.VISIBLE);
            }
        });
    }



    public void clickAnswer(){
        btn_grm_a.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_a.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 1;
            }
        });

        btn_grm_b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_b.setBackgroundResource(R.drawable.btn_true);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 2;
            }
        });

        btn_grm_c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_c.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                btn_grm_d.setBackgroundResource(R.drawable.btn_not);
                da = 3;
            }
        });

        btn_grm_d.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_grm_d.setBackgroundResource(R.drawable.btn_true);
                btn_grm_b.setBackgroundResource(R.drawable.btn_not);
                btn_grm_c.setBackgroundResource(R.drawable.btn_not);
                btn_grm_a.setBackgroundResource(R.drawable.btn_not);
                da = 4;
            }
        });
    }



    public void showDialogOK(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(TenseQuestion.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(TenseQuestion.this);
        }
        builder.setTitle("OK");
        builder.setMessage("Is this your last question?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.DONUT)
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                btn_ok_grammar.setVisibility(View.GONE);
                tvGrmCountDown.setVisibility(View.GONE);
                tv_grm_state.setVisibility(View.VISIBLE);
                if(i!=15){
                    if(da == kq_grm){
                        dialogInterface.dismiss();
                        count.cancel();
                        fillColor(kq_grm);
                        tv_grm_state.setText("well done");
                        btn_next_grammar.setVisibility(View.VISIBLE);
                        String toSpeak = "well done";
                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    }else{
                        count.cancel();
                        fillColor(kq_grm);
                        tv_grm_state.setText("Oh no =(( ");
                        btn_save_grammar.setVisibility(View.VISIBLE);
                        btn_dont_save_grammar.setVisibility(View.VISIBLE);
                    }
                }else{
                    if(da == kq_grm){
                        dialogInterface.dismiss();
                        count.cancel();
                        grmContent.setVisibility(View.GONE);
                        String toSpeak = "Congratulation";
                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        Intent intent = new Intent(getApplicationContext(), ActivityChampion.class);
                        startActivity(intent);
                    }else{
                        count.cancel();
                        fillColor(kq_grm);
                        tv_grm_state.setText("Oh no =(( ");
                        btn_save_grammar.setVisibility(View.VISIBLE);
                        btn_dont_save_grammar.setVisibility(View.VISIBLE);
                    }

                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    public void countDownTimer(int time){
        count = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                tvGrmCountDown.setText(String.valueOf(millisUntilFinished/1000));
                //here you can have your logic to set text to edittext

            }



            @RequiresApi(api = Build.VERSION_CODES.DONUT)
            public void onFinish() {
                tvGrmCountDown.setText("0");
                if(kq_grm == da){
                    fillColor(kq_grm);
                    tv_grm_state.setText("well done");
                    btn_next_grammar.setVisibility(View.VISIBLE);
                    String toSpeak = "well done";
                    Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    fillColor(kq_grm);
                    tv_grm_state.setText("Oh no =(( ");
                    btn_save_grammar.setVisibility(View.VISIBLE);
                    btn_dont_save_grammar.setVisibility(View.VISIBLE);
                }
                btn_ok_grammar.setVisibility(View.GONE);
                tvGrmCountDown.setVisibility(View.GONE);
                tv_grm_state.setVisibility(View.VISIBLE);

            }

        }.start();

    }


    public void fillColor(int fico){
        if(fico == 1){
            btn_grm_a.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 2){
            btn_grm_b.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 3){
            btn_grm_c.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 4){
            btn_grm_d.setBackgroundResource(R.drawable.btn_true_answer);
        }
    }

    public int RandomQuestion(int min, int max) {

        Random r = new Random();
        return min+r.nextInt(max-min);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //Intent intent = new Intent()
    }
}
