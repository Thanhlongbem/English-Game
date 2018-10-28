package thanhlongbanh8997.englishforeverybody.listening;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import thanhlongbanh8997.englishforeverybody.ActivityChampion;
import thanhlongbanh8997.englishforeverybody.MainActivity;
import thanhlongbanh8997.englishforeverybody.R;


public class ContentListening extends AppCompatActivity {
    Button btnListening;
    LinearLayout ln_listenning_start;
    LinearLayout rl_listenning_content;
    TextView listening_yA;
    TextView listening_yB;
    TextView listening_yC;
    TextView listening_yD;
    MediaPlayer mp;
    TextView tvQuestionLis;
    CountDownTimer count;
    TextView tvLisCountDown;
    //TextToSpeech t1;
    Button btn_next_lis;
    Button btn_ok_lis;
    Button btn_save_lis;
    Button btn_dont_save_lis;
    TextView numQues;
    TextView tvSoLanNghe;
    Button btn_continue_lis;

    int soLanNghe = 2;
    int dapAn = 0;
    int kq_lis = 0;
    int i = 1;
    String timeLive = "45000";


    public int timeCountDown15 = 30000;
    public int timeCountDown610 = 30000;
    public int timeCountDown1115 = 30000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_content);
        initView();
        initClickAnswer();
        initClick();
        evenLisClick();

        initData();
        btn_continue_lis.setVisibility(View.VISIBLE);
        btn_ok_lis.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
        finish();
    }

    public void initView(){
        btnListening = findViewById(R.id.btnListening);
        ln_listenning_start = findViewById(R.id.ln_listenning_start);
        rl_listenning_content = findViewById(R.id.rl_listenning_content);
        tvQuestionLis = findViewById(R.id.tvQuestionLis);
        listening_yA = findViewById(R.id.listening_yA);
        listening_yB = findViewById(R.id.listening_yB);
        listening_yC = findViewById(R.id.listening_yC);
        listening_yD = findViewById(R.id.listening_yD);
        tvLisCountDown = findViewById(R.id.tvLisCountDown);
        btn_ok_lis = findViewById(R.id.btn_ok_lis);
        btn_next_lis = findViewById(R.id.btn_next_lis);
        btn_dont_save_lis = findViewById(R.id.btn_dont_save_lis);
        btn_save_lis = findViewById(R.id.btn_save_lis);
        numQues = findViewById(R.id.numQues);
        tvSoLanNghe = findViewById(R.id.tvSoLanNghe);
        btn_continue_lis = findViewById(R.id.btn_continue_lis);


    }

    public void initData(){
        //Bai1

        //Cau1
        soLanNghe = 2;
        tvQuestionLis.setText(R.string.lis_ques_1_1);
        listening_yA.setText(R.string.ques_1a1);
        listening_yB.setText(R.string.ques_1b1);
        listening_yC.setText(R.string.ques_1c1);
        listening_yD.setText(R.string.ques_1d1);
        kq_lis = 2;
        //countDownTimer(timeCountDown15);
        tvLisCountDown.setText("45");

        numQues.setText(R.string.lis_c1);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                countDownTimer(timeCountDown15);
            }
        });




    }

    public void initClickAnswer(){
        listening_yA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dapAn = 1;
                listening_yA.setBackgroundResource(R.drawable.btn_true);
                listening_yB.setBackgroundResource(R.drawable.btn_not);
                listening_yC.setBackgroundResource(R.drawable.btn_not);
                listening_yD.setBackgroundResource(R.drawable.btn_not);
            }
        });

        listening_yB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dapAn = 2;
                listening_yB.setBackgroundResource(R.drawable.btn_true);
                listening_yA.setBackgroundResource(R.drawable.btn_not);
                listening_yC.setBackgroundResource(R.drawable.btn_not);
                listening_yD.setBackgroundResource(R.drawable.btn_not);
            }
        });

        listening_yC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dapAn = 3;
                listening_yC.setBackgroundResource(R.drawable.btn_true);
                listening_yB.setBackgroundResource(R.drawable.btn_not);
                listening_yA.setBackgroundResource(R.drawable.btn_not);
                listening_yD.setBackgroundResource(R.drawable.btn_not);
            }
        });

        listening_yD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dapAn = 4;
                listening_yD.setBackgroundResource(R.drawable.btn_true);
                listening_yB.setBackgroundResource(R.drawable.btn_not);
                listening_yC.setBackgroundResource(R.drawable.btn_not);
                listening_yA.setBackgroundResource(R.drawable.btn_not);
            }
        });
    }

    public void initClick(){
        btn_save_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_dont_save_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_ok_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOK();
            }
        });

        btn_next_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listening_yA.setBackgroundResource(R.drawable.btn_not);
                listening_yB.setBackgroundResource(R.drawable.btn_not);
                listening_yC.setBackgroundResource(R.drawable.btn_not);
                listening_yD.setBackgroundResource(R.drawable.btn_not);

                switch (i){
                    case 1:
                        i++;
                        numQues.setText(R.string.lis_c2);
                        tvQuestionLis.setText(R.string.lis_ques_1_2);
                        listening_yA.setText(R.string.ques_1a2);
                        listening_yB.setText(R.string.ques_1b2);
                        listening_yC.setText(R.string.ques_1c2);
                        listening_yD.setText(R.string.ques_1d2);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac1_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                        onNextCommon();
                        break;
                    case 2:
                        i++;
                        numQues.setText(R.string.lis_c3);
                        tvQuestionLis.setText(R.string.lis_ques_1_3);
                        listening_yA.setText(R.string.ques_1a3);
                        listening_yB.setText(R.string.ques_1b3);
                        listening_yC.setText(R.string.ques_1c3);
                        listening_yD.setText(R.string.ques_1d3);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac1_3)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                        onNextCommon();
                        break;
                    case 3:
                        i++;
                        numQues.setText(R.string.lis_c4);
                        tvQuestionLis.setText(R.string.lis_ques_1_4);
                        listening_yA.setText(R.string.ques_1a4);
                        listening_yB.setText(R.string.ques_1b4);
                        listening_yC.setText(R.string.ques_1c4);
                        listening_yD.setText(R.string.ques_1d4);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac1_4)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                        onNextCommon();
                        break;
                    case 4:
                        i++;
                        numQues.setText(R.string.lis_c5);
                        tvQuestionLis.setText(R.string.lis_ques_1_5);
                        listening_yA.setText(R.string.ques_1a5);
                        listening_yB.setText(R.string.ques_1b5);
                        listening_yC.setText(R.string.ques_1c5);
                        listening_yD.setText(R.string.ques_1d5);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac1_5)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                        onNextCommon();
                        break;
                    case 5:
                        i++;
                        numQues.setText(R.string.lis_c6);
                        tvQuestionLis.setText(R.string.lis_ques_2_1);
                        listening_yA.setText(R.string.ques_2a1);
                        listening_yB.setText(R.string.ques_2b1);
                        listening_yC.setVisibility(View.GONE);
                        listening_yD.setVisibility(View.GONE);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac2_1)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        onNextCommon();
                        break;
                    case 6:
                        i++;
                        numQues.setText(R.string.lis_c7);
                        tvQuestionLis.setText(R.string.lis_ques_2_2);
                        listening_yA.setText(R.string.ques_2a2);
                        listening_yB.setText(R.string.ques_2b2);
                        listening_yC.setVisibility(View.GONE);
                        listening_yD.setVisibility(View.GONE);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac2_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        onNextCommon();
                        break;
                    case 7:
                        i++;
                        tvQuestionLis.setText(R.string.lis_ques_2_3);
                        listening_yA.setText(R.string.ques_2a3);
                        listening_yB.setText(R.string.ques_2b3);
                        listening_yC.setVisibility(View.GONE);
                        listening_yD.setVisibility(View.GONE);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac2_3)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        onNextCommon();
                        break;
                    case 8:
                        i++;
                        numQues.setText(R.string.lis_c9);
                        tvQuestionLis.setText(R.string.lis_ques_2_4);
                        listening_yA.setText(R.string.ques_2a4);
                        listening_yB.setText(R.string.ques_2b4);
                        listening_yC.setVisibility(View.GONE);
                        listening_yD.setVisibility(View.GONE);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac2_4)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        onNextCommon();
                        break;
                    case 9:
                        i++;
                        tvQuestionLis.setText(R.string.lis_ques_2_5);
                        listening_yA.setText(R.string.ques_2a5);
                        listening_yB.setText(R.string.ques_2b5);
                        listening_yC.setVisibility(View.GONE);
                        listening_yD.setVisibility(View.GONE);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac2_5)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        onNextCommon();
                        break;
                    case 10:
                        i++;
                        listening_yC.setVisibility(View.VISIBLE);
                        listening_yD.setVisibility(View.VISIBLE);
                        numQues.setText(R.string.lis_c11);
                        tvQuestionLis.setText(R.string.lis_ques_3_1);
                        listening_yA.setText(R.string.ques_3a1);
                        listening_yB.setText(R.string.ques_3b1);
                        listening_yC.setText(R.string.ques_3c1);
                        listening_yD.setText(R.string.ques_3d1);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac3_1)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        onNextCommon();
                        break;
                    case 11:
                        i++;
                        numQues.setText(R.string.lis_c12);
                        tvQuestionLis.setText(R.string.lis_ques_3_2);
                        listening_yA.setText(R.string.ques_3a2);
                        listening_yB.setText(R.string.ques_3b2);
                        listening_yC.setText(R.string.ques_3c2);
                        listening_yD.setText(R.string.ques_3d2);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac3_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        onNextCommon();
                        break;
                    case 12:
                        i++;
                        numQues.setText(R.string.lis_c13);
                        tvQuestionLis.setText(R.string.lis_ques_3_3);
                        listening_yA.setText(R.string.ques_3a3);
                        listening_yB.setText(R.string.ques_3b3);
                        listening_yC.setText(R.string.ques_3c3);
                        listening_yD.setText(R.string.ques_3d3);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac3_3)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        onNextCommon();
                        break;
                    case 13:
                        i++;
                        numQues.setText(R.string.lis_c14);
                        tvQuestionLis.setText(R.string.lis_ques_3_4);
                        listening_yA.setText(R.string.ques_3a4);
                        listening_yB.setText(R.string.ques_3b4);
                        listening_yC.setText(R.string.ques_3c4);
                        listening_yD.setText(R.string.ques_3d4);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac3_4)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        onNextCommon();
                        break;
                    case 14:
                        i++;
                        numQues.setText(R.string.lis_c15);
                        tvQuestionLis.setText(R.string.lis_ques_3_5);
                        listening_yA.setText(R.string.ques_3a5);
                        listening_yB.setText(R.string.ques_3b5);
                        listening_yC.setText(R.string.ques_3c5);
                        listening_yD.setText(R.string.ques_3d5);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac3_5)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        onNextCommon();
                        break;
                    case 15:
                        i++;
                        numQues.setText(R.string.lis_c16);
                        tvQuestionLis.setText(R.string.lis_ques_4_1);
                        listening_yA.setText(R.string.ques_4a1);
                        listening_yB.setText(R.string.ques_4b1);
                        listening_yC.setText(R.string.ques_4c1);
                        listening_yD.setText(R.string.ques_4d1);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac4_1)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                        onNextCommon();
                        break;
                    case 16:
                        i++;
                        numQues.setText(R.string.lis_c17);
                        tvQuestionLis.setText(R.string.lis_ques_4_2);
                        listening_yA.setText(R.string.ques_4a2);
                        listening_yB.setText(R.string.ques_4b2);
                        listening_yC.setText(R.string.ques_4c2);
                        listening_yD.setText(R.string.ques_4d2);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac4_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                        onNextCommon();
                        break;
                    case 17:
                        i++;
                        numQues.setText(R.string.lis_c18);
                        tvQuestionLis.setText(R.string.lis_ques_4_3);
                        listening_yA.setText(R.string.ques_4a3);
                        listening_yB.setText(R.string.ques_4b3);
                        listening_yC.setText(R.string.ques_4c3);
                        listening_yD.setText(R.string.ques_4d3);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac4_3)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                        onNextCommon();
                        break;
                    case 18:
                        i++;
                        numQues.setText(R.string.lis_c19);
                        tvQuestionLis.setText(R.string.lis_ques_5_1);
                        listening_yA.setText(R.string.ques_5a1);
                        listening_yB.setText(R.string.ques_5b1);
                        listening_yC.setText(R.string.ques_5c1);
                        listening_yD.setText(R.string.ques_5d1);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac5_1)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.phongs_father);
                        onNextCommon();
                        break;
                    case 19:
                        i++;
                        numQues.setText(R.string.lis_c20);
                        tvQuestionLis.setText(R.string.lis_ques_5_2);
                        listening_yA.setText(R.string.ques_5a2);
                        listening_yB.setText(R.string.ques_5b2);
                        listening_yC.setText(R.string.ques_5c2);
                        listening_yD.setText(R.string.ques_5d2);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac5_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.phongs_father);
                        onNextCommon();
                        break;
                    case 20:
                        i++;
                        numQues.setText(R.string.lis_c21);
                        tvQuestionLis.setText(R.string.lis_ques_6_1);
                        listening_yA.setText(R.string.ques_6a1);
                        listening_yB.setText(R.string.ques_6b1);
                        listening_yC.setText(R.string.ques_6c1);
                        listening_yD.setText(R.string.ques_6d1);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac6_1)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        onNextCommon();
                        break;
                    case 21:
                        i++;
                        numQues.setText(R.string.lis_c22);
                        tvQuestionLis.setText(R.string.lis_ques_6_2);
                        listening_yA.setText(R.string.ques_6a2);
                        listening_yB.setText(R.string.ques_6b2);
                        listening_yC.setText(R.string.ques_6c2);
                        listening_yD.setText(R.string.ques_6d2);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac6_2)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        onNextCommon();
                        break;
                    case 22:
                        i++;
                        numQues.setText(R.string.lis_c23);
                        tvQuestionLis.setText(R.string.lis_ques_6_3);
                        listening_yA.setText(R.string.ques_6a3);
                        listening_yB.setText(R.string.ques_6b3);
                        listening_yC.setText(R.string.ques_6c3);
                        listening_yD.setText(R.string.ques_6d3);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac6_3)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        onNextCommon();
                        break;
                    case 23:
                        i++;
                        numQues.setText(R.string.lis_c24);
                        tvQuestionLis.setText(R.string.lis_ques_6_4);
                        listening_yA.setText(R.string.ques_6a4);
                        listening_yB.setText(R.string.ques_6b4);
                        listening_yC.setText(R.string.ques_6c4);
                        listening_yD.setText(R.string.ques_6d4);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac6_4)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        onNextCommon();
                        break;
                    case 24:
                        i++;
                        numQues.setText(R.string.lis_c25);
                        tvQuestionLis.setText(R.string.lis_ques_6_5);
                        listening_yA.setText(R.string.ques_6a5);
                        listening_yB.setText(R.string.ques_6b5);
                        listening_yC.setText(R.string.ques_6c5);
                        listening_yD.setText(R.string.ques_6d5);
                        kq_lis = Integer.parseInt(String.valueOf(getResources().getString(R.string.lis_dac6_5)));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        onNextCommon();
                        break;



                }
            }
        });
    }

    public void onNextCommon(){
        count.cancel();
        tvLisCountDown.setText("45");
        btn_continue_lis.setVisibility(View.VISIBLE);
        btn_ok_lis.setVisibility(View.GONE);
        btnListening.setVisibility(View.VISIBLE);
        soLanNghe = 2;
        tvSoLanNghe.setText("2");
        tvLisCountDown.setVisibility(View.VISIBLE);
        btn_next_lis.setVisibility(View.GONE);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                countDownTimer(45000);

                btn_ok_lis.setVisibility(View.VISIBLE);
            }
        });
    }


    public void evenLisClick(){
        btn_continue_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer(Integer.parseInt(timeLive));
                btn_continue_lis.setVisibility(View.GONE);
                btn_ok_lis.setVisibility(View.VISIBLE);
                mp.stop();
            }
        });

        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
        btnListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i == 1 || i==2 || i==3 || i==4 || i==5){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else{
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if(mp.isPlaying()){
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                            mp.start();
                        }else{
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.unit1);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }


                }else if(i == 6 || i==7 || i==8 || i==9 || i==10){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if(mp.isPlaying()){
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                            mp.start();
                        }else{
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.mr_lam_mp3cut_net);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }


                }else if(i == 11 || i==12 || i==13 || i==14 || i==15){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else{
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if(mp.isPlaying()){
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                            mp.start();
                        }else{
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.nguyen_hong_phong);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }

                }else if(i == 16 || i==17 || i==18){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else{
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if(mp.isPlaying()){
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                            mp.start();
                        }else{
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.teacher);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }


                }else if(i == 19 || i==20){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else{
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.phongs_father);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if (mp.isPlaying()) {
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.phongs_father);
                            mp.start();
                        } else {
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.phongs_father);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }

                }else if(i == 21 || i==22 || i==23 || i==24 || i==25){
                    if(mp.isPlaying()){
                        mp.stop();
                    }else{
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                        btn_continue_lis.setVisibility(View.VISIBLE);
                        btn_ok_lis.setVisibility(View.GONE);
                        soLanNghe--;
                        tvSoLanNghe.setText(String.valueOf(soLanNghe));
                        checkSoLanNghe();
                        timeLive = tvLisCountDown.getText().toString()+"000";
                        count.cancel();
                        if (mp.isPlaying()) {
                            mp.stop();
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                            mp.start();
                        } else {
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.marier_curier);
                            mp.start();
                        }
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                countDownTimer(Integer.parseInt(timeLive));
                            }
                        });
                    }

                }

            }
        });


    }





    public void countDownTimer(int time){
        count = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                tvLisCountDown.setText(String.valueOf(millisUntilFinished/1000));
                //here you can have your logic to set text to edittext
            }



            public void onFinish() {
                tvLisCountDown.setVisibility(View.GONE);
                btn_continue_lis.setVisibility(View.GONE);
                if(kq_lis == dapAn){
                    fillColor(kq_lis);
                    btn_next_lis.setVisibility(View.VISIBLE);
                    //String toSpeak = "well done";
                    //t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    fillColor(kq_lis);
                    btn_save_lis.setVisibility(View.VISIBLE);
                    btn_dont_save_lis.setVisibility(View.VISIBLE);
                }
                btn_ok_lis.setVisibility(View.GONE);
                tvLisCountDown.setVisibility(View.GONE);
            }

        }.start();

    }

    public void fillColor(int fico){
        if(fico == 1){
            listening_yA.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 2){
            listening_yB.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 3){
            listening_yC.setBackgroundResource(R.drawable.btn_true_answer);
        }else if(fico == 4){
            listening_yD.setBackgroundResource(R.drawable.btn_true_answer);
        }
    }



    public void showDialogOK(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("OK");
        builder.setMessage("Is this your last question?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                btn_ok_lis.setVisibility(View.GONE);
                tvLisCountDown.setVisibility(View.GONE);
                if(i!=25){
                    if(dapAn == kq_lis){
                        dialogInterface.dismiss();
                        count.cancel();
                        fillColor(kq_lis);
                        btn_next_lis.setVisibility(View.VISIBLE);
                        mp.stop();
                        //String toSpeak = "well done";
                        //t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    }else{
                        count.cancel();
                        fillColor(kq_lis);
                        btn_save_lis.setVisibility(View.VISIBLE);
                        btn_dont_save_lis.setVisibility(View.VISIBLE);
                    }
                }else{
                    if(dapAn == kq_lis){
                        dialogInterface.dismiss();
                        count.cancel();
                        mp.stop();
                        rl_listenning_content.setVisibility(View.GONE);
                        //String toSpeak = "Congratulation";
                        //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        //t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        Intent intent = new Intent(getApplicationContext(), ActivityChampion.class);
                        startActivity(intent);
                    }else{
                        count.cancel();
                        mp.stop();
                        fillColor(kq_lis);
                        btn_save_lis.setVisibility(View.VISIBLE);
                        btn_dont_save_lis.setVisibility(View.VISIBLE);
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

    public void checkSoLanNghe(){
        if(Integer.parseInt(tvSoLanNghe.getText().toString()) <= 0){
            btnListening.setVisibility(View.GONE);
            tvSoLanNghe.setText("0");
        }
    }
}

