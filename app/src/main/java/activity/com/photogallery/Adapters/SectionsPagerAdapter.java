package activity.com.photogallery.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import activity.com.photogallery.ImageViewerFragment;
import activity.com.photogallery.Models.ImageModel;

/**
 * Created by hanaa mohamed on 09-Sep-16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<ImageModel> imageModels;
    Activity activity;

    public SectionsPagerAdapter(FragmentManager fm, List<ImageModel> imageModelList, Activity activity) {
        super(fm);
        this.activity = activity;
        this.imageModels = imageModelList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return ImageViewerFragment.newInstance(imageModels.get(position));
    }

    @Override
    public int getCount() {
        if (imageModels != null)
            return imageModels.size();
        else
            return 0;
    }

}
