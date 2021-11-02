package das.anusha.widgetcollage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.util.Iterator;
import java.util.LinkedList;
public class MainActivity extends AppCompatActivity implements EditSkillsFrag.addListener, SkillSetFrag.setSelector{
    private String[] SKILLSETS;
    private int SETNUMBER;
    private int selectIndex = 0;
    private EditSkillsFrag adderFrag;
    private LinkedList<String>[] SetSkillTags;
    private ViewPager2 photoPager;
    private int imgNums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //skill sets on top
        SKILLSETS = getResources().getStringArray(R.array.skillsets);
        SETNUMBER = SKILLSETS.length;
        SetSkillTags = new LinkedList[SETNUMBER];
        for(int i=0; i<SETNUMBER; i++){
            String fragTag = i+SKILLSETS[i];
            SKILLSETS[i] = fragTag;
            FragmentTransaction myFt = getSupportFragmentManager().beginTransaction();
            myFt.add(R.id.sSetScroll, SkillSetFrag.newInstance(SKILLSETS[i], fragTag), fragTag);
            myFt.commit();//commit transaction to add to bar
            SetSkillTags[i] = new LinkedList<String>();
        }
        //fragment transitions
        resetSkillList(0);
        //adding new skill listener
        findViewById(R.id.plusSign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopup();
            }
        });
        //set up photos view pager, make circular TODO are super calls necessaru
        imgNums = getResources().getIntArray(R.array.photoIds).length;
        photoPager =findViewById(R.id.photos);
        photoPager.setAdapter(new myAdapter(this));
        photoPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private int currPos;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(currPos==0) photoPager.setCurrentItem(imgNums-2);
                if(currPos==imgNums-1) photoPager.setCurrentItem(1);
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void resetSkillList(int newTagListInd) {
        //selected look
        TextView oldtitleTxt = getFragmentManager().findFragmentByTag(SKILLSETS[selectIndex]).getView().findViewById(R.id.titleTxt);
        oldtitleTxt.setTextColor(getResources().getColor(R.color.white));//CAN NOT FIND BY TAG???
        TextView newtitleTxt = getSupportFragmentManager().findFragmentByTag(SKILLSETS[newTagListInd]).getView().findViewById(R.id.titleTxt);
        newtitleTxt.setTextColor(getResources().getColor(R.color.darkBlue));
        //removing
        Iterator<String> oldFrags= SetSkillTags[selectIndex].iterator();
        while (oldFrags.hasNext()){
            Fragment forRemoval = getSupportFragmentManager().findFragmentByTag(oldFrags.next());////TODO WHY DOESNT THIS WORKKKK??
            getSupportFragmentManager().beginTransaction().remove(forRemoval).commit();//TODO fix removal
        }
        //adding
        selectIndex = newTagListInd;
        Iterator<String> newFrags= SetSkillTags[selectIndex].iterator();
        while (newFrags.hasNext()){
            Fragment forAdding = getSupportFragmentManager().findFragmentByTag(newFrags.next());
            getSupportFragmentManager().beginTransaction().add(R.id.skillScroll, forAdding).commit();
        }
        selectIndex = newTagListInd;
    }

    @Override
    public void onSetSelect(String fragTitle) { ;
        ////remove old frags, add new frags
        resetSkillList(Integer.parseInt(fragTitle.substring(0,1)));
    }
    public void onShrinkSkill(DialogFragment daFrag, String newSkillText){
        FragmentTransaction myFt = getSupportFragmentManager().beginTransaction();
        SkillsFrag myNewSkill = SkillsFrag.newInstance(newSkillText);
        myNewSkill.setEnterTransition(new Slide(Gravity.RIGHT));
        myNewSkill.setExitTransition(new Slide(Gravity.LEFT));
        //look at subclasses https://developer.android.com/reference/android/transition/Transition
        //for custom or targeting subview https://stackoverflow.com/questions/35724449/define-certain-enter-and-exit-transitions-for-specific-views-in-activity
        myFt.add(R.id.skillScroll, myNewSkill, newSkillText);
        SetSkillTags[selectIndex].add(newSkillText);//updated list of frags
        adderFrag.dismiss();
        myFt.commit();
    }
    public void createPopup() {
        Log.i("expand", "popup");
        adderFrag = new EditSkillsFrag();
        adderFrag.show(getSupportFragmentManager(), "SkillAdderTag");
    }

    private class myAdapter extends FragmentStateAdapter {
        ViewPager2 myPager;
        public myAdapter(FragmentActivity fragmentActivity) {//what
           super(fragmentActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position){
            return PhotoFrag.newInstance(myPager,position);
        }
        @Override
        public int getItemCount() {
            //fix number with photoIds length
            return imgNums;
        }
    }
}

//Submit each layout screenshot
//P3L07aDasAnusha.apk
//embelishments apk and video
//guides.codepath.com/android/creating-and-using-fragments
//TODO fix switching sets
//TODO colors, logo? skillset select formatting
//TODO plus button animation, swipe delete
//TODO shared prefs JSON for saving lists of skill fragments