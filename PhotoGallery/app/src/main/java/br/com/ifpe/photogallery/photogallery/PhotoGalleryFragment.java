package br.com.ifpe.photogallery.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Adrielly, Kaio e Marcela on 13/05/2018.
 */

public class PhotoGalleryFragment extends Fragment {
    private static final String TAG = "PhotoGalleryFragment";
    GridView mGridView;
    ArrayList<GalleryItem> mItens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridview);
        adaptadorConf();
        return view;
    }

    protected void adaptadorConf() {
        if(getActivity() == null || mGridView == null) {
            return;
        }
        if(mItens != null) {
            mGridView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_gallery_item, mItens));
            mGridView.setAdapter(new GalleryItemAdapter(mItens));

        } else {
            mGridView.setAdapter(null);
        }
    }

    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {
        public GalleryItemAdapter(ArrayList<GalleryItem> itens){
            super(getActivity(), 0, itens);

        }

        @Override
        public View getView(int posicao, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.gallery_item, viewGroup,false);
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.mipmap.ic_launcher_imagem);
            return view;
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<GalleryItem>> {
        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
            return new FlickrFetchr().fetchItems(0);
        }

        @Override
        protected void onPostExecute(ArrayList<GalleryItem> galleryItems) {
            mItens = galleryItems;
            adaptadorConf();
        }
    }

}
