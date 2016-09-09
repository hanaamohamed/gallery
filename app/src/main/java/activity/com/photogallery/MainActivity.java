package activity.com.photogallery;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import activity.com.photogallery.Adapters.GalleryAdapter;
import activity.com.photogallery.Models.ImageModel;
import activity.com.photogallery.Observers.OnItemClickListener;

public class MainActivity extends AppCompatActivity implements EndlessRecyclerViewAdapter.RequestToLoadMoreListener, OnItemClickListener {
    RecyclerView recyclerView;
    List<ImageModel> imagesList;
    GalleryAdapter adapter;
    EndlessRecyclerViewAdapter endlessRecyclerViewAdapter;
    boolean isDataReady;
    StaggeredGridLayoutManager gridLayoutManager;
    final int visibleThreshold = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        imagesList = new ArrayList<>();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        else
            gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        addImages();
        adapter = new GalleryAdapter(imagesList, this);
        adapter.setOnItemClickListener(this);
        endlessRecyclerViewAdapter = new EndlessRecyclerViewAdapter(this, adapter, this);
        recyclerView.setAdapter(endlessRecyclerViewAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    try {
                        getLastItemAndLoadMore();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
        } else
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    try {
                        getLastItemAndLoadMore();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


    }

    private void getLastItemAndLoadMore() throws Exception {
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int[] list;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            list = new int[3];
        else
            list = new int[2];
        int lastVisibleItem = gridLayoutManager.findLastCompletelyVisibleItemPositions(list)[0];

        if (isDataReady && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            if (endlessRecyclerViewAdapter != null) {
                endlessRecyclerViewAdapter.onDataReady(true);
            }
        }
    }

    private void addImages() {
        isDataReady = false;
        imagesList.add(new ImageModel(340, "http://lorempixel.com/400/250/city/3/"));
        imagesList.add(new ImageModel(250, "http://lorempixel.com/250/250/city/2/"));
        imagesList.add(new ImageModel(600, "http://lorempixel.com/600/250/city/1/"));
        imagesList.add(new ImageModel(200, "http://lorempixel.com/200/250/city/5/"));
        imagesList.add(new ImageModel(900, "http://lorempixel.com/900/250/city/4/"));
        imagesList.add(new ImageModel(700, "http://lorempixel.com/700/250/city/6/"));
        imagesList.add(new ImageModel(500, "http://lorempixel.com/400/500/city/7/"));
        isDataReady = true;
    }


    @Override
    public void onLoadMoreRequested() {
        if (isDataReady) {
            addImages();
            android.os.Handler handler = new android.os.Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    adapter.updateList(imagesList);

                }
            };
            handler.postDelayed(runnable, 500);

            // adapter.updateList(imagesList);
        } else
            try {
                endlessRecyclerViewAdapter.onDataReady(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public void onItemClickListener(View v, int pos) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putParcelableArrayListExtra(DetailsActivity.IMAGE_ARG, (ArrayList<? extends Parcelable>) imagesList);
        intent.putExtra(DetailsActivity.POSITION_ARG, pos);
        startActivity(intent);
    }
}
