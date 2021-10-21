package das.anusha.widgetcollage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.Iterator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements MainClickListener{
    private String[] SKILLSETS;
    private int SETNUMBER;
    private int selectIndex = 0;
    private EditSkillsFrag adderFrag;
    private LinkedList<String>[] SetSkillTags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SKILLSETS = getResources().getStringArray(R.array.skillsets);
        SETNUMBER = SKILLSETS.length;
        SetSkillTags = new LinkedList[SETNUMBER];
        for(int i=0; i<SETNUMBER; i++){
            String fragTag = i+SKILLSETS[i];
            FragmentTransaction myFt = getSupportFragmentManager().beginTransaction()
                    .add(R.id.sSetScroll, SkillSetFrag.newInstance(SKILLSETS[i], fragTag), fragTag);
            myFt.commit();
            SetSkillTags[i] = new LinkedList<String>();
        }
        resetSkillList(0);
        adderFrag = (EditSkillsFrag) getSupportFragmentManager().findFragmentById(R.id.skillAdder);
    }

    private void resetSkillList(int newTagListInd) {
        //TODO remove old frags, add new frags
        Iterator<String> oldFrags= SetSkillTags[selectIndex].iterator();
        while (oldFrags.hasNext()){
            String tagForRemoval = oldFrags.next();
            //getSupportFragmentManager().beginTransaction().add(R.id.sSetScroll, ----).commit();
        }
        selectIndex = newTagListInd;
    }

    @Override
    public void onSetSelect(String fragTitle) {
        resetSkillList(Integer.parseInt(fragTitle.substring(0,1)));
    }

    @Override
    public void onAddSkill() {
        //TODO bring frag into focus
    }
}

//Submit each layout screenshot
//P3L07aDasAnusha.apk
//embelishments apk and video
//guides.codepath.com/android/creating-and-using-fragments
//TODO transition later
//TODO colors, logo? skillset select formatting
//TODO transitions when frag leaves returns, plus button animation, swipe delete
//TODO shared prefs JSON for saving lists of skill fragments