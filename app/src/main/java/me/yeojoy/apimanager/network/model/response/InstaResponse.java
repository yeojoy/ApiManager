package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.yeojoy.apimanager.network.model.Instagram;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class InstaResponse extends BaseResponse {
    @SerializedName("items")
    public List<Instagram> instagrams;
}
