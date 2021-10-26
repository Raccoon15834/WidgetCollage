package das.anusha.widgetcollage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditSkillsFrag extends Fragment {
    private MainClickListener myListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.myListener = (MainClickListener) context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.skilladderfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onAddSkill(view);
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.myListener = null;
    }
}
