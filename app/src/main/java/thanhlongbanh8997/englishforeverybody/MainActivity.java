package thanhlongbanh8997.englishforeverybody;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

import io.realm.Realm;
import io.realm.RealmResults;
import thanhlongbanh8997.englishforeverybody.model.User;

public class MainActivity extends AppCompatActivity {
    Button btn_home_start;
    Button btn_home_vocabulary;
    EditText startUserName;
    Button startOK;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initDataUser();
        btn_home_start = findViewById(R.id.btn_home_start);
        btn_home_vocabulary = findViewById(R.id.btn_home_vocabulary);

        btn_home_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TypeTrainingActivity.class);
                startActivity(intent);
            }
        });
        //realm = Realm.getDefaultInstance();

        //RealmResults<User> banh = realm.where(User.class,).equalTo("userName", "")

        //writeToDB(null, "Preposition: 0", "Article: 0", "Relative clause: 0", "Passive voice: 0");
        btn_home_vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void initDataUser(){
        sharedPreferences = getSharedPreferences("SPFolder", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("GRAM_gatv", 0);
        editor.putInt("GRM_pq", 0);
        editor.putInt("GRM_rc", 0);
        editor.putInt("GRM_tq", 0);
        editor.putInt("LIS", 0);
        editor.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);

    }


}
