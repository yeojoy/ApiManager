package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.response.InstaResponse;
import me.yeojoy.apimanager.network.model.response.ListsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface InstagramApi {
    @GET("/do8eomation/media/")
    Flowable<InstaResponse> lists(@Query("max_id") String maxId);
}
