package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.response.ListsResponse;
import retrofit2.http.GET;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface LocalApi {
    @GET("/dojo/list3")
    Flowable<ListsResponse> lists();
}
