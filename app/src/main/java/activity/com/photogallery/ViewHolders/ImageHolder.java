package activity.com.photogallery.ViewHolders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import activity.com.photogallery.Models.ImageModel;
import activity.com.photogallery.Observers.OnItemClickListener;
import activity.com.photogallery.R;

/**
 * Created by hanaa mohamed on 09-Sep-16.
 */
public class ImageHolder extends RecyclerView.ViewHolder {
    ImageView img;
    RelativeLayout container;
    ProgressBar pb;
    OnItemClickListener onItemClickListener;

    public ImageHolder(final View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
        pb = (ProgressBar) itemView.findViewById(R.id.Progress);

        container = (RelativeLayout) itemView.findViewById(R.id.img_layout);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClickListener(itemView, getAdapterPosition());
            }
        });
    }

    public void renderData(Activity activity, ImageModel item, int position, boolean resizeView) {
        final Animation animation = AnimationUtils.loadAnimation(activity, R.anim.fadein);

        Picasso.with(activity).load(item.getImagePath()).into(img, new Callback() {
            @Override
            public void onSuccess() {
                pb.setVisibility(View.GONE);
                img.startAnimation(animation);

            }

            @Override
            public void onError() {
                pb.setVisibility(View.GONE);
            }
        });
        if (resizeView) {
            container.getLayoutParams().height = (item.getHeight());
            container.getLayoutParams().height = (item.getHeight());
            img.getLayoutParams().height = item.getHeight();
            img.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
