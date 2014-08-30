package org.quanqi.gitlab.models;


import com.google.gson.annotations.SerializedName;

public abstract class GitlabAbstractMember extends GitlabUser {

	public static final String URL = "/members";

    @SerializedName("access_level")
	private int _accessLevel;

	public GitlabAccessLevel getAccessLevel() {
		return GitlabAccessLevel.fromAccessValue(_accessLevel);
	}

	public void setAccessLevel(GitlabAccessLevel accessLevel) {
		_accessLevel = accessLevel.accessValue;
	}

}
