package thanhlongbanh8997.englishforeverybody.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thanhlongbanh8997.englishforeverybody.R;
import thanhlongbanh8997.englishforeverybody.GrammarStartActivity;

public class GrammarFragment extends Fragment{
    View v;

    Button btnTenseGRM;
    Button btnPassiveVoiceGRM;
    Button btnGerundToVerbGRM;
    Button btnRelativeClauseGRM;

    public int type_Grammar=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_grammar,container,false);
        btnTenseGRM = v.findViewById(R.id.btnTenseGRM);
        btnPassiveVoiceGRM = v.findViewById(R.id.btnPassiveVoiceGRM);
        btnGerundToVerbGRM = v.findViewById(R.id.btnGerundToVerbGRM);
        btnRelativeClauseGRM = v.findViewById(R.id.btnRelativeClauseGRM);

        btnTenseGRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_Grammar = 1;
                Intent intent = new Intent(getContext(), GrammarStartActivity.class);
                intent.putExtra("TypeGrammar","1");
                intent.putExtra("TitleOfType", "Put the verbs in the correct form");
                startActivity(intent);
            }
        });


        btnPassiveVoiceGRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_Grammar = 2;
                Intent intent = new Intent(getContext(), GrammarStartActivity.class);
                intent.putExtra("TypeGrammar","2");
                intent.putExtra("TitleOfType", "Put the sentences into passive voice");
                startActivity(intent);
            }
        });

        btnGerundToVerbGRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_Grammar = 3;
                Intent intent = new Intent(getContext(), GrammarStartActivity.class);
                intent.putExtra("TypeGrammar","3");
                intent.putExtra("TitleOfType", "Put the verbs into correct form");
                startActivity(intent);
            }
        });

        btnRelativeClauseGRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_Grammar = 4;
                Intent intent = new Intent(getContext(), GrammarStartActivity.class);
                intent.putExtra("TypeGrammar","4");
                intent.putExtra("TitleOfType", "Choose the best choice");
                startActivity(intent);
            }
        });





        return v;
    }







}

