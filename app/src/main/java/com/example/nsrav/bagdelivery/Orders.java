package com.example.nsrav.bagdelivery;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsrav on 17-03-2018.
 */

public class Orders {
    String key,customerkey,shopposition,imageB;
    public Orders() {
    }

    public Orders(String key, String customerkey, String shopposition, String imageB) {
        this.key = key;
        this.customerkey = customerkey;
        this.shopposition= shopposition;
        this.imageB = imageB;


    }
    public String getCustomerkey() {
        return customerkey;
    }

    public void setCustomerkey(String customerkey) {
        this.customerkey = customerkey;
    }

    public String getShopposition() {
        return  shopposition;
    }

    public void setShopposition(String shopposition) {
        this.shopposition = shopposition;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageB() {
        return imageB;
    }

    public void setImageB(String imageB) {
        this.imageB = imageB;
    }

    /*public String getName()
        {
            return "sravya";
        }*/
    @Exclude

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("customerkey", customerkey);
        result.put("shopposition", shopposition);
        result.put("imageB", imageB);
        return result;
    }
}



