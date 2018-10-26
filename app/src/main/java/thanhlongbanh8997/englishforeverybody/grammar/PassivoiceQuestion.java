package thanhlongbanh8997.englishforeverybody.grammar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
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

public class PassivoiceQuestion extends AppCompatActivity {
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


    public int random = 0;
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_passive_voice_question);
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


        random = RandomQuestion(30,33);
        switch (random){
            case 30:
                tvQuestion.setText(R.string.grm_c31);
                btn_grm_a.setText(R.string.grm_c31a);
                btn_grm_b.setText(R.string.grm_c31b);
                btn_grm_c.setText(R.string.grm_c31c);
                btn_grm_d.setText(R.string.grm_c31d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac31)));
                countDownTimer(timeCountDown15);
                break;
            case 31:
                tvQuestion.setText(R.string.grm_c31);
                btn_grm_a.setText(R.string.grm_c31a);
                btn_grm_b.setText(R.string.grm_c31b);
                btn_grm_c.setText(R.string.grm_c31c);
                btn_grm_d.setText(R.string.grm_c31d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac31)));
                countDownTimer(timeCountDown15);
                break;
            case 32:
                tvQuestion.setText(R.string.grm_c32);
                btn_grm_a.setText(R.string.grm_c32a);
                btn_grm_b.setText(R.string.grm_c32b);
                btn_grm_c.setText(R.string.grm_c32c);
                btn_grm_d.setText(R.string.grm_c32d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac32)));
                countDownTimer(timeCountDown15);
                break;
            case 33:
                tvQuestion.setText(R.string.grm_c32);
                btn_grm_a.setText(R.string.grm_c32a);
                btn_grm_b.setText(R.string.grm_c32b);
                btn_grm_c.setText(R.string.grm_c32c);
                btn_grm_d.setText(R.string.grm_c32d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac32)));
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
                        random = RandomQuestion(32,35);
                        kq_grm = 0;
                        switch (random){
                            case 32:
                                tvQuestion.setText(R.string.grm_c33);
                                btn_grm_a.setText(R.string.grm_c33a);
                                btn_grm_b.setText(R.string.grm_c33b);
                                btn_grm_c.setText(R.string.grm_c33c);
                                btn_grm_d.setText(R.string.grm_c33d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac3)));
                                break;
                            case 33:
                                tvQuestion.setText(R.string.grm_c33);
                                btn_grm_a.setText(R.string.grm_c33a);
                                btn_grm_b.setText(R.string.grm_c33b);
                                btn_grm_c.setText(R.string.grm_c33c);
                                btn_grm_d.setText(R.string.grm_c33d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac33)));
                                break;
                            case 34:
                                tvQuestion.setText(R.string.grm_c34);
                                btn_grm_a.setText(R.string.grm_c34a);
                                btn_grm_b.setText(R.string.grm_c34b);
                                btn_grm_c.setText(R.string.grm_c34c);
                                btn_grm_d.setText(R.string.grm_c34d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac34)));
                                break;
                            case 35:
                                tvQuestion.setText(R.string.grm_c34);
                                btn_grm_a.setText(R.string.grm_c34a);
                                btn_grm_b.setText(R.string.grm_c34b);
                                btn_grm_c.setText(R.string.grm_c34c);
                                btn_grm_d.setText(R.string.grm_c34d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac34)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau1);
                        break;
                    case 2:
                        i++;
                        random = RandomQuestion(34,37);
                        switch (random){
                            case 34:
                                tvQuestion.setText(R.string.grm_c35);
                                btn_grm_a.setText(R.string.grm_c35a);
                                btn_grm_b.setText(R.string.grm_c35b);
                                btn_grm_c.setText(R.string.grm_c35c);
                                btn_grm_d.setText(R.string.grm_c35d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac35)));
                                break;
                            case 35:
                                tvQuestion.setText(R.string.grm_c35);
                                btn_grm_a.setText(R.string.grm_c35a);
                                btn_grm_b.setText(R.string.grm_c35b);
                                btn_grm_c.setText(R.string.grm_c35c);
                                btn_grm_d.setText(R.string.grm_c35d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac35)));
                                break;
                            case 36:
                                tvQuestion.setText(R.string.grm_c36);
                                btn_grm_a.setText(R.string.grm_c36a);
                                btn_grm_b.setText(R.string.grm_c36b);
                                btn_grm_c.setText(R.string.grm_c36c);
                                btn_grm_d.setText(R.string.grm_c36d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac36)));
                                break;
                            case 37:
                                tvQuestion.setText(R.string.grm_c36);
                                btn_grm_a.setText(R.string.grm_c36a);
                                btn_grm_b.setText(R.string.grm_c36b);
                                btn_grm_c.setText(R.string.grm_c36c);
                                btn_grm_d.setText(R.string.grm_c36d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac36)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau2);
                        break;
                    case 3:
                        i++;
                        random = RandomQuestion(36,39);
                        switch (random){
                            case 36:
                                tvQuestion.setText(R.string.grm_c37);
                                btn_grm_a.setText(R.string.grm_c37a);
                                btn_grm_b.setText(R.string.grm_c37b);
                                btn_grm_c.setText(R.string.grm_c37c);
                                btn_grm_d.setText(R.string.grm_c37d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac37)));
                                break;
                            case 37:
                                tvQuestion.setText(R.string.grm_c37);
                                btn_grm_a.setText(R.string.grm_c37a);
                                btn_grm_b.setText(R.string.grm_c37b);
                                btn_grm_c.setText(R.string.grm_c37c);
                                btn_grm_d.setText(R.string.grm_c37d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac37)));
                                break;
                            case 38:
                                tvQuestion.setText(R.string.grm_c38);
                                btn_grm_a.setText(R.string.grm_c38a);
                                btn_grm_b.setText(R.string.grm_c38b);
                                btn_grm_c.setText(R.string.grm_c38c);
                                btn_grm_d.setText(R.string.grm_c38d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac38)));
                                break;
                            case 39:
                                tvQuestion.setText(R.string.grm_c38);
                                btn_grm_a.setText(R.string.grm_c38a);
                                btn_grm_b.setText(R.string.grm_c38b);
                                btn_grm_c.setText(R.string.grm_c38c);
                                btn_grm_d.setText(R.string.grm_c38d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac38)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau3);
                        break;
                    case 4:
                        i++;
                        random = RandomQuestion(38,41);
                        switch (random){
                            case 38:
                                tvQuestion.setText(R.string.grm_c39);
                                btn_grm_a.setText(R.string.grm_c39a);
                                btn_grm_b.setText(R.string.grm_c39b);
                                btn_grm_c.setText(R.string.grm_c39c);
                                btn_grm_d.setText(R.string.grm_c39d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac39)));
                                break;
                            case 39:
                                tvQuestion.setText(R.string.grm_c39);
                                btn_grm_a.setText(R.string.grm_c39a);
                                btn_grm_b.setText(R.string.grm_c39b);
                                btn_grm_c.setText(R.string.grm_c39c);
                                btn_grm_d.setText(R.string.grm_c39d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac39)));
                                break;
                            case 40:
                                tvQuestion.setText(R.string.grm_c40);
                                btn_grm_a.setText(R.string.grm_c40a);
                                btn_grm_b.setText(R.string.grm_c40b);
                                btn_grm_c.setText(R.string.grm_c40c);
                                btn_grm_d.setText(R.string.grm_c40d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac40)));
                                break;
                            case 41:
                                tvQuestion.setText(R.string.grm_c40);
                                btn_grm_a.setText(R.string.grm_c40a);
                                btn_grm_b.setText(R.string.grm_c40b);
                                btn_grm_c.setText(R.string.grm_c40c);
                                btn_grm_d.setText(R.string.grm_c40d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac40)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau4);
                        break;
                    case 5:
                        i++;
                        random = RandomQuestion(40,43);
                        switch (random){
                            case 40:
                                tvQuestion.setText(R.string.grm_c41);
                                btn_grm_a.setText(R.string.grm_c41a);
                                btn_grm_b.setText(R.string.grm_c41b);
                                btn_grm_c.setText(R.string.grm_c41c);
                                btn_grm_d.setText(R.string.grm_c41d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac41)));
                                break;
                            case 41:
                                tvQuestion.setText(R.string.grm_c41);
                                btn_grm_a.setText(R.string.grm_c41a);
                                btn_grm_b.setText(R.string.grm_c41b);
                                btn_grm_c.setText(R.string.grm_c41c);
                                btn_grm_d.setText(R.string.grm_c41d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac41)));
                                break;
                            case 42:
                                tvQuestion.setText(R.string.grm_c42);
                                btn_grm_a.setText(R.string.grm_c42a);
                                btn_grm_b.setText(R.string.grm_c42b);
                                btn_grm_c.setText(R.string.grm_c42c);
                                btn_grm_d.setText(R.string.grm_c42d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac42)));
                                break;
                            case 43:
                                tvQuestion.setText(R.string.grm_c42);
                                btn_grm_a.setText(R.string.grm_c42a);
                                btn_grm_b.setText(R.string.grm_c42b);
                                btn_grm_c.setText(R.string.grm_c42c);
                                btn_grm_d.setText(R.string.grm_c42d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac42)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau5);
                        break;
                    case 6:
                        i++;
                        random = RandomQuestion(42,45);
                        switch (random){
                            case 42:
                                tvQuestion.setText(R.string.grm_c43);
                                btn_grm_a.setText(R.string.grm_c43a);
                                btn_grm_b.setText(R.string.grm_c43b);
                                btn_grm_c.setText(R.string.grm_c43c);
                                btn_grm_d.setText(R.string.grm_c43d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac43)));
                                break;
                            case 43:
                                tvQuestion.setText(R.string.grm_c43);
                                btn_grm_a.setText(R.string.grm_c43a);
                                btn_grm_b.setText(R.string.grm_c43b);
                                btn_grm_c.setText(R.string.grm_c43c);
                                btn_grm_d.setText(R.string.grm_c43d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac43)));
                                break;
                            case 44:
                                tvQuestion.setText(R.string.grm_c44);
                                btn_grm_a.setText(R.string.grm_c44a);
                                btn_grm_b.setText(R.string.grm_c44b);
                                btn_grm_c.setText(R.string.grm_c44c);
                                btn_grm_d.setText(R.string.grm_c44d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac44)));
                                break;
                            case 45:
                                tvQuestion.setText(R.string.grm_c44);
                                btn_grm_a.setText(R.string.grm_c44a);
                                btn_grm_b.setText(R.string.grm_c44b);
                                btn_grm_c.setText(R.string.grm_c44c);
                                btn_grm_d.setText(R.string.grm_c44d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac44)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau6);
                        break;
                    case 7:
                        i++;
                        random = RandomQuestion(44,47);
                        switch (random){
                            case 44:
                                tvQuestion.setText(R.string.grm_c45);
                                btn_grm_a.setText(R.string.grm_c45a);
                                btn_grm_b.setText(R.string.grm_c45b);
                                btn_grm_c.setText(R.string.grm_c45c);
                                btn_grm_d.setText(R.string.grm_c45d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac45)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c45);
                                btn_grm_a.setText(R.string.grm_c45a);
                                btn_grm_b.setText(R.string.grm_c45b);
                                btn_grm_c.setText(R.string.grm_c45c);
                                btn_grm_d.setText(R.string.grm_c45d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac45)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c46);
                                btn_grm_a.setText(R.string.grm_c46a);
                                btn_grm_b.setText(R.string.grm_c46b);
                                btn_grm_c.setText(R.string.grm_c46c);
                                btn_grm_d.setText(R.string.grm_c46d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac46)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c46);
                                btn_grm_a.setText(R.string.grm_c46a);
                                btn_grm_b.setText(R.string.grm_c46b);
                                btn_grm_c.setText(R.string.grm_c46c);
                                btn_grm_d.setText(R.string.grm_c46d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac46)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau7);
                        break;
                    case 8:
                        i++;
                        random = RandomQuestion(46,49);
                        switch (random){
                            case 46:
                                tvQuestion.setText(R.string.grm_c47);
                                btn_grm_a.setText(R.string.grm_c47a);
                                btn_grm_b.setText(R.string.grm_c47b);
                                btn_grm_c.setText(R.string.grm_c47c);
                                btn_grm_d.setText(R.string.grm_c47d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac47)));
                                break;
                            case 47:
                                tvQuestion.setText(R.string.grm_c47);
                                btn_grm_a.setText(R.string.grm_c47a);
                                btn_grm_b.setText(R.string.grm_c47b);
                                btn_grm_c.setText(R.string.grm_c47c);
                                btn_grm_d.setText(R.string.grm_c47d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac47)));
                                break;
                            case 48:
                                tvQuestion.setText(R.string.grm_c48);
                                btn_grm_a.setText(R.string.grm_c48a);
                                btn_grm_b.setText(R.string.grm_c48b);
                                btn_grm_c.setText(R.string.grm_c48c);
                                btn_grm_d.setText(R.string.grm_c48d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac48)));
                                break;
                            case 49:
                                tvQuestion.setText(R.string.grm_c48);
                                btn_grm_a.setText(R.string.grm_c48a);
                                btn_grm_b.setText(R.string.grm_c48b);
                                btn_grm_c.setText(R.string.grm_c48c);
                                btn_grm_d.setText(R.string.grm_c48d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac48)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau8);
                        break;
                    case 9:
                        i++;
                        random = RandomQuestion(48,51);
                        switch (random){
                            case 48:
                                tvQuestion.setText(R.string.grm_c49);
                                btn_grm_a.setText(R.string.grm_c49a);
                                btn_grm_b.setText(R.string.grm_c49b);
                                btn_grm_c.setText(R.string.grm_c49c);
                                btn_grm_d.setText(R.string.grm_c49d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac49)));
                                break;
                            case 49:
                                tvQuestion.setText(R.string.grm_c49);
                                btn_grm_a.setText(R.string.grm_c49a);
                                btn_grm_b.setText(R.string.grm_c49b);
                                btn_grm_c.setText(R.string.grm_c49c);
                                btn_grm_d.setText(R.string.grm_c49d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac49)));
                                break;
                            case 50:
                                tvQuestion.setText(R.string.grm_c50);
                                btn_grm_a.setText(R.string.grm_c50a);
                                btn_grm_b.setText(R.string.grm_c50b);
                                btn_grm_c.setText(R.string.grm_c50c);
                                btn_grm_d.setText(R.string.grm_c50d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac50)));
                                break;
                            case 51:
                                tvQuestion.setText(R.string.grm_c50);
                                btn_grm_a.setText(R.string.grm_c50a);
                                btn_grm_b.setText(R.string.grm_c50b);
                                btn_grm_c.setText(R.string.grm_c50c);
                                btn_grm_d.setText(R.string.grm_c50d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac50)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau9);
                        break;
                    case 10:
                        i++;
                        random = RandomQuestion(50,53);
                        switch (random){
                            case 50:
                                tvQuestion.setText(R.string.grm_c51);
                                btn_grm_a.setText(R.string.grm_c51a);
                                btn_grm_b.setText(R.string.grm_c51b);
                                btn_grm_c.setText(R.string.grm_c51c);
                                btn_grm_d.setText(R.string.grm_c51d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac51)));
                                break;
                            case 51:
                                tvQuestion.setText(R.string.grm_c51);
                                btn_grm_a.setText(R.string.grm_c51a);
                                btn_grm_b.setText(R.string.grm_c51b);
                                btn_grm_c.setText(R.string.grm_c51c);
                                btn_grm_d.setText(R.string.grm_c51d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac51)));
                                break;
                            case 52:
                                tvQuestion.setText(R.string.grm_c52);
                                btn_grm_a.setText(R.string.grm_c52a);
                                btn_grm_b.setText(R.string.grm_c52b);
                                btn_grm_c.setText(R.string.grm_c52c);
                                btn_grm_d.setText(R.string.grm_c52d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac52)));
                                break;
                            case 53:
                                tvQuestion.setText(R.string.grm_c52);
                                btn_grm_a.setText(R.string.grm_c52a);
                                btn_grm_b.setText(R.string.grm_c52b);
                                btn_grm_c.setText(R.string.grm_c52c);
                                btn_grm_d.setText(R.string.grm_c52d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac52)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau10);
                        break;
                    case 11:
                        i++;
                        random = RandomQuestion(52,55);
                        switch (random){
                            case 52:
                                tvQuestion.setText(R.string.grm_c53);
                                btn_grm_a.setText(R.string.grm_c53a);
                                btn_grm_b.setText(R.string.grm_c53b);
                                btn_grm_c.setText(R.string.grm_c53c);
                                btn_grm_d.setText(R.string.grm_c53d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac53)));
                                break;
                            case 53:
                                tvQuestion.setText(R.string.grm_c53);
                                btn_grm_a.setText(R.string.grm_c53a);
                                btn_grm_b.setText(R.string.grm_c53b);
                                btn_grm_c.setText(R.string.grm_c53c);
                                btn_grm_d.setText(R.string.grm_c53d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac53)));
                                break;
                            case 54:
                                tvQuestion.setText(R.string.grm_c54);
                                btn_grm_a.setText(R.string.grm_c54a);
                                btn_grm_b.setText(R.string.grm_c54b);
                                btn_grm_c.setText(R.string.grm_c54c);
                                btn_grm_d.setText(R.string.grm_c54d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac54)));
                                break;
                            case 55:
                                tvQuestion.setText(R.string.grm_c54);
                                btn_grm_a.setText(R.string.grm_c54a);
                                btn_grm_b.setText(R.string.grm_c54b);
                                btn_grm_c.setText(R.string.grm_c54c);
                                btn_grm_d.setText(R.string.grm_c54d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac54)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau11);
                        break;
                    case 12:
                        i++;
                        random = RandomQuestion(54,57);
                        switch (random){
                            case 54:
                                tvQuestion.setText(R.string.grm_c55);
                                btn_grm_a.setText(R.string.grm_c55a);
                                btn_grm_b.setText(R.string.grm_c55b);
                                btn_grm_c.setText(R.string.grm_c55c);
                                btn_grm_d.setText(R.string.grm_c25d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac55)));
                                break;
                            case 55:
                                tvQuestion.setText(R.string.grm_c55);
                                btn_grm_a.setText(R.string.grm_c55a);
                                btn_grm_b.setText(R.string.grm_c55b);
                                btn_grm_c.setText(R.string.grm_c55c);
                                btn_grm_d.setText(R.string.grm_c25d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac55)));
                                break;
                            case 56:
                                tvQuestion.setText(R.string.grm_c56);
                                btn_grm_a.setText(R.string.grm_c56a);
                                btn_grm_b.setText(R.string.grm_c56b);
                                btn_grm_c.setText(R.string.grm_c56c);
                                btn_grm_d.setText(R.string.grm_c56d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac56)));
                                break;
                            case 57:
                                tvQuestion.setText(R.string.grm_c56);
                                btn_grm_a.setText(R.string.grm_c56a);
                                btn_grm_b.setText(R.string.grm_c56b);
                                btn_grm_c.setText(R.string.grm_c56c);
                                btn_grm_d.setText(R.string.grm_c56d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac56)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau12);
                        break;
                    case 13:
                        i++;
                        random = RandomQuestion(56,59);
                        switch (random){
                            case 56:
                                tvQuestion.setText(R.string.grm_c57);
                                btn_grm_a.setText(R.string.grm_c57a);
                                btn_grm_b.setText(R.string.grm_c57b);
                                btn_grm_c.setText(R.string.grm_c57c);
                                btn_grm_d.setText(R.string.grm_c57d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac57)));
                                break;
                            case 57:
                                tvQuestion.setText(R.string.grm_c57);
                                btn_grm_a.setText(R.string.grm_c57a);
                                btn_grm_b.setText(R.string.grm_c57b);
                                btn_grm_c.setText(R.string.grm_c57c);
                                btn_grm_d.setText(R.string.grm_c57d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac57)));
                                break;
                            case 58:
                                tvQuestion.setText(R.string.grm_c58);
                                btn_grm_a.setText(R.string.grm_c58a);
                                btn_grm_b.setText(R.string.grm_c58b);
                                btn_grm_c.setText(R.string.grm_c58c);
                                btn_grm_d.setText(R.string.grm_c58d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac58)));
                                break;
                            case 59:
                                tvQuestion.setText(R.string.grm_c58);
                                btn_grm_a.setText(R.string.grm_c58a);
                                btn_grm_b.setText(R.string.grm_c58b);
                                btn_grm_c.setText(R.string.grm_c58c);
                                btn_grm_d.setText(R.string.grm_c58d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac58)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau13);
                        break;
                    case 14:
                        i++;
                        random = RandomQuestion(57,60);
                        switch (random){
                            case 27:
                                tvQuestion.setText(R.string.grm_c59);
                                btn_grm_a.setText(R.string.grm_c59a);
                                btn_grm_b.setText(R.string.grm_c59b);
                                btn_grm_c.setText(R.string.grm_c59c);
                                btn_grm_d.setText(R.string.grm_c59d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac59)));
                                break;
                            case 28:
                                tvQuestion.setText(R.string.grm_c59);
                                btn_grm_a.setText(R.string.grm_c59a);
                                btn_grm_b.setText(R.string.grm_c59b);
                                btn_grm_c.setText(R.string.grm_c59c);
                                btn_grm_d.setText(R.string.grm_c59d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac59)));
                                break;
                            case 29:
                                tvQuestion.setText(R.string.grm_c60);
                                btn_grm_a.setText(R.string.grm_c60a);
                                btn_grm_b.setText(R.string.grm_c60b);
                                btn_grm_c.setText(R.string.grm_c60c);
                                btn_grm_d.setText(R.string.grm_c60d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac60)));
                                break;
                            case 30:
                                tvQuestion.setText(R.string.grm_c60);
                                btn_grm_a.setText(R.string.grm_c60a);
                                btn_grm_b.setText(R.string.grm_c60b);
                                btn_grm_c.setText(R.string.grm_c60c);
                                btn_grm_d.setText(R.string.grm_c60d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac60)));
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
            builder = new AlertDialog.Builder(PassivoiceQuestion.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(PassivoiceQuestion.this);
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
    }
}
