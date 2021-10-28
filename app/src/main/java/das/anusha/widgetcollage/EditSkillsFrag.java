package das.anusha.widgetcollage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class EditSkillsFrag extends DialogFragment {
    private addListener myListener;

    public interface addListener{
        void onAddSkill(View view, DialogFragment daFrag);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (addListener) context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.skilladderfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.plusSign).setOnClickListener(new btnAddListener(view, this) );
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
    private class btnAddListener implements View.OnClickListener {
        View overallView;
        DialogFragment daFrag;
        public btnAddListener(View overallView, DialogFragment daFrag){
            this.overallView = overallView;
            this.daFrag = daFrag;
        }
        @Override
        public void onClick(View view) {
            myListener.onAddSkill(overallView, daFrag);
            //dim background
           // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }
}
