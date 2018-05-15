package br.com.ifpe.photogallery.photogallery;

/**
 * Created by Adrielly, Kaio e Marcela on 13/05/2018.
 */

public class GalleryItem {
    private String mId;
    private String mLegenda;
    private String mUrl;

    public GalleryItem() {}

    public GalleryItem(String mId, String mLegenda, String mUrl) {
        this.mId = mId;
        this.mLegenda = mLegenda;
        this.mUrl = mUrl;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getLegenda() {
        return this.mLegenda;
    }

    public void setLegenda(String mLegenda) {
        this.mLegenda = mLegenda;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

}
