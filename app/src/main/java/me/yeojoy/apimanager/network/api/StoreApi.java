package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.response.AllStoresResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.StoreResponse;
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
    Flowable<BaseResponse> create(@Header("Authorization") String accessToken,
                                    @Path("store_name") String storeName);

    @GET("/store/{storeId}")
    Flowable<StoreResponse> get(@Header("Authorization") String accessToken,
                                  @Path("storeId") int id);

    @GET("/stores")
    Flowable<AllStoresResponse> getAllStores(@Header("Authorization") String accessToken);

    @DELETE("/store/{storeId}")
    Flowable<BaseResponse> deleteUser(@Header("Authorization") String accessToken,
                                        @Path("storeId") int id);

}
