package com.hari.nsrav.bagdelivery;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsrav on 15-03-2018.
 */

public class Users {
    String name,phone,address,status;

    public Users() {
    }

    public Users(String name, String phone, String address,String status) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public String getName()
        {
            return "sravya";
        }*/
    @Exclude

    public  Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("phone", phone);
        result.put("address", address);
        result.put("status", status);
        return result;
    }
}

