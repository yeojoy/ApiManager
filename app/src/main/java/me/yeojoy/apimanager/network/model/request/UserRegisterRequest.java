package me.yeojoy.apimanager.network.model.request;

import com.google.gson.annotations.SerializedName;

public class UserRegisterRequest {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public UserRegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
