package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class ListsResponse extends BaseResponse {
    @SerializedName("items")
    public List<String> lists;
}
