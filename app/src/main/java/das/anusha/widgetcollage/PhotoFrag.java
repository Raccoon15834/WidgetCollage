package das.anusha.widgetcollage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class PhotoFrag extends Fragment {
    int pos;
    public PhotoFrag(ViewPager2 myPager, int position) {
        pos = position;
        //setparent as pager??
    }

    public static Fragment newInstance(ViewPager2 myPager, int position) {
        PhotoFrag daNewFrag =  new PhotoFrag(myPager, position);
        return daNewFrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.photofrag, null);
        //inflate with own view
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView myphoto = (ImageView)view.findViewById(R.id.photos);
        int[] imgRes = getResources().getIntArray(R.array.photoIds);
        myphoto.setBackgroundResource(imgRes[pos]);
        //add image from resources as position
    }
}
