package net.mybluemix.de_eu.maxterminatorx.apptestloco;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maxterminatorx on 06-Feb-18.
 */

public class LocoViewHolder extends RecyclerView.ViewHolder {

    private ImageView backfround;
    private ProgressBar loading;

    private TextView txtFromTo;
    private TextView txtFlightDate;
    private TextView txtRecommended;
    private TextView txtCurrency;
    private TextView txtPrice;

    public LocoViewHolder(View v) {
        super(v);

        txtFromTo = v.findViewById(R.id.from_to_text);
        txtFlightDate = v.findViewById(R.id.date_text);
        txtRecommended = v.findViewById(R.id.recommended_text);
        txtCurrency = v.findViewById(R.id.currency_text);
        txtPrice = v.findViewById(R.id.price_text);

        loading = v.findViewById(R.id.image_progress_bar);
        backfround = v.findViewById(R.id.background);

    }

    public void setFromTo(String fromTo) {
        txtFromTo.setText(fromTo);

    }

    public void setDate(String date) {

        String[] DateFrags = date.split(" ")[0].split("-");
        Date d = new Date(Integer.valueOf(DateFrags[0]),Integer.valueOf(DateFrags[1]),Integer.valueOf(DateFrags[2]));
        Date d2 = new Date(d.getTime()+1000*60*60*24*3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd");
        txtFlightDate.setText(dateFormat.format(d)+" - "+dateFormat.format(d2));
    }

    public void setFavorite(boolean favorite) {
        if(favorite)
            txtRecommended.setVisibility(View.VISIBLE);
        else
            txtRecommended.setVisibility(View.INVISIBLE);
    }

    public void setCurrency(String currency) {
        txtCurrency.setText(currency);
    }

    public void setPrice(String price) {
        txtPrice.setText(price);
    }

    public ImageView getBackground() {
        return backfround;
    }

    public ProgressBar getProgressBar() {
        return loading;
    }

}
