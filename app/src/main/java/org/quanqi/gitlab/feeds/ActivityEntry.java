package org.quanqi.gitlab.feeds;

import java.util.Date;

/**
 * Created by cindy on 9/4/14.
 */
public class ActivityEntry implements Comparable<ActivityEntry> {
    private String author;
    private Date updated;
    private String thumbnail;
    private String email;
    private String id;
    private String link;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int compareTo(ActivityEntry another) {
        if (another == null) return 1;
        return updated.compareTo(another.getUpdated());
    }

    public String toString() {
        return "author: " + author + ", Email: " + email + ", updated: " + updated
                + ", link: " + link + ", thumb: " + thumbnail + ", id: " + id +
                "";
    }

    public ActivityEntry copy() {
        ActivityEntry entry = new ActivityEntry();
        entry.setAuthor(this.author);
        entry.setEmail(this.email);
        entry.setLink(this.link);
        entry.setThumbnail(this.thumbnail);
        entry.setId(this.id);
        entry.setUpdated(this.updated);
        return entry;
    }


}
