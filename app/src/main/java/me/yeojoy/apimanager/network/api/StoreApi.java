package me.yeojoy.apimanager.network.api;

import io.reactivex.Observable;
import me.yeojoy.apimanager.ApiApplication;
import me.yeojoy.apimanager.network.model.Store;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AllStoresResponse;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.StoreResponse;
import me.yeojoy.apimanager.network.model.response.UserResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface StoreApi {

    @POST("/store/{store_name}")
    Observable<BaseResponse> create(@Header("Authorization") String accessToken,
                                    @Path("store_name") String storeName);

    @GET("/store/{storeId}")
    Observable<StoreResponse> get(@Header("Authorization") String accessToken,
                                  @Path("storeId") int id);

    @GET("/stores")
    Observable<AllStoresResponse> getAllStores(@Header("Authorization") String accessToken);

    @DELETE("/store/{storeId}")
    Observable<BaseResponse> deleteUser(@Header("Authorization") String accessToken,
                                        @Path("storeId") int id);

}
