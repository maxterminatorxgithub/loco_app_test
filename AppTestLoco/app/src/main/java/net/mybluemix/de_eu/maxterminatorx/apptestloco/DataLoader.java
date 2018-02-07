package net.mybluemix.de_eu.maxterminatorx.apptestloco;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by maxterminatorx on 06-Feb-18.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class DataLoader{


    interface ResponseHandler{
        public void onSuccess(JSONObject response);
        public void onError(String errorMassage);
    }

    private final String KEY;
    private final String URL;
    private Context context;
    private ResponseHandler responseHandler;

    public DataLoader(String url,String key,Context c,ResponseHandler responseHandler){
        KEY = key;
        URL = url;
        context = c;
        this.responseHandler = responseHandler;
    }



    public void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject json = new JSONObject(response);
                            responseHandler.onSuccess(json);
                        } catch (JSONException e) {
                            responseHandler.onError(e.getMessage());
                        }
                    }
                },new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                responseHandler.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", KEY);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
