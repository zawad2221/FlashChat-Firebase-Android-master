package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/convert/global";
    final String APP_ID = "ZWZmZjQ3YjJkMTgyNDE5NmJkOWJlNzU5MTI1MTVlNGVkNWU0ZTViNDFjZTA0NDZhYTAzN2Q1NTJmY2NjNjJjYQ";

    private String currency[];

    // Member Variables:
    TextView mPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);
        currency=getResources().getStringArray(R.array.currency_array);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bit","Position "+position);
                //Log.d("Bit","Adept "+view.toString());

                Log.d("Bit","App mane "+currency[position]);
                RequestParams prams = new RequestParams();
                prams.put("from","BTC");
                prams.put("to",currency[position]);
                prams.put("amount",1);
                prams.put("appid",APP_ID);
                letsDoSomeNetworking(prams);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(RequestParams prams) {


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL,prams,new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Bit","Response "+response.toString());
                BitDataModel bitDataModel;
                bitDataModel=BitDataModel.fromJason(response);
                updateUI(bitDataModel);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("Bit","Fail "+throwable.toString());
            }
        });


    }


    //update ui
    private void updateUI(BitDataModel bitDataModel)
    {
        mPriceTextView.setText(bitDataModel.getbPrice());
    }


}
