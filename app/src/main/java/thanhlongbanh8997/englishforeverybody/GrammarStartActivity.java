package thanhlongbanh8997.englishforeverybody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import thanhlongbanh8997.englishforeverybody.grammar.GerundAndToVerb;
import thanhlongbanh8997.englishforeverybody.grammar.PassivoiceQuestion;
import thanhlongbanh8997.englishforeverybody.grammar.RelativeClause;
import thanhlongbanh8997.englishforeverybody.grammar.TenseQuestion;

public class GrammarStartActivity extends AppCompatActivity {
    TextView tvInsistence;
    Button btn_start_test;
    public String type;
    public int num_type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tvInsistence = findViewById(R.id.tvInsistence);
        btn_start_test = findViewById(R.id.btn_start_test);

        final Intent intent = getIntent();
        type = intent.getStringExtra("TypeGrammar");

        num_type = Integer.parseInt(type);
        Log.e("TypeGrammar_Long", String.valueOf(num_type));
        String deBai = intent.getStringExtra("TitleOfType");

        tvInsistence.setText(deBai);

        btn_start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num_type - 1 == 0 ){
                    Intent intent1 = new Intent(getApplicationContext(), TenseQuestion.class);
                    startActivity(intent1);
                }else if(num_type - 2 == 0){
                    Intent intent1 = new Intent(getApplicationContext(), PassivoiceQuestion.class);
                    startActivity(intent1);
                }else if(num_type - 3 == 0){
                    Intent intent1 = new Intent(getApplicationContext(), GerundAndToVerb.class);
                    startActivity(intent1);
                }else if(num_type - 4 == 0){
                    Intent intent1 = new Intent(getApplicationContext(), RelativeClause.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
