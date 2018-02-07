package net.mybluemix.de_eu.maxterminatorx.apptestloco;



import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by maxterminatorx on 06-Feb-18.
 */

public class LocoAdapter extends RecyclerView.Adapter<LocoViewHolder>{

    private Context context;
    private List<Flight> flightList;

    public LocoAdapter(Context c, List<Flight> flightList){
        context=c;
        this.flightList = flightList;
    }


    @Override
    public LocoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context)
                .inflate(R.layout.flight, parent, false);

        final LocoViewHolder myViewHolder = new LocoViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(LocoViewHolder holder, int position) {
        Flight f = flightList.get(position);

        holder.setFromTo(f.getProperty("from")+" - "+f.getProperty("to"));
        holder.setDate((String)f.getProperty("date"));
        holder.setFavorite((boolean)f.getProperty("favorite"));
        holder.setCurrency(f.getProperty("currency").toString());
        holder.setPrice(f.getProperty("price").toString());

        f.loadImageFromUrl(context,holder.getBackground(),holder.getProgressBar());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

}
