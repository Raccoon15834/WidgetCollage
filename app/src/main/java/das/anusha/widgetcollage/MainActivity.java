package das.anusha.widgetcollage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.io.Console;
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
            FragmentTransaction myFt = getSupportFragmentManager().beginTransaction();
            myFt.add(R.id.sSetScroll, SkillSetFrag.newInstance(SKILLSETS[i], fragTag), fragTag);
            myFt.commit();//may do this after all at once
            SetSkillTags[i] = new LinkedList<String>();
        }
        resetSkillList(0);
        adderFrag = (EditSkillsFrag) getSupportFragmentManager().findFragmentById(R.id.skillAdder);
    }

    private void resetSkillList(int newTagListInd) {
        //remove old frags, add new frags
        Iterator<String> oldFrags= SetSkillTags[selectIndex].iterator();
        while (oldFrags.hasNext()){
            Fragment forRemoval = getSupportFragmentManager().findFragmentByTag(oldFrags.next());
            getSupportFragmentManager().beginTransaction().remove(forRemoval).commit();
        }
        selectIndex = newTagListInd;
        Iterator<String> newFrags= SetSkillTags[selectIndex].iterator();
        while (oldFrags.hasNext()){
            Fragment forAdding = getSupportFragmentManager().findFragmentByTag(oldFrags.next());
            getSupportFragmentManager().beginTransaction().add(R.id.skillScroll, forAdding).commit();
        }
    }

    @Override
    public void onSetSelect(String fragTitle) {
        resetSkillList(Integer.parseInt(fragTitle.substring(0,1)));
    }

    @Override
    public void onAddSkill(View view) {
        //TODO bring frag into focus
        Log.i("popup", "adder clicked!!!!");
        Window currWin = getWindow();
        //currWin.setLocalFocus(???);
        currWin.setLayout(50, 50);
        WindowManager.LayoutParams params = currWin.getAttributes();
        params.dimAmount = 0.75f;
        params.gravity = Gravity.CENTER;
        getWindowManager().updateViewLayout(view, params);
        //TODO: reset layout, bring back edittxt + directions
        AppCompatButton done= view.findViewById(R.id.submitBtn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newSkillText = String.valueOf(((EditText) findViewById(R.id.newSkillNameEditor)).getText());
                //popup.cancel();TODO cancel popup
                FragmentTransaction myFt = getSupportFragmentManager().beginTransaction();
                myFt.add(R.id.skillScroll, SkillsFrag.newInstance(newSkillText), newSkillText);
                SetSkillTags[selectIndex].add(newSkillText);//updated list of frags
                myFt.commit();

            }
        });
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