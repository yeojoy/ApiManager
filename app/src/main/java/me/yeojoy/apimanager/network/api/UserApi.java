package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.UserResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface UserApi {

    @POST("/register")
    Flowable<BaseResponse> register(@Body UserRegisterRequest request);

    @POST("/login")
    Flowable<AuthResponse> login(@Body UserRegisterRequest request);

    @GET("/user/{userId}")
    Flowable<UserResponse> getUser(@Path("userId") int id);

    @DELETE("/user/{userId}")
    Flowable<BaseResponse> deleteUser(@Path("userId") int id);

}
