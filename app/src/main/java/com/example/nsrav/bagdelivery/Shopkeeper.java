package com.example.nsrav.bagdelivery;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsrav on 16-03-2018.
 */

public class Shopkeeper {
    String name,phone,address;
    public Shopkeeper() {
    }
    public Shopkeeper(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;



    }
    public String getName()
    {
        return name;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("phone", phone);
        result.put("address", address);

        return result;
    }
}
