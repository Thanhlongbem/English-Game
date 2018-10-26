package thanhlongbanh8997.englishforeverybody.grammar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
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

public class GerundAndToVerb extends AppCompatActivity {
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
    MediaPlayer mPlayer;

    public int timeCountDown15 = 30000;
    public int timeCountDown610 = 30000;
    public int timeCountDown1115 = 30000;
    public int i=1;
    public static int da = 0;
    public int kq_grm = 0;
    public int random = 0;

    public static final int S1 = R.raw.nam_cau_dau;
    public static final int S2 = R.raw.nam_cau_giua;
    public static final int S3 = R.raw.nam_cau_cuoi;
    public static final int S = R.raw.nhac_nen;


    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_gerund_and_to_verb);
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


        random = RandomQuestion(60,63);
        switch (random){
            case 60:
                tvQuestion.setText(R.string.grm_c61);
                btn_grm_a.setText(R.string.grm_c61a);
                btn_grm_b.setText(R.string.grm_c61b);
                btn_grm_c.setText(R.string.grm_c61c);
                btn_grm_d.setText(R.string.grm_c61d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac61)));
                countDownTimer(timeCountDown15);
                break;
            case 61:
                tvQuestion.setText(R.string.grm_c61);
                btn_grm_a.setText(R.string.grm_c61a);
                btn_grm_b.setText(R.string.grm_c61b);
                btn_grm_c.setText(R.string.grm_c61c);
                btn_grm_d.setText(R.string.grm_c61d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac61)));
                countDownTimer(timeCountDown15);
                break;
            case 62:
                tvQuestion.setText(R.string.grm_c62);
                btn_grm_a.setText(R.string.grm_c62a);
                btn_grm_b.setText(R.string.grm_c62b);
                btn_grm_c.setText(R.string.grm_c62c);
                btn_grm_d.setText(R.string.grm_c62d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac62)));
                countDownTimer(timeCountDown15);
                break;
            case 63:
                tvQuestion.setText(R.string.grm_c62);
                btn_grm_a.setText(R.string.grm_c62a);
                btn_grm_b.setText(R.string.grm_c62b);
                btn_grm_c.setText(R.string.grm_c62c);
                btn_grm_d.setText(R.string.grm_c62d);
                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac62)));
                countDownTimer(timeCountDown15);

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
                        random = RandomQuestion(62,65);
                        kq_grm = 0;
                        switch (random){
                            case 62:
                                tvQuestion.setText(R.string.grm_c63);
                                btn_grm_a.setText(R.string.grm_c63a);
                                btn_grm_b.setText(R.string.grm_c63b);
                                btn_grm_c.setText(R.string.grm_c63c);
                                btn_grm_d.setText(R.string.grm_c63d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac63)));
                                break;
                            case 63:
                                tvQuestion.setText(R.string.grm_c63);
                                btn_grm_a.setText(R.string.grm_c63a);
                                btn_grm_b.setText(R.string.grm_c63b);
                                btn_grm_c.setText(R.string.grm_c63c);
                                btn_grm_d.setText(R.string.grm_c63d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac63)));
                                break;
                            case 64:
                                tvQuestion.setText(R.string.grm_c64);
                                btn_grm_a.setText(R.string.grm_c64a);
                                btn_grm_b.setText(R.string.grm_c64b);
                                btn_grm_c.setText(R.string.grm_c64c);
                                btn_grm_d.setText(R.string.grm_c64d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac64)));
                                break;
                            case 65:
                                tvQuestion.setText(R.string.grm_c64);
                                btn_grm_a.setText(R.string.grm_c64a);
                                btn_grm_b.setText(R.string.grm_c64b);
                                btn_grm_c.setText(R.string.grm_c64c);
                                btn_grm_d.setText(R.string.grm_c64d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac64)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau1);
                        break;
                    case 2:
                        i++;
                        random = RandomQuestion(64,67);
                        switch (random){
                            case 64:
                                tvQuestion.setText(R.string.grm_c65);
                                btn_grm_a.setText(R.string.grm_c65a);
                                btn_grm_b.setText(R.string.grm_c65b);
                                btn_grm_c.setText(R.string.grm_c65c);
                                btn_grm_d.setText(R.string.grm_c65d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac65)));
                                break;
                            case 65:
                                tvQuestion.setText(R.string.grm_c65);
                                btn_grm_a.setText(R.string.grm_c65a);
                                btn_grm_b.setText(R.string.grm_c65b);
                                btn_grm_c.setText(R.string.grm_c65c);
                                btn_grm_d.setText(R.string.grm_c65d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac65)));
                                break;
                            case 66:
                                tvQuestion.setText(R.string.grm_c66);
                                btn_grm_a.setText(R.string.grm_c66a);
                                btn_grm_b.setText(R.string.grm_c66b);
                                btn_grm_c.setText(R.string.grm_c66c);
                                btn_grm_d.setText(R.string.grm_c66d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac66)));
                                break;
                            case 67:
                                tvQuestion.setText(R.string.grm_c66);
                                btn_grm_a.setText(R.string.grm_c66a);
                                btn_grm_b.setText(R.string.grm_c66b);
                                btn_grm_c.setText(R.string.grm_c66c);
                                btn_grm_d.setText(R.string.grm_c66d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac66)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau2);
                        break;
                    case 3:
                        i++;
                        random = RandomQuestion(66,69);
                        switch (random){
                            case 66:
                                tvQuestion.setText(R.string.grm_c67);
                                btn_grm_a.setText(R.string.grm_c67a);
                                btn_grm_b.setText(R.string.grm_c67b);
                                btn_grm_c.setText(R.string.grm_c67c);
                                btn_grm_d.setText(R.string.grm_c67d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac67)));
                                break;
                            case 67:
                                tvQuestion.setText(R.string.grm_c67);
                                btn_grm_a.setText(R.string.grm_c67a);
                                btn_grm_b.setText(R.string.grm_c67b);
                                btn_grm_c.setText(R.string.grm_c67c);
                                btn_grm_d.setText(R.string.grm_c67d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac67)));
                                break;
                            case 68:
                                tvQuestion.setText(R.string.grm_c68);
                                btn_grm_a.setText(R.string.grm_c68a);
                                btn_grm_b.setText(R.string.grm_c68b);
                                btn_grm_c.setText(R.string.grm_c68c);
                                btn_grm_d.setText(R.string.grm_c68d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac68)));
                                break;
                            case 69:
                                tvQuestion.setText(R.string.grm_c68);
                                btn_grm_a.setText(R.string.grm_c68a);
                                btn_grm_b.setText(R.string.grm_c68b);
                                btn_grm_c.setText(R.string.grm_c68c);
                                btn_grm_d.setText(R.string.grm_c68d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac68)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau3);
                        break;
                    case 4:
                        i++;
                        random = RandomQuestion(68,71);
                        switch (random){
                            case 68:
                                tvQuestion.setText(R.string.grm_c69);
                                btn_grm_a.setText(R.string.grm_c69a);
                                btn_grm_b.setText(R.string.grm_c69b);
                                btn_grm_c.setText(R.string.grm_c69c);
                                btn_grm_d.setText(R.string.grm_c69d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac69)));
                                break;
                            case 69:
                                tvQuestion.setText(R.string.grm_c69);
                                btn_grm_a.setText(R.string.grm_c69a);
                                btn_grm_b.setText(R.string.grm_c69b);
                                btn_grm_c.setText(R.string.grm_c69c);
                                btn_grm_d.setText(R.string.grm_c69d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac69)));
                                break;
                            case 70:
                                tvQuestion.setText(R.string.grm_c70);
                                btn_grm_a.setText(R.string.grm_c70a);
                                btn_grm_b.setText(R.string.grm_c70b);
                                btn_grm_c.setText(R.string.grm_c70c);
                                btn_grm_d.setText(R.string.grm_c70d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac70)));
                                break;
                            case 71:
                                tvQuestion.setText(R.string.grm_c70);
                                btn_grm_a.setText(R.string.grm_c70a);
                                btn_grm_b.setText(R.string.grm_c70b);
                                btn_grm_c.setText(R.string.grm_c70c);
                                btn_grm_d.setText(R.string.grm_c70d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac70)));
                                break;
                        }
                        countDownTimer(timeCountDown15);
                        tvGrammarMoney.setText(R.string.cau4);
                        break;
                    case 5:
                        i++;
                        random = RandomQuestion(70,73);
                        switch (random){
                            case 70:
                                tvQuestion.setText(R.string.grm_c71);
                                btn_grm_a.setText(R.string.grm_c71a);
                                btn_grm_b.setText(R.string.grm_c71b);
                                btn_grm_c.setText(R.string.grm_c71c);
                                btn_grm_d.setText(R.string.grm_c71d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac71)));
                                break;
                            case 71:
                                tvQuestion.setText(R.string.grm_c71);
                                btn_grm_a.setText(R.string.grm_c71a);
                                btn_grm_b.setText(R.string.grm_c71b);
                                btn_grm_c.setText(R.string.grm_c71c);
                                btn_grm_d.setText(R.string.grm_c71d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac71)));
                                break;
                            case 72:
                                tvQuestion.setText(R.string.grm_c72);
                                btn_grm_a.setText(R.string.grm_c72a);
                                btn_grm_b.setText(R.string.grm_c72b);
                                btn_grm_c.setText(R.string.grm_c72c);
                                btn_grm_d.setText(R.string.grm_c72d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac72)));
                                break;
                            case 73:
                                tvQuestion.setText(R.string.grm_c72);
                                btn_grm_a.setText(R.string.grm_c72a);
                                btn_grm_b.setText(R.string.grm_c72b);
                                btn_grm_c.setText(R.string.grm_c72c);
                                btn_grm_d.setText(R.string.grm_c72d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac72)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau5);
                        break;
                    case 6:
                        i++;
                        random = RandomQuestion(72,75);
                        switch (random){
                            case 72:
                                tvQuestion.setText(R.string.grm_c73);
                                btn_grm_a.setText(R.string.grm_c73a);
                                btn_grm_b.setText(R.string.grm_c73b);
                                btn_grm_c.setText(R.string.grm_c73c);
                                btn_grm_d.setText(R.string.grm_c73d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac73)));
                                break;
                            case 73:
                                tvQuestion.setText(R.string.grm_c73);
                                btn_grm_a.setText(R.string.grm_c73a);
                                btn_grm_b.setText(R.string.grm_c73b);
                                btn_grm_c.setText(R.string.grm_c73c);
                                btn_grm_d.setText(R.string.grm_c73d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac73)));
                                break;
                            case 74:
                                tvQuestion.setText(R.string.grm_c74);
                                btn_grm_a.setText(R.string.grm_c74a);
                                btn_grm_b.setText(R.string.grm_c74b);
                                btn_grm_c.setText(R.string.grm_c74c);
                                btn_grm_d.setText(R.string.grm_c74d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac74)));
                                break;
                            case 75:
                                tvQuestion.setText(R.string.grm_c74);
                                btn_grm_a.setText(R.string.grm_c74a);
                                btn_grm_b.setText(R.string.grm_c74b);
                                btn_grm_c.setText(R.string.grm_c74c);
                                btn_grm_d.setText(R.string.grm_c74d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac74)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau6);
                        break;
                    case 7:
                        i++;
                        random = RandomQuestion(74,77);
                        switch (random){
                            case 74:
                                tvQuestion.setText(R.string.grm_c75);
                                btn_grm_a.setText(R.string.grm_c75a);
                                btn_grm_b.setText(R.string.grm_c75b);
                                btn_grm_c.setText(R.string.grm_c75c);
                                btn_grm_d.setText(R.string.grm_c75d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac75)));
                                break;
                            case 15:
                                tvQuestion.setText(R.string.grm_c75);
                                btn_grm_a.setText(R.string.grm_c75a);
                                btn_grm_b.setText(R.string.grm_c75b);
                                btn_grm_c.setText(R.string.grm_c75c);
                                btn_grm_d.setText(R.string.grm_c75d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac75)));
                                break;
                            case 16:
                                tvQuestion.setText(R.string.grm_c76);
                                btn_grm_a.setText(R.string.grm_c76a);
                                btn_grm_b.setText(R.string.grm_c76b);
                                btn_grm_c.setText(R.string.grm_c76c);
                                btn_grm_d.setText(R.string.grm_c76d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac76)));
                                break;
                            case 17:
                                tvQuestion.setText(R.string.grm_c76);
                                btn_grm_a.setText(R.string.grm_c76a);
                                btn_grm_b.setText(R.string.grm_c76b);
                                btn_grm_c.setText(R.string.grm_c76c);
                                btn_grm_d.setText(R.string.grm_c76d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac76)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau7);
                        break;
                    case 8:
                        i++;
                        random = RandomQuestion(76,79);
                        switch (random){
                            case 76:
                                tvQuestion.setText(R.string.grm_c77);
                                btn_grm_a.setText(R.string.grm_c77a);
                                btn_grm_b.setText(R.string.grm_c77b);
                                btn_grm_c.setText(R.string.grm_c77c);
                                btn_grm_d.setText(R.string.grm_c77d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac77)));
                                break;
                            case 77:
                                tvQuestion.setText(R.string.grm_c77);
                                btn_grm_a.setText(R.string.grm_c77a);
                                btn_grm_b.setText(R.string.grm_c77b);
                                btn_grm_c.setText(R.string.grm_c77c);
                                btn_grm_d.setText(R.string.grm_c77d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac77)));
                                break;
                            case 78:
                                tvQuestion.setText(R.string.grm_c78);
                                btn_grm_a.setText(R.string.grm_c78a);
                                btn_grm_b.setText(R.string.grm_c78b);
                                btn_grm_c.setText(R.string.grm_c78c);
                                btn_grm_d.setText(R.string.grm_c78d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac78)));
                                break;
                            case 79:
                                tvQuestion.setText(R.string.grm_c78);
                                btn_grm_a.setText(R.string.grm_c78a);
                                btn_grm_b.setText(R.string.grm_c78b);
                                btn_grm_c.setText(R.string.grm_c78c);
                                btn_grm_d.setText(R.string.grm_c78d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac78)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau8);
                        break;
                    case 9:
                        i++;
                        random = RandomQuestion(78,81);
                        switch (random){
                            case 78:
                                tvQuestion.setText(R.string.grm_c79);
                                btn_grm_a.setText(R.string.grm_c79a);
                                btn_grm_b.setText(R.string.grm_c79b);
                                btn_grm_c.setText(R.string.grm_c79c);
                                btn_grm_d.setText(R.string.grm_c79d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac79)));
                                break;
                            case 79:
                                tvQuestion.setText(R.string.grm_c79);
                                btn_grm_a.setText(R.string.grm_c79a);
                                btn_grm_b.setText(R.string.grm_c79b);
                                btn_grm_c.setText(R.string.grm_c79c);
                                btn_grm_d.setText(R.string.grm_c79d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac79)));
                                break;
                            case 80:
                                tvQuestion.setText(R.string.grm_c80);
                                btn_grm_a.setText(R.string.grm_c80a);
                                btn_grm_b.setText(R.string.grm_c80b);
                                btn_grm_c.setText(R.string.grm_c80c);
                                btn_grm_d.setText(R.string.grm_c80d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac80)));
                                break;
                            case 81:
                                tvQuestion.setText(R.string.grm_c80);
                                btn_grm_a.setText(R.string.grm_c80a);
                                btn_grm_b.setText(R.string.grm_c80b);
                                btn_grm_c.setText(R.string.grm_c80c);
                                btn_grm_d.setText(R.string.grm_c80d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac80)));
                                break;
                        }
                        countDownTimer(timeCountDown610);
                        tvGrammarMoney.setText(R.string.cau9);
                        break;
                    case 10:
                        i++;
                        random = RandomQuestion(80,83);
                        switch (random){
                            case 80:
                                tvQuestion.setText(R.string.grm_c81);
                                btn_grm_a.setText(R.string.grm_c81a);
                                btn_grm_b.setText(R.string.grm_c81b);
                                btn_grm_c.setText(R.string.grm_c81c);
                                btn_grm_d.setText(R.string.grm_c81d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac81)));
                                break;
                            case 81:
                                tvQuestion.setText(R.string.grm_c81);
                                btn_grm_a.setText(R.string.grm_c81a);
                                btn_grm_b.setText(R.string.grm_c81b);
                                btn_grm_c.setText(R.string.grm_c81c);
                                btn_grm_d.setText(R.string.grm_c81d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac81)));
                                break;
                            case 82:
                                tvQuestion.setText(R.string.grm_c82);
                                btn_grm_a.setText(R.string.grm_c82a);
                                btn_grm_b.setText(R.string.grm_c82b);
                                btn_grm_c.setText(R.string.grm_c82c);
                                btn_grm_d.setText(R.string.grm_c82d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac82)));
                                break;
                            case 83:
                                tvQuestion.setText(R.string.grm_c82);
                                btn_grm_a.setText(R.string.grm_c82a);
                                btn_grm_b.setText(R.string.grm_c82b);
                                btn_grm_c.setText(R.string.grm_c82c);
                                btn_grm_d.setText(R.string.grm_c82d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac82)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau10);
                        break;
                    case 11:
                        i++;
                        random = RandomQuestion(82,85);
                        switch (random){
                            case 52:
                                tvQuestion.setText(R.string.grm_c83);
                                btn_grm_a.setText(R.string.grm_c83a);
                                btn_grm_b.setText(R.string.grm_c83b);
                                btn_grm_c.setText(R.string.grm_c83c);
                                btn_grm_d.setText(R.string.grm_c83d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac83)));
                                break;
                            case 53:
                                tvQuestion.setText(R.string.grm_c83);
                                btn_grm_a.setText(R.string.grm_c83a);
                                btn_grm_b.setText(R.string.grm_c83b);
                                btn_grm_c.setText(R.string.grm_c83c);
                                btn_grm_d.setText(R.string.grm_c83d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac83)));
                                break;
                            case 54:
                                tvQuestion.setText(R.string.grm_c84);
                                btn_grm_a.setText(R.string.grm_c84a);
                                btn_grm_b.setText(R.string.grm_c84b);
                                btn_grm_c.setText(R.string.grm_c84c);
                                btn_grm_d.setText(R.string.grm_c84d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac84)));
                                break;
                            case 55:
                                tvQuestion.setText(R.string.grm_c84);
                                btn_grm_a.setText(R.string.grm_c84a);
                                btn_grm_b.setText(R.string.grm_c84b);
                                btn_grm_c.setText(R.string.grm_c84c);
                                btn_grm_d.setText(R.string.grm_c84d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac84)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau11);
                        break;
                    case 12:
                        i++;
                        random = RandomQuestion(84,87);
                        switch (random){
                            case 84:
                                tvQuestion.setText(R.string.grm_c85);
                                btn_grm_a.setText(R.string.grm_c85a);
                                btn_grm_b.setText(R.string.grm_c85b);
                                btn_grm_c.setText(R.string.grm_c85c);
                                btn_grm_d.setText(R.string.grm_c85d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac85)));
                                break;
                            case 85:
                                tvQuestion.setText(R.string.grm_c85);
                                btn_grm_a.setText(R.string.grm_c85a);
                                btn_grm_b.setText(R.string.grm_c85b);
                                btn_grm_c.setText(R.string.grm_c85c);
                                btn_grm_d.setText(R.string.grm_c85d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac85)));
                                break;
                            case 86:
                                tvQuestion.setText(R.string.grm_c86);
                                btn_grm_a.setText(R.string.grm_c86a);
                                btn_grm_b.setText(R.string.grm_c86b);
                                btn_grm_c.setText(R.string.grm_c86c);
                                btn_grm_d.setText(R.string.grm_c86d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac86)));
                                break;
                            case 87:
                                tvQuestion.setText(R.string.grm_c86);
                                btn_grm_a.setText(R.string.grm_c86a);
                                btn_grm_b.setText(R.string.grm_c86b);
                                btn_grm_c.setText(R.string.grm_c86c);
                                btn_grm_d.setText(R.string.grm_c86d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac86)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau12);
                        break;
                    case 13:
                        i++;
                        random = RandomQuestion(86,89);
                        switch (random){
                            case 86:
                                tvQuestion.setText(R.string.grm_c87);
                                btn_grm_a.setText(R.string.grm_c87a);
                                btn_grm_b.setText(R.string.grm_c87b);
                                btn_grm_c.setText(R.string.grm_c87c);
                                btn_grm_d.setText(R.string.grm_c87d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac87)));
                                break;
                            case 87:
                                tvQuestion.setText(R.string.grm_c87);
                                btn_grm_a.setText(R.string.grm_c87a);
                                btn_grm_b.setText(R.string.grm_c87b);
                                btn_grm_c.setText(R.string.grm_c87c);
                                btn_grm_d.setText(R.string.grm_c87d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac87)));
                                break;
                            case 88:
                                tvQuestion.setText(R.string.grm_c88);
                                btn_grm_a.setText(R.string.grm_c88a);
                                btn_grm_b.setText(R.string.grm_c88b);
                                btn_grm_c.setText(R.string.grm_c88c);
                                btn_grm_d.setText(R.string.grm_c88d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac88)));
                                break;
                            case 89:
                                tvQuestion.setText(R.string.grm_c88);
                                btn_grm_a.setText(R.string.grm_c88a);
                                btn_grm_b.setText(R.string.grm_c88b);
                                btn_grm_c.setText(R.string.grm_c88c);
                                btn_grm_d.setText(R.string.grm_c88d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac88)));
                                break;
                        }
                        countDownTimer(timeCountDown1115);
                        tvGrammarMoney.setText(R.string.cau13);
                        break;
                    case 14:
                        i++;
                        random = RandomQuestion(87,90);
                        switch (random){
                            case 87:
                                tvQuestion.setText(R.string.grm_c89);
                                btn_grm_a.setText(R.string.grm_c89a);
                                btn_grm_b.setText(R.string.grm_c89b);
                                btn_grm_c.setText(R.string.grm_c89c);
                                btn_grm_d.setText(R.string.grm_c89d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac89)));
                                break;
                            case 88:
                                tvQuestion.setText(R.string.grm_c89);
                                btn_grm_a.setText(R.string.grm_c89a);
                                btn_grm_b.setText(R.string.grm_c89b);
                                btn_grm_c.setText(R.string.grm_c89c);
                                btn_grm_d.setText(R.string.grm_c89d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac89)));
                                break;
                            case 89:
                                tvQuestion.setText(R.string.grm_c90);
                                btn_grm_a.setText(R.string.grm_c90a);
                                btn_grm_b.setText(R.string.grm_c90b);
                                btn_grm_c.setText(R.string.grm_c90c);
                                btn_grm_d.setText(R.string.grm_c90d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac90)));
                                break;
                            case 90:
                                tvQuestion.setText(R.string.grm_c90);
                                btn_grm_a.setText(R.string.grm_c90a);
                                btn_grm_b.setText(R.string.grm_c90b);
                                btn_grm_c.setText(R.string.grm_c90c);
                                btn_grm_d.setText(R.string.grm_c90d);
                                kq_grm = Integer.parseInt(String.valueOf(getResources().getString(R.string.dac90)));
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
            builder = new AlertDialog.Builder(GerundAndToVerb.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(GerundAndToVerb.this);
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




    public void countDownTimer(int time/*, final int sound*/){
        count = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                tvGrmCountDown.setText(String.valueOf(millisUntilFinished/1000));
                //here you can have your logic to set text to edittext
                /*mPlayer = MediaPlayer.create(getApplicationContext(), sound);
                mPlayer.start();*/
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
