package org.quanqi.gitlab.feeds;

import java.util.List;

/**
 * Created by cindy on 9/4/14.
 */
public interface FeedParser {
    public List<ActivityEntry> parse();
}
