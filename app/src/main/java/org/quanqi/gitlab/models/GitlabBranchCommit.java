package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


import java.util.Date;

public class GitlabBranchCommit {
    public static String URL = "/users";

    private String _id;
    private String _tree;
    private String _message;
    private GitlabUser _author;
    private GitlabUser _committer;

    @SerializedName("authored_date")
    private Date _authoredDate;

    @SerializedName("committed_date")
    private Date _committedDate;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getTree() {
        return _tree;
    }

    public void setTree(String tree) {
        _tree = tree;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public GitlabUser getAuthor() {
        return _author;
    }

    public void setAuthor(GitlabUser author) {
        _author = author;
    }

    public GitlabUser getCommitter() {
        return _committer;
    }

    public void setCommitter(GitlabUser committer) {
        _committer = committer;
    }

    public Date getAuthoredDate() {
        return _authoredDate;
    }

    public void setAuthoredDate(Date authoredDate) {
        _authoredDate = authoredDate;
    }

    public Date getCommittedDate() {
        return _committedDate;
    }

    public void setCommittedDate(Date committedDate) {
        _committedDate = committedDate;
    }
}
