package br.com.ifpe.photogallery.photogallery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Adrielly, Kaio e Marcela on 13/05/2018.
 */

public class FlickrFetchr {
    public static final String TAG = "FlickrFetchr";
    private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    private static final String API_KEY = "23ba2011fe73935f6479700637c0e410";
    private static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    private static final String PARAM_EXTRAS = "extras";
    private static final String EXTRA_SMALL_URL = "urls";
    private static final String XML_PHOTO = "photo";

    byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = conexao.getInputStream();

            if(conexao.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytes =  0;
            byte[] buffer = new byte[1024];

            while((bytes = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, bytes);
            }

            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();

        } finally {
            conexao.disconnect();
        }
    }


    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public ArrayList<GalleryItem> fetchItems(int pagina) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GET_RECENT)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter(PARAM_EXTRAS, EXTRA_SMALL_URL)
                .appendQueryParameter("pagina", Integer.toString(pagina))
                .build().toString();
        return downloadItens(url);
    }

    public ArrayList<GalleryItem> downloadItens(String url) {
        ArrayList<GalleryItem> itens = new ArrayList<GalleryItem>();

        try {
            String xmlString = getUrl(url);
            Log.i(TAG, "Received xml: " + xmlString);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            parseItens(itens, parser);

        } catch (IOException ex) {
            Log.e(TAG, "Falha na busca.", ex);
        } catch (XmlPullParserException ex) {
            Log.e(TAG, "Falha no item encotrado.", ex);
        }
        return itens;
    }

    protected void parseItens(ArrayList<GalleryItem> itens, XmlPullParser parser) throws XmlPullParserException, IOException {
        int tipoEvento = parser.next();

        while(tipoEvento != XmlPullParser.END_DOCUMENT) {
            if(tipoEvento == XmlPullParser.START_TAG && XML_PHOTO.equals(parser.getName())) {
                String id = parser.getAttributeValue(null, "id");
                String legenda = parser.getAttributeValue(null, "titulo");
                String url = parser.getAttributeValue(null, EXTRA_SMALL_URL);

                GalleryItem item = new GalleryItem();
                item.setId(id);
                item.setLegenda(legenda);
                item.setUrl(url);
                itens.add(item);
            }

            tipoEvento = parser.next();
        }
    }

}
