package org.quanqi.gitlab.feeds;

import android.util.Log;

import junit.framework.TestCase;

import java.util.List;

public class AndroidSaxFeedParserTest extends TestCase {

    public void testParse() throws Exception {
        AndroidSaxFeedParser parser = new AndroidSaxFeedParser("https://gitlab.com/dashboard.atom?private_token=Ngz1rqMMSsomp4yttRm3");
        List<ActivityEntry> entries = parser.parse();
        for (ActivityEntry entry : entries) {
            Log.i("AndroidSaxFeedParserTest", "entry: " + entry.toString());
        }

    }
}