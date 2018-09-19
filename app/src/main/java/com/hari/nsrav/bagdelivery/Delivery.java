package com.hari.nsrav.bagdelivery;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsrav on 16-03-2018.
 */

public class Delivery {
    String name,phone;
    public Delivery() {
    }

    public Delivery(String name, String phone) {
        this.name = name;
        this.phone = phone;



    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("phone", phone);

        return result;
    }
}
