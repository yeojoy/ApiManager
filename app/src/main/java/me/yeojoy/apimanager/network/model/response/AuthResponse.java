package me.yeojoy.apimanager.network.model.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse extends BaseResponse {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("refresh_token")
    public String refreshToken;
}
