package me.yeojoy.apimanager.network.model.request;

import com.google.gson.annotations.SerializedName;

public class ItemRegisterRequest {
    @SerializedName("price")
    private float price;
    @SerializedName("store_id")
    private int storeId;

    public ItemRegisterRequest(float price, int storeId) {
        this.price = price;
        this.storeId = storeId;
    }
}
