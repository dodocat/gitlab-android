package org.quanqi.gitlab.feeds;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;

import org.codehaus.jackson.map.util.ISO8601DateFormat;
import org.xml.sax.Attributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cindy on 9/5/14.
 */
public class AndroidSaxFeedParser extends BaseFeedParser {

    public static final String TAG = "AndroidSaxFeedParser";


    public AndroidSaxFeedParser(String feedUrl) {
        super(feedUrl);
    }

    public static final String XML_NS = "http://www.w3.org/2005/Atom";

    public List<ActivityEntry> parse() {
        final ActivityEntry currentEntry = new ActivityEntry();
        RootElement root = new RootElement("feed");
        root = new RootElement(XML_NS, "feed");
        final List<ActivityEntry> messages = new ArrayList<ActivityEntry>();
        final Element entry = root.getChild(XML_NS, "entry");
        Log.i(TAG, entry.toString());
        entry.setEndElementListener(new EndElementListener() {
            public void end() {
                messages.add(currentEntry.copy());
            }
        });
        entry.getChild(XML_NS, "link").setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                currentEntry.setLink(attributes.getValue("href"));
            }
        });
        entry.getChild(XML_NS, "updated").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                try {
                    currentEntry.setUpdated(new ISO8601DateFormat().parse(body));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        entry.getChild(XML_NS, "id").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                currentEntry.setId(body);
            }
        });
        entry.getChild("http://search.yahoo.com/mrss/", "thumbnail").setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                currentEntry.setThumbnail(attributes.getValue("url"));
            }
        });
        entry.getChild(XML_NS, "author").getChild(XML_NS, "name").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                currentEntry.setAuthor(body);
            }
        });

        entry.getChild(XML_NS, "author").getChild(XML_NS, "email").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                currentEntry.setEmail(body);
            }
        });

        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8,
                    root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

}
