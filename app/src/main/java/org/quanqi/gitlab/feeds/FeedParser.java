package org.quanqi.gitlab.feeds;

import java.util.List;

/**
 * Interface of a Entry parse.
 * Created by cindy on 9/4/14.
 */
public interface FeedParser {
    public List<ActivityEntry> parse();
}
