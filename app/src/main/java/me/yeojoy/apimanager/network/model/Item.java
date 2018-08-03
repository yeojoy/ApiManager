package me.yeojoy.apimanager.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public float price = -1f;
    @SerializedName("store_id")
    public int storeId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
