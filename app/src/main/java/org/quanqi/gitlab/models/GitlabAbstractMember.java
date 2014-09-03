package org.quanqi.gitlab.models;


import com.google.gson.annotations.SerializedName;

public abstract class GitlabAbstractMember extends GitlabUser {

	public static final String URL = "/members";

    @SerializedName("access_level")
	private int accessLevel;

	public GitlabAccessLevel getAccessLevel() {
		return GitlabAccessLevel.fromAccessValue(accessLevel);
	}

	public void setAccessLevel(GitlabAccessLevel accessLevel) {
		this.accessLevel = accessLevel.accessValue;
	}

}
