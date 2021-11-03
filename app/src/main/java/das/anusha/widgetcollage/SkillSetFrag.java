package das.anusha.widgetcollage;

import android.content.Context;
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
    String title, fragTag;
    private setSelector myListener;
    private View itsView;
    //TODO: complexify to not menu bar

    public static SkillSetFrag newInstance(String title, String fragTag){
        SkillSetFrag myNewFrag= new SkillSetFrag();
        myNewFrag.title = title;
        myNewFrag.fragTag = fragTag;
        return myNewFrag;
    }

    public View getItsView() {
        return itsView;
    }

    public interface setSelector{
        void onSetSelect(String fragTag);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (setSelector) context;
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onSetSelect(fragTag);
            }
        });
        itsView = view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
}
