package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yeojoy on 2017. 11. 4..
 */

public class InstaLikes {
    @SerializedName("data")
    public List<InstaUser> likes;
    @SerializedName("count")
    public int count;
}
