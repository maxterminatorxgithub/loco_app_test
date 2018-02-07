package net.mybluemix.de_eu.maxterminatorx.apptestloco;



import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by maxterminatorx on 06-Feb-18.
 */

public class Flight {

    private Map<String,Object> properties;


    public Flight(int dbId,
                  String from,
                  String to,
                  String date,
                  String price,
                  String currency,
                  boolean favorite,
                  String image){

        properties = new HashMap<>();

        properties.put("db_id",dbId);
        properties.put("from",from);
        properties.put("to",to);
        properties.put("date",date);
        properties.put("price",price);
        properties.put("currency",currency);
        properties.put("favorite",favorite);
        properties.put("image",image);

    }

    public Object getProperty(String name){
        return properties.get(name);
    }

    public void loadImageFromUrl(final Context with, final ImageView loadTo, final ProgressBar hideAfterLoading){


        Picasso.with(with).load((String)properties.get("image")).into(loadTo,
                new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                        int imgWidth = loadTo.getWidth();
                        int imgHeight = loadTo.getHeight();

                        int drawableWidth = loadTo.getDrawable().getBounds().width();
                        int drawableHeight = loadTo.getDrawable().getBounds().height();

                        loadTo.setVisibility(View.VISIBLE);
                        loadTo.getLayoutParams().width = imgWidth>drawableWidth?imgWidth:drawableWidth;
                        loadTo.getLayoutParams().height = imgHeight>drawableHeight?imgHeight:drawableHeight;
                        hideAfterLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Flight.this.loadImageFromUrl(with,loadTo,hideAfterLoading);
                    }
                });

    }


}
