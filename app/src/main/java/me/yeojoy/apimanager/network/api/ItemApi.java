package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.request.ItemRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AllItemsResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.ItemResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface ItemApi {

    @POST("/item/{item_name}")
    Flowable<BaseResponse> create(@Header("Authorization") String accessToken,
                                    @Path("item_name") String itemName,
                                    @Body ItemRegisterRequest request);

    @GET("/item/{item_name}")
    Flowable<ItemResponse> get(@Header("Authorization") String accessToken,
                                 @Path("item_name") String itemName);

    @DELETE("/item/{item_name}")
    Flowable<BaseResponse> deleteUser(@Header("Authorization") String accessToken,
                                        @Path("item_name") String itemName);

    @PUT("/item/{item_name}")
    Flowable<ItemResponse> update(@Header("Authorization") String accessToken,
                                    @Path("item_name") String itemName,
                                    @Body ItemRegisterRequest request);

    @GET("/items")
    Flowable<AllItemsResponse> getAllItems(@Header("Authorization") String accessToken);

}
