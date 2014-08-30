package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


public class GitlabBranch {
	public final static String URL = "/repository/branches/";
	
	@SerializedName("name")
    private String _name;

    	@SerializedName("commit")
    	private GitlabBranchCommit _commit;
	
	@SerializedName("protected")
	private boolean _protected;

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public GitlabBranchCommit getCommit() {
		return _commit;
	}

	public void setCommit(GitlabBranchCommit commit) {
		this._commit = commit;
	}

	public boolean isProtected() {
		return _protected;
	}

	public void setProtected(boolean isProtected) {
		this._protected = isProtected;
	}
}