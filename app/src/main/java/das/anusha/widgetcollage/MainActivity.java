package das.anusha.widgetcollage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
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

public class MainActivity extends AppCompatActivity implements EditSkillsFrag.addListener, SkillSetFrag.setSelector{
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
    public void onSetSelect(String fragTitle) { ;
        //TODO change selected look
        resetSkillList(Integer.parseInt(fragTitle.substring(0,1)));
    }

    @Override
    public void onAddSkill(View view, DialogFragment myPopup) {
        Log.i("expand", "popup");
        AppCompatButton done= view.findViewById(R.id.submitBtn);
        EditText myEditor = (EditText) view.findViewById(R.id.newSkillNameEditor);
        View mytxt = view.findViewById(R.id.directionsNewSkill);
        done.setVisibility(View.VISIBLE);
        myEditor.setVisibility(View.VISIBLE);
        mytxt.setVisibility(View.VISIBLE);
        myEditor.requestFocus();
        myPopup.show(getSupportFragmentManager(), "SkillAdderTag");//TODO pop up the popup
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPopup.dismiss();//TODO shrink popup
                String newSkillText = String.valueOf(myEditor.getText());
                FragmentTransaction myFt = getSupportFragmentManager().beginTransaction();
                myFt.add(R.id.skillScroll, SkillsFrag.newInstance(newSkillText), newSkillText);
                SetSkillTags[selectIndex].add(newSkillText);//updated list of frags
                myFt.commit();
                done.setVisibility(View.GONE);
                myEditor.setVisibility(View.GONE);
                mytxt.setVisibility(View.GONE);

            }
        });
    }
}

//Submit each layout screenshot
//P3L07aDasAnusha.apk
//embelishments apk and video
//guides.codepath.com/android/creating-and-using-fragments
//TODO TODAY - pop up, change sets
//TODO transition later
//TODO colors, logo? skillset select formatting
//TODO transitions when frag leaves returns, plus button animation, swipe delete
//TODO shared prefs JSON for saving lists of skill fragments