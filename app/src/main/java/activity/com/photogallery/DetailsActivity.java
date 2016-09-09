package activity.com.photogallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import java.util.List;

import activity.com.photogallery.Adapters.SectionsPagerAdapter;
import activity.com.photogallery.Models.ImageModel;

public class DetailsActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    List<ImageModel> imageModelList;
    int position;

    public static final String IMAGE_ARG = "IMAGES";
    public static final String POSITION_ARG = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageModelList = getIntent().getParcelableArrayListExtra(IMAGE_ARG);
        position = getIntent().getIntExtra(POSITION_ARG, 0);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), imageModelList, this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(position);
        mViewPager.setPageTransformer(true, new RotateUpTransformer());
    }


}
