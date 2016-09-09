package activity.com.photogallery.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import activity.com.photogallery.ViewHolders.ImageHolder;
import activity.com.photogallery.Models.ImageModel;
import activity.com.photogallery.Observers.OnItemClickListener;
import activity.com.photogallery.R;

/**
 * Created by hanaa mohamed on 09-Sep-16.
 */
public class GalleryAdapter extends RecyclerView.Adapter {
    List<ImageModel> images;
    Activity activity;
    OnItemClickListener onItemClickListener;

    public GalleryAdapter(List<ImageModel> images, Activity activity) {
        this.images = images;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.image_item_view, parent, false);
        return new ImageHolder(v,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.renderData(activity, images.get(position), position,true);
        }catch (Exception e){

        }

    }


    @Override
    public int getItemCount() {
        if (images == null)
            return 0;
        else
            return images.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateList(List<ImageModel> imagesList) {
        if (imagesList != null) {
            /*this.images.clear();*/
            this.images.addAll(imagesList);
        }
        this.notifyDataSetChanged();
    }
}
