package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

public class BitDataModel {
    private String bPrice;


    public static BitDataModel fromJason(JSONObject jsonObject)
    {
        BitDataModel bitDataModel=new BitDataModel();

        try {
            double price=jsonObject.getDouble("price");
            bitDataModel.bPrice=String.valueOf(price);


        }
        catch (JSONException e)
        {
            e.printStackTrace();

        }

        return  bitDataModel;
    }

    public String getbPrice() {
        return bPrice;
    }
}
