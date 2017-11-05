package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 11. 4..
 */

public class InstaImages {
    @SerializedName("thumbnail")
    public InstaImage thumbnail;
    @SerializedName("low_resolution")
    public InstaImage lowResolution;
    @SerializedName("standard_resolution")
    public InstaImage standardResolution;
}
