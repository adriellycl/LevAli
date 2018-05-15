package br.com.ifpe.photogallery.photogallery;

import android.support.v4.app.Fragment;

/**
 * Created by Adrielly, Kaio e Marcela on 13/05/2018.
 */

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PhotoGalleryFragment();
    }

}
