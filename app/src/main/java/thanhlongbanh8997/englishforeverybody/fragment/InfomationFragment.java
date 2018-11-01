package thanhlongbanh8997.englishforeverybody.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import thanhlongbanh8997.englishforeverybody.R;

import static android.app.Activity.RESULT_OK;

public class InfomationFragment extends Fragment {
    View v;
    ImageView imgUser;
    Button chooseImageFromGallery;
    Button usingCamera;

    TextView userGrammarGerundAndToVerb;
    TextView userGrammarPassiveVoice;
    TextView userRelativeClause;
    TextView userGrammarTense;
    TextView userLisScore;

    public InfomationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_info,container,false);


        initView();
        initScoreDefault();



        imgUser = v.findViewById(R.id.imgUser);
        chooseImageFromGallery = v.findViewById(R.id.chooseImageFromGallery);
        usingCamera = v.findViewById(R.id.usingCamera);



        ImageGallery();
        UsingCamera();





        return v;
    }

    public void initView(){
        userGrammarGerundAndToVerb = v.findViewById(R.id.userGrammarGerundAndToVerb);
        userGrammarPassiveVoice = v.findViewById(R.id.userGrammarPassiveVoice);
        userRelativeClause = v.findViewById(R.id.userRelativeClause);
        userGrammarTense = v.findViewById(R.id.userGrammarTense);
        userLisScore = v.findViewById(R.id.userLisScore);
    }

    public void initScoreDefault(){
        SharedPreferences prefs = getContext().getSharedPreferences("SPFolder", Context.MODE_PRIVATE);
        int ScoreGrm_GATV = prefs.getInt("GRAM_gatv", 0);
        int ScoreGrm_PQ = prefs.getInt("GRM_pq", 0);
        int ScoreGrm_RC = prefs.getInt("GRM_rc", 0);
        int ScoreGrm_TQ = prefs.getInt("GRM_tq", 0);
        int ScoreLIS = prefs.getInt("LIS", 0);



        userGrammarGerundAndToVerb.setText("Gerund and to verb: " + String.valueOf(ScoreGrm_GATV));
        userGrammarPassiveVoice.setText("Passive voice: " + String.valueOf(ScoreGrm_PQ));
        userRelativeClause.setText("Relative clause: " + String.valueOf(ScoreGrm_RC));
        userGrammarTense.setText("Tense: " + String.valueOf(ScoreGrm_TQ));
        userLisScore.setText(String.valueOf(ScoreLIS));

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgUser.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgUser.setImageURI(selectedImage);
                }
                break;
        }

    }

    public void ImageGallery(){
        chooseImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
            }
        });
    }

    public void UsingCamera(){
        usingCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code
            }
        });

    }
}
