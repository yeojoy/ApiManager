package me.yeojoy.apimanager.network.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends BaseResponse {
    @SerializedName("id")
    public int userId;
    @SerializedName("username")
    public String username;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
