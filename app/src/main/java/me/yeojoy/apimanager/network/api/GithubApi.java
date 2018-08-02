package me.yeojoy.apimanager.network.api;

import io.reactivex.Flowable;
import me.yeojoy.apimanager.network.model.response.GithubRepositoryResponse;
import me.yeojoy.apimanager.network.model.response.GithubUserResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public interface GithubApi {
    @GET("/users/{user_name}/repos")
    Flowable<GithubRepositoryResponse> repositories(@Path("user_name") String userName);

    @GET("/users/{user_name}")
    Flowable<GithubUserResponse> userDetail(@Path("user_name") String userName);
}
