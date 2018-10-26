package thanhlongbanh8997.englishforeverybody;

import android.app.Dialog;
import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);

    }


}
