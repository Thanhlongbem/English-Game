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

public class RelativeClause extends AppCompatActivity {
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
        setContentView(R.layout.grammar_relative_clause);
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
                highScore = sharedPreferences.getInt("GRM_rc",0);
                if( i-1 > highScore ){
                    highScore = i-1;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("GRM_rc", highScore);
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
                tvQuestion.setText(R.string.grm_c91);
                btn_grm_a.setText(R.string.grm_c91a);
                btn_grm_b.setText(R.string.grm_c91b);
                btn_grm_c.setText(R.string.grm_c91c);
                btn_grm_d.setText(R.string.grm_c91d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac91)));
                countDownTimer(timeCountDown15);
                break;
            case 1:
                tvQuestion.setText(R.string.grm_c91);
                btn_grm_a.setText(R.string.grm_c91a);
                btn_grm_b.setText(R.string.grm_c91b);
                btn_grm_c.setText(R.string.grm_c91c);
                btn_grm_d.setText(R.string.grm_c91d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac91)));
                countDownTimer(timeCountDown15);
                break;
            case 2:
                tvQuestion.setText(R.string.grm_c92);
                btn_grm_a.setText(R.string.grm_c92a);
                btn_grm_b.setText(R.string.grm_c92b);
                btn_grm_c.setText(R.string.grm_c92c);
                btn_grm_d.setText(R.string.grm_c92d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac92)));
                countDownTimer(timeCountDown15);
                break;
            case 3:
                tvQuestion.setText(R.string.grm_c92);
                btn_grm_a.setText(R.string.grm_c92a);
                btn_grm_b.setText(R.string.grm_c92b);
                btn_grm_c.setText(R.string.grm_c92c);
                btn_grm_d.setText(R.string.grm_c92d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac92)));
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
                                tvQuestion.setText(R.string.grm_c93);
                                btn_grm_a.setText(R.string.grm_c93a);
                                btn_grm_b.setText(R.string.grm_c93b);
                                btn_grm_c.setText(R.string.grm_c93c);
                                btn_grm_d.setText(R.string.grm_c93d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac93)));
                                break;
                            case 3:
                                tvQuestion.setText(R.string.grm_c93);
                                btn_grm_a.setText(R.string.grm_c93a);
                                btn_grm_b.setText(R.string.grm_c93b);
                                btn_grm_c.setText(R.string.grm_c93c);
                                btn_grm_d.setText(R.string.grm_c93d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac93)));
                                break;
                            case 4:
                                tvQuestion.setText(R.string.grm_c94);
                                btn_grm_a.setText(R.string.grm_c94a);
                                btn_grm_b.setText(R.string.grm_c94b);
                                btn_grm_c.setText(R.string.grm_c94c);
                                btn_grm_d.setText(R.string.grm_c94d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac94)));
                                break;
                            case 5:
                                tvQuestion.setText(R.string.grm_c94);
                                btn_grm_a.setText(R.string.grm_c94a);
                                btn_grm_b.setText(R.string.grm_c94b);
                                btn_grm_c.setText(R.string.grm_c94c);
                                btn_grm_d.setText(R.string.grm_c94d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac94)));
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
                                tvQuestion.setText(R.string.grm_c95);
                                btn_grm_a.setText(R.string.grm_c95a);
                                btn_grm_b.setText(R.string.grm_c95b);
                                btn_grm_c.setText(R.string.grm_c95c);
                                btn_grm_d.setText(R.string.grm_c95d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac95)));
                                break;
                            case 5:
                                tvQuestion.setText(R.string.grm_c95);
                                btn_grm_a.setText(R.string.grm_c95a);
                                btn_grm_b.setText(R.string.grm_c95b);
                                btn_grm_c.setText(R.string.grm_c95c);
                                btn_grm_d.setText(R.string.grm_c95d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac95)));
                                break;
                            case 6:
                                tvQuestion.setText(R.string.grm_c96);
                                btn_grm_a.setText(R.string.grm_c96a);
                                btn_grm_b.setText(R.string.grm_c96b);
                                btn_grm_c.setText(R.string.grm_c96c);
                                btn_grm_d.setText(R.string.grm_c96d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac96)));
                                break;
                            case 7:
                                tvQuestion.setText(R.string.grm_c96);
                                btn_grm_a.setText(R.string.grm_c96a);
                                btn_grm_b.setText(R.string.grm_c96b);
                                btn_grm_c.setText(R.string.grm_c96c);
                                btn_grm_d.setText(R.string.grm_c96d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac96)));
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
                                tvQuestion.setText(R.string.grm_c97);
                                btn_grm_a.setText(R.string.grm_c97a);
                                btn_grm_b.setText(R.string.grm_c97b);
                                btn_grm_c.setText(R.string.grm_c97c);
                                btn_grm_d.setText(R.string.grm_c97d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac97)));
                                break;
                            case 7:
                                tvQuestion.setText(R.string.grm_c97);
                                btn_grm_a.setText(R.string.grm_c97a);
                                btn_grm_b.setText(R.string.grm_c97b);
                                btn_grm_c.setText(R.string.grm_c97c);
                                btn_grm_d.setText(R.string.grm_c97d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac97)));
                                break;
                            case 8:
                                tvQuestion.setText(R.string.grm_c98);
                                btn_grm_a.setText(R.string.grm_c98a);
                                btn_grm_b.setText(R.string.grm_c98b);
                                btn_grm_c.setText(R.string.grm_c98c);
                                btn_grm_d.setText(R.string.grm_c98d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac98)));
                                break;
                            case 9:
                                tvQuestion.setText(R.string.grm_c98);
                                btn_grm_a.setText(R.string.grm_c98a);
                                btn_grm_b.setText(R.string.grm_c98b);
                                btn_grm_c.setText(R.string.grm_c98c);
                                btn_grm_d.setText(R.string.grm_c98d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac98)));
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
                                tvQuestion.setText(R.string.grm_c99);
                                btn_grm_a.setText(R.string.grm_c99a);
                                btn_grm_b.setText(R.string.grm_c99b);
                                btn_grm_c.setText(R.string.grm_c99c);
                                btn_grm_d.setText(R.string.grm_c99d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac99)));
                                break;
                            case 9:
                                tvQuestion.setText(R.string.grm_c99);
                                btn_grm_a.setText(R.string.grm_c99a);
                                btn_grm_b.setText(R.string.grm_c99b);
                                btn_grm_c.setText(R.string.grm_c99c);
                                btn_grm_d.setText(R.string.grm_c99d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac99)));
                                break;
                            case 10:
                                tvQuestion.setText(R.string.grm_c100);
                                btn_grm_a.setText(R.string.grm_c100a);
                                btn_grm_b.setText(R.string.grm_c100b);
                                btn_grm_c.setText(R.string.grm_c100c);
                                btn_grm_d.setText(R.string.grm_c100d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac100)));
                                break;
                            case 11:
                                tvQuestion.setText(R.string.grm_c100);
                                btn_grm_a.setText(R.string.grm_c100a);
                                btn_grm_b.setText(R.string.grm_c100b);
                                btn_grm_c.setText(R.string.grm_c100c);
                                btn_grm_d.setText(R.string.grm_c100d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac100)));
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
                                tvQuestion.setText(R.string.grm_c101);
                                btn_grm_a.setText(R.string.grm_c101a);
                                btn_grm_b.setText(R.string.grm_c101b);
                                btn_grm_c.setText(R.string.grm_c101c);
                                btn_grm_d.setText(R.string.grm_c101d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac101)));
                                break;
                            case 11:
                                tvQuestion.setText(R.string.grm_c101);
                                btn_grm_a.setText(R.string.grm_c101a);
                                btn_grm_b.setText(R.string.grm_c101b);
                                btn_grm_c.setText(R.string.grm_c101c);
                                btn_grm_d.setText(R.string.grm_c101d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac101)));
                                break;
                            case 12:
                                tvQuestion.setText(R.string.grm_c102);
                                btn_grm_a.setText(R.string.grm_c102a);
                                btn_grm_b.setText(R.string.grm_c102b);
                                btn_grm_c.setText(R.string.grm_c102c);
                                btn_grm_d.setText(R.string.grm_c102d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac102)));
                                break;
                            case 13:
                                tvQuestion.setText(R.string.grm_c102);
                                btn_grm_a.setText(R.string.grm_c102a);
                                btn_grm_b.setText(R.string.grm_c102b);
                                btn_grm_c.setText(R.string.grm_c102c);
                                btn_grm_d.setText(R.string.grm_c102d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac102)));
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
                                tvQuestion.setText(R.string.grm_c103);
                                btn_grm_a.setText(R.string.grm_c103a);
                                btn_grm_b.setText(R.string.grm_c103b);
                                btn_grm_c.setText(R.string.grm_c103c);
                                btn_grm_d.setText(R.string.grm_c103d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac103)));
                                break;
                            case 13:
                                tvQuestion.setText(R.string.grm_c103);
                                btn_grm_a.setText(R.string.grm_c103a);
                                btn_grm_b.setText(R.string.grm_c103b);
                                btn_grm_c.setText(R.string.grm_c103c);
                                btn_grm_d.setText(R.string.grm_c103d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac103)));
                                break;
                            case 14:
                                tvQuestion.setText(R.string.grm_c104);
                                btn_grm_a.setText(R.string.grm_c104a);
                                btn_grm_b.setText(R.string.grm_c104b);
                                btn_grm_c.setText(R.string.grm_c104c);
                                btn_grm_d.setText(R.string.grm_c104d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac104)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c104);
                                btn_grm_a.setText(R.string.grm_c104a);
                                btn_grm_b.setText(R.string.grm_c104b);
                                btn_grm_c.setText(R.string.grm_c104c);
                                btn_grm_d.setText(R.string.grm_c104d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac104)));
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
                                tvQuestion.setText(R.string.grm_c105);
                                btn_grm_a.setText(R.string.grm_c105a);
                                btn_grm_b.setText(R.string.grm_c105b);
                                btn_grm_c.setText(R.string.grm_c105c);
                                btn_grm_d.setText(R.string.grm_c105d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac105)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c105);
                                btn_grm_a.setText(R.string.grm_c105a);
                                btn_grm_b.setText(R.string.grm_c105b);
                                btn_grm_c.setText(R.string.grm_c105c);
                                btn_grm_d.setText(R.string.grm_c105d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac105)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c106);
                                btn_grm_a.setText(R.string.grm_c106a);
                                btn_grm_b.setText(R.string.grm_c106b);
                                btn_grm_c.setText(R.string.grm_c106c);
                                btn_grm_d.setText(R.string.grm_c106d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac106)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c106);
                                btn_grm_a.setText(R.string.grm_c106a);
                                btn_grm_b.setText(R.string.grm_c106b);
                                btn_grm_c.setText(R.string.grm_c106c);
                                btn_grm_d.setText(R.string.grm_c106d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac106)));
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
                                tvQuestion.setText(R.string.grm_c107);
                                btn_grm_a.setText(R.string.grm_c107a);
                                btn_grm_b.setText(R.string.grm_c107b);
                                btn_grm_c.setText(R.string.grm_c107c);
                                btn_grm_d.setText(R.string.grm_c107d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac107)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c107);
                                btn_grm_a.setText(R.string.grm_c107a);
                                btn_grm_b.setText(R.string.grm_c107b);
                                btn_grm_c.setText(R.string.grm_c107c);
                                btn_grm_d.setText(R.string.grm_c107d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac107)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c108);
                                btn_grm_a.setText(R.string.grm_c108a);
                                btn_grm_b.setText(R.string.grm_c108b);
                                btn_grm_c.setText(R.string.grm_c108c);
                                btn_grm_d.setText(R.string.grm_c108d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac108)));
                                break;
                            case 18:
                                tvQuestion.setText(R.string.grm_c108);
                                btn_grm_a.setText(R.string.grm_c108a);
                                btn_grm_b.setText(R.string.grm_c108b);
                                btn_grm_c.setText(R.string.grm_c108c);
                                btn_grm_d.setText(R.string.grm_c108d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac108)));
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
                                tvQuestion.setText(R.string.grm_c109);
                                btn_grm_a.setText(R.string.grm_c109a);
                                btn_grm_b.setText(R.string.grm_c109b);
                                btn_grm_c.setText(R.string.grm_c109c);
                                btn_grm_d.setText(R.string.grm_c109d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac109)));
                                break;
                            case 19:
                                tvQuestion.setText(R.string.grm_c109);
                                btn_grm_a.setText(R.string.grm_c109a);
                                btn_grm_b.setText(R.string.grm_c109b);
                                btn_grm_c.setText(R.string.grm_c109c);
                                btn_grm_d.setText(R.string.grm_c109d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac109)));
                                break;
                            case 20:
                                tvQuestion.setText(R.string.grm_c110);
                                btn_grm_a.setText(R.string.grm_c110a);
                                btn_grm_b.setText(R.string.grm_c110b);
                                btn_grm_c.setText(R.string.grm_c110c);
                                btn_grm_d.setText(R.string.grm_c110d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac110)));
                                break;
                            case 21:
                                tvQuestion.setText(R.string.grm_c110);
                                btn_grm_a.setText(R.string.grm_c110a);
                                btn_grm_b.setText(R.string.grm_c110b);
                                btn_grm_c.setText(R.string.grm_c110c);
                                btn_grm_d.setText(R.string.grm_c110d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac110)));
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
                                tvQuestion.setText(R.string.grm_c111);
                                btn_grm_a.setText(R.string.grm_c111a);
                                btn_grm_b.setText(R.string.grm_c111b);
                                btn_grm_c.setText(R.string.grm_c111c);
                                btn_grm_d.setText(R.string.grm_c111d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac111)));
                                break;
                            case 21:
                                tvQuestion.setText(R.string.grm_c111);
                                btn_grm_a.setText(R.string.grm_c111a);
                                btn_grm_b.setText(R.string.grm_c111b);
                                btn_grm_c.setText(R.string.grm_c111c);
                                btn_grm_d.setText(R.string.grm_c111d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac111)));
                                break;
                            case 22:
                                tvQuestion.setText(R.string.grm_c112);
                                btn_grm_a.setText(R.string.grm_c112a);
                                btn_grm_b.setText(R.string.grm_c112b);
                                btn_grm_c.setText(R.string.grm_c112c);
                                btn_grm_d.setText(R.string.grm_c112d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac112)));
                                break;
                            case 23:
                                tvQuestion.setText(R.string.grm_c112);
                                btn_grm_a.setText(R.string.grm_c112a);
                                btn_grm_b.setText(R.string.grm_c112b);
                                btn_grm_c.setText(R.string.grm_c112c);
                                btn_grm_d.setText(R.string.grm_c112d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac112)));
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
                                tvQuestion.setText(R.string.grm_c113);
                                btn_grm_a.setText(R.string.grm_c113a);
                                btn_grm_b.setText(R.string.grm_c113b);
                                btn_grm_c.setText(R.string.grm_c113c);
                                btn_grm_d.setText(R.string.grm_c113d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac113)));
                                break;
                            case 23:
                                tvQuestion.setText(R.string.grm_c113);
                                btn_grm_a.setText(R.string.grm_c113a);
                                btn_grm_b.setText(R.string.grm_c113b);
                                btn_grm_c.setText(R.string.grm_c113c);
                                btn_grm_d.setText(R.string.grm_c113d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac113)));
                                break;
                            case 24:
                                tvQuestion.setText(R.string.grm_c114);
                                btn_grm_a.setText(R.string.grm_c114a);
                                btn_grm_b.setText(R.string.grm_c114b);
                                btn_grm_c.setText(R.string.grm_c114c);
                                btn_grm_d.setText(R.string.grm_c114d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac114)));
                                break;
                            case 25:
                                tvQuestion.setText(R.string.grm_c114);
                                btn_grm_a.setText(R.string.grm_c114a);
                                btn_grm_b.setText(R.string.grm_c114b);
                                btn_grm_c.setText(R.string.grm_c114c);
                                btn_grm_d.setText(R.string.grm_c114d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac114)));
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
                                tvQuestion.setText(R.string.grm_c115);
                                btn_grm_a.setText(R.string.grm_c115a);
                                btn_grm_b.setText(R.string.grm_c115b);
                                btn_grm_c.setText(R.string.grm_c115c);
                                btn_grm_d.setText(R.string.grm_c115d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac115)));
                                break;
                            case 25:
                                tvQuestion.setText(R.string.grm_c115);
                                btn_grm_a.setText(R.string.grm_c115a);
                                btn_grm_b.setText(R.string.grm_c115b);
                                btn_grm_c.setText(R.string.grm_c115c);
                                btn_grm_d.setText(R.string.grm_c115d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac115)));
                                break;
                            case 26:
                                tvQuestion.setText(R.string.grm_c116);
                                btn_grm_a.setText(R.string.grm_c116a);
                                btn_grm_b.setText(R.string.grm_c116b);
                                btn_grm_c.setText(R.string.grm_c116c);
                                btn_grm_d.setText(R.string.grm_c116d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac116)));
                                break;
                            case 27:
                                tvQuestion.setText(R.string.grm_c116);
                                btn_grm_a.setText(R.string.grm_c116a);
                                btn_grm_b.setText(R.string.grm_c116b);
                                btn_grm_c.setText(R.string.grm_c116c);
                                btn_grm_d.setText(R.string.grm_c116d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac116)));
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
                                tvQuestion.setText(R.string.grm_c117);
                                btn_grm_a.setText(R.string.grm_c117a);
                                btn_grm_b.setText(R.string.grm_c117b);
                                btn_grm_c.setText(R.string.grm_c117c);
                                btn_grm_d.setText(R.string.grm_c117d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac117)));
                                break;
                            case 27:
                                tvQuestion.setText(R.string.grm_c117);
                                btn_grm_a.setText(R.string.grm_c117a);
                                btn_grm_b.setText(R.string.grm_c117b);
                                btn_grm_c.setText(R.string.grm_c117c);
                                btn_grm_d.setText(R.string.grm_c117d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac117)));
                                break;
                            case 28:
                                tvQuestion.setText(R.string.grm_c118);
                                btn_grm_a.setText(R.string.grm_c118a);
                                btn_grm_b.setText(R.string.grm_c118b);
                                btn_grm_c.setText(R.string.grm_c118c);
                                btn_grm_d.setText(R.string.grm_c118d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac118)));
                                break;
                            case 29:
                                tvQuestion.setText(R.string.grm_c118);
                                btn_grm_a.setText(R.string.grm_c118a);
                                btn_grm_b.setText(R.string.grm_c118b);
                                btn_grm_c.setText(R.string.grm_c118c);
                                btn_grm_d.setText(R.string.grm_c118d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac118)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau13);
                        break;
                    case 14:
                        i++;
                        random = RandomQuestion(27,30);
                        switch (random){
                            case 27:
                                tvQuestion.setText(R.string.grm_c119);
                                btn_grm_a.setText(R.string.grm_c119a);
                                btn_grm_b.setText(R.string.grm_c119b);
                                btn_grm_c.setText(R.string.grm_c119c);
                                btn_grm_d.setText(R.string.grm_c119d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac119)));
                                break;
                            case 28:
                                tvQuestion.setText(R.string.grm_c119);
                                btn_grm_a.setText(R.string.grm_c119a);
                                btn_grm_b.setText(R.string.grm_c119b);
                                btn_grm_c.setText(R.string.grm_c119c);
                                btn_grm_d.setText(R.string.grm_c119d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac119)));
                                break;
                            case 29:
                                tvQuestion.setText(R.string.grm_c120);
                                btn_grm_a.setText(R.string.grm_c120a);
                                btn_grm_b.setText(R.string.grm_c120b);
                                btn_grm_c.setText(R.string.grm_c120c);
                                btn_grm_d.setText(R.string.grm_c120d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac120)));
                                break;
                            case 30:
                                tvQuestion.setText(R.string.grm_c120);
                                btn_grm_a.setText(R.string.grm_c120a);
                                btn_grm_b.setText(R.string.grm_c120b);
                                btn_grm_c.setText(R.string.grm_c120c);
                                btn_grm_d.setText(R.string.grm_c120d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac120)));
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
            builder = new AlertDialog.Builder(RelativeClause.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(RelativeClause.this);
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
    }
}
