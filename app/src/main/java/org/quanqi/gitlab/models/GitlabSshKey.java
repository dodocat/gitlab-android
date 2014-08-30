package org.quanqi.gitlab.models;

/**
 * Created by cindy on 8/29/14.
 */
public class GitlabSshKey {

    private int id;
    private int title;
    private String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
