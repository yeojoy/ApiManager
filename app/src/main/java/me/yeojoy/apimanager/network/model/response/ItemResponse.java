package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

import me.yeojoy.apimanager.network.model.Item;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class ItemResponse extends BaseResponse {
    @SerializedName("item")
    public Item item;
}
