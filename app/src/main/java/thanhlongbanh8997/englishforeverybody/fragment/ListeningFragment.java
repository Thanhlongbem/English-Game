package thanhlongbanh8997.englishforeverybody.fragment;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import thanhlongbanh8997.englishforeverybody.ActivityChampion;
import thanhlongbanh8997.englishforeverybody.MainActivity;
import thanhlongbanh8997.englishforeverybody.R;
import thanhlongbanh8997.englishforeverybody.listening.ContentListening;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

public class ListeningFragment extends Fragment {
    View v;
    ValueAnimator animator;
    Button btnListeningStart;
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
    MediaPlayer mPlayer;
    TextView tvLisCountDown;
    TextToSpeech t1;
    Button btn_next_lis;
    Button btn_ok_lis;
    Button btn_save_lis;
    Button btn_dont_save_lis;
    TextView numQues;

    int soLanNghe = 2;
    int dapAn = 0;
    int kq_lis = 0;
    int i = 1;

    public int timeCountDown15 = 10000;
    public int timeCountDown610 = 30000;
    public int timeCountDown1115 = 60000;

    public ListeningFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_listening,container,false);
        btnListeningStart = v.findViewById(R.id.btnListeningStart);

        btnListeningStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContentListening.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
