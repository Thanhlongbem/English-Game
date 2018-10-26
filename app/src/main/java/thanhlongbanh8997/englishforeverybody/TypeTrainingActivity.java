package thanhlongbanh8997.englishforeverybody;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import thanhlongbanh8997.englishforeverybody.adapter.ViewPageAdapter;
import thanhlongbanh8997.englishforeverybody.fragment.GrammarFragment;
import thanhlongbanh8997.englishforeverybody.fragment.InfomationFragment;
import thanhlongbanh8997.englishforeverybody.fragment.ListeningFragment;

public class TypeTrainingActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_training_activity);

        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //add fragment here
        adapter.AddFragment(new GrammarFragment(),"Grammar");
        adapter.AddFragment(new ListeningFragment(),"Listening");
        adapter.AddFragment(new InfomationFragment(), "User");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_grammar);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_listening);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info);


    }
}
