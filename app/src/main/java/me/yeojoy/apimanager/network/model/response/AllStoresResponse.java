package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.yeojoy.apimanager.network.model.Store;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class AllStoresResponse extends BaseResponse {
    @SerializedName("stores")
    public List<Store> stores;
}
