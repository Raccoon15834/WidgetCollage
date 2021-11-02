package das.anusha.widgetcollage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class EditSkillsFrag extends DialogFragment {
    private View myView;
    private addListener myListener;

    public interface addListener{
        void onShrinkSkill(DialogFragment daFrag, String newSkillTxt);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (addListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.skilladderfrag, null);
        builder.setView(view);
        DialogFragment daFrag = this;

        AppCompatButton done= view.findViewById(R.id.submitBtn);
        EditText myEditor = (EditText) view.findViewById(R.id.newSkillNameEditor);
        myEditor.requestFocus();
        view.findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newSkillText = String.valueOf(myEditor.getText());
                myListener.onShrinkSkill(daFrag, newSkillText);
            }
        });
        return builder.create();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
//    private class btnAddListener implements View.OnClickListener {
//        View overallView;
//        DialogFragment daFrag;
//        public btnAddListener(View overallView, DialogFragment daFrag){
//            this.overallView = overallView;
//            this.daFrag = daFrag;
//        }
//        @Override
//        public void onClick(View view) {
//            myListener.onAddSkill(overallView, daFrag);
//            //dim background
//           // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        }
//    }
}
