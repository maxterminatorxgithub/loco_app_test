package net.mybluemix.de_eu.maxterminatorx.apptestloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataLoader.ResponseHandler{


    private static final String URL = "http://192.168.14.82";
    private static final String KEY = "erty78912xx";

    private LocoAdapter adapter;
    private List<Flight> flights;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flights = new ArrayList<>();

        adapter = new LocoAdapter(this,flights);




        recycler = findViewById(R.id.main_list);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));





        DataLoader loader = new DataLoader(URL,KEY,this,this);
        loader.loadData();

        Log.i("start","reading");



    }

    @Override
    public void onSuccess(JSONObject json) {
        Log.i("json responce","success");

        try {

            int status = json.getInt("status");
            Log.i("status",String.valueOf(status));

            if(status == 0){
                analizeData(json.getJSONArray("data"));
            }


        }catch (JSONException e) {
            Log.i("error:",e.getMessage());
        }
    }

    @Override
    public void onError(String errorMassage) {
        Log.i("json responce","failed");
    }


    private void analizeData(JSONArray dataArray)throws JSONException{
        int length = dataArray.length();

        for(int i=0;i<length;i++){
            JSONObject jsonObject = dataArray.getJSONObject(i);
            Log.i(String.valueOf(i),jsonObject.toString());
            flights.add(getFlight(jsonObject));
        }

        recycler.setAdapter(adapter);
    }

    private Flight getFlight(JSONObject json)throws JSONException{

        int dbId = json.getInt("db_id");
        String from = json.getString("from");
        String to = json.getString("to");
        String date = json.getString("date");
        String price = json.getString("price");
        String currency = json.getString("currency");
        boolean favorite = json.getBoolean("favorite");
        String image = json.getString("image");

        return new Flight(dbId,from,to,date,price,currency,favorite,image);
    }

}
