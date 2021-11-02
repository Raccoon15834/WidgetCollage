package das.anusha.widgetcollage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//change to vector background

public class SkillsFrag extends Fragment {
    String title;
    public static SkillsFrag newInstance(String title){
        SkillsFrag myNewFrag= new SkillsFrag();
        myNewFrag.title = title;
        return myNewFrag;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.skill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView myTitle = view.findViewById(R.id.titleTxt);
        myTitle.setText(title);
    }

//    @Override
//    public void onDestroyView() {
//        getView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.));
//        super.onDestroyView();
//    }
}
