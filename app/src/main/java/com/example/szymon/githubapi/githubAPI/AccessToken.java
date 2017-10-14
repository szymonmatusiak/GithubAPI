package com.example.szymon.githubapi.githubAPI;

import com.google.gson.annotations.SerializedName;

/**
 * Created by szymon on 14.10.17.
 */

public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
