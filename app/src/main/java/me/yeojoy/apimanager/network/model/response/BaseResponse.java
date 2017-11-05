package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 10. 17..
 */

public class BaseResponse {
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;
    @SerializedName("more_available")
    public boolean isMoreAvailable;
}
