package activity.com.photogallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.com.photogallery.ViewHolders.ImageHolder;
import activity.com.photogallery.Models.ImageModel;

/**
 * Created by hanaa mohamed on 09-Sep-16.
 */
public class ImageViewerFragment extends Fragment {
    static String IMAGES_ARG = "IMG";
    ImageModel imageModel;
    View rootView;

    public static ImageViewerFragment newInstance(ImageModel imageModel) {

        Bundle args = new Bundle();
        args.putParcelable(IMAGES_ARG, imageModel);
        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageModel = getArguments().getParcelable(IMAGES_ARG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.image_item_view, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*ImageView imgView = (ImageView) rootView.findViewById(R.id.img);
        Picasso.with(getActivity()).load(imageModel.getImagePath()).into(imgView);*/
        ImageHolder imageHolder = new ImageHolder(rootView, null);

        imageHolder.renderData(getActivity(), imageModel, 0,false);
    }
}

