package me.yeojoy.apimanager.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("items")
    public List<Item> items;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
