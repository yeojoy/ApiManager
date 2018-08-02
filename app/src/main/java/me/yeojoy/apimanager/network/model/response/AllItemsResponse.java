package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.yeojoy.apimanager.network.model.Item;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class AllItemsResponse extends BaseResponse {
    @SerializedName("items")
    public List<Item> items;
}
