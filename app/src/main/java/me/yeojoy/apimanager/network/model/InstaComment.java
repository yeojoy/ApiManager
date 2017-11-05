package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 11. 4..
 */

public class InstaComment {
    @SerializedName("id")
    public String id;
    @SerializedName("text")
    public String text;
    @SerializedName("created_time")
    public String createdTime;
    @SerializedName("from")
    public InstaUser from;
}
