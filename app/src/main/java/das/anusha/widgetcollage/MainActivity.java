package das.anusha.widgetcollage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    String[] SKILLSETS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SKILLSETS = getResources().
                getStringArray(R.array.skillsets);
        String tagPrefix = "ss";
        for(String i: SKILLSETS){
            FragmentTransaction myFt = getSupportFragmentManager().beginTransaction()
                    .add(R.id.sSetScroll, SkillSetFrag.newInstance(i), tagPrefix+i);
        }
    }

}

//Submit each layout screenshot
//P3L07aDasAnusha.apk
//embelishments apk and video
//TODO transition later
//TODO colors, logo, skillsets descriptins?
//TODO transitions when frag leaves returns, plus button animation