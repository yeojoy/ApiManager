package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 11. 4..
 */

public class InstaUser {
    @SerializedName("id")
    public String id;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("profile_picture")
    public String profilePictureUrl;
    @SerializedName("username")
    public String username;
}
