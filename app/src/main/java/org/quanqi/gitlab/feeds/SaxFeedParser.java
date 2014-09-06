package org.quanqi.gitlab.feeds;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by cindy on 9/4/14.
 */
public class SaxFeedParser extends BaseFeedParser {

        protected SaxFeedParser(String feedUrl){
            super(feedUrl);
        }

        public List<ActivityEntry> parse() {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser parser = factory.newSAXParser();
                RssHandler handler = new RssHandler();
                parser.parse(this.getInputStream(), handler);
                return handler.getMessages();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}
