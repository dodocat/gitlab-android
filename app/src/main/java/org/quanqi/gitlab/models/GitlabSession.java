package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;

public class GitlabSession extends GitlabUser {
    public static final String URL = "/session";

    @SerializedName("private_token")
    private String privateToken;

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

}
