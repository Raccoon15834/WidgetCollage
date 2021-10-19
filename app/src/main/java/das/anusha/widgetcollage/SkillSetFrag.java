package das.anusha.widgetcollage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class SkillSetFrag extends Fragment {
    private View.OnClickListener mySSlistener;
    //TODO add listener that can communicate with activity
    //guides.codepath.com/android/creating-and-using-fragments
    String title;

    public static SkillSetFrag newInstance(String title){
        SkillSetFrag myNewFrag= new SkillSetFrag();
        myNewFrag.title = title;
        return myNewFrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.skillsetfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView myTitle = view.findViewById(R.id.titleTxt);
        myTitle.setText(title);
    }

}
