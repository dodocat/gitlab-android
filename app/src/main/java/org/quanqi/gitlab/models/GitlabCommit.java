package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


import java.util.Date;
import java.util.List;

public class GitlabCommit {

    public final static String URL = "/commits";

    private String _id;
    private String _title;
    private String _description;

    @SerializedName("short_id")
    private String _shortId;

    @SerializedName("author_name")
    private String _authorName;

    @SerializedName("author_email")
    private String _authorEmail;

    @SerializedName("created_at")
    private Date _createdAt;

    @SerializedName("committed_date")
    private Date _committedDate;

    @SerializedName("authored_date")
    private Date _authoredDate;

    @SerializedName("parent_ids")
    private List<String> _parentIds;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getShortId() {
        return _shortId;
    }

    public void setShortId(String shortId) {
        _shortId = shortId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getAuthorName() {
        return _authorName;
    }

    public void setAuthorName(String authorName) {
        _authorName = authorName;
    }

    public String getAuthorEmail() {
        return _authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        _authorEmail = authorEmail;
    }

    public Date getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        _createdAt = createdAt;
    }

    public List<String> getParentIds() {
        return _parentIds;
    }

    public void setParentIds(List<String> parentIds) {
        _parentIds = parentIds;
    }
}
