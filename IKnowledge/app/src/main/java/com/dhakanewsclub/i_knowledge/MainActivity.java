package com.dhakanewsclub.i_knowledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;




public class MainActivity extends AppCompatActivity {

    // TODO: Create the base URL
    private final String BASE_URL = "https://gateway.watsonplatform.net/visual-recognition/api/v3/classify";
    final String APP_ID = "8MiRUs_T79t-ZNs_tqkFT8T4B8N8HGDJcwINTrFBWoQR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestParams prams = new RequestParams();
        prams.put("url","https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/fruitbowl.jpg");

        prams.put("version","2018-03-19");
        prams.put("appid",APP_ID);

        letsDoSomeNetworking(prams);

    }

    private void letsDoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization","Basic YXBpa2V5OjhNaVJVc19UNzl0LVpOc190cWtGVDhUNEI4TjhIR0RKY3dJTlRyRkJXb1FS");
        client.get(BASE_URL,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Bit","Response "+response.toString());


                Log.d("Bit","token "+"apikey".getBytes()+APP_ID.getBytes());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("Bit","Fail "+statusCode+" "+throwable.toString());
            }
        });


    }
}
